/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Contributors:
 *     ybonnel - initial API and implementation
 */
package fr.ybo;

import fr.ybo.modele.Answer;
import fr.ybo.modele.ParametreUrl;
import fr.ybo.modele.bus.Alert;
import fr.ybo.modele.bus.ParkRelai;
import fr.ybo.modele.bus.PointDeVente;
import fr.ybo.modele.velos.Station;
import fr.ybo.sax.GetAlertsHandler;
import fr.ybo.sax.GetParkRelaiHandler;
import fr.ybo.sax.GetPointDeVenteHandler;
import fr.ybo.sax.GetStationHandler;
import fr.ybo.sax.KeolisHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Classe d'accés aux API Keolis. Cette classe est une singletton.
 *
 * @author ybonnel
 */
public class Keolis {

    /**
     * URL d'accés au API Keolis.
     */
    private static final String URL = "http://data.keolis-rennes.com/xml/";

    /**
     * Version.
     */
    private static final String VERSION = "2.0";
    /**
     * Commande pour récupérer les stations.
     */
    private static final String COMMANDE_STATIONS = "getbikestations";
    /**
     * Commande pour récupérer les alerts.
     */
    private static final String COMMANDE_ALERTS = "getlinesalerts";
    /**
     * Commande pour récupérer les Park relais.
     */
    private static final String COMMANDE_PARK_RELAI = "getrelayparks";
    /**
     * Commande pour récupérer les points de vente.
     */
    private static final String COMMANDE_POS = "getpos";

    /**
     * Constructeur.
     *
     * @param apiKey clé fournie par le site.
     */
    public Keolis(String apiKey) {
        key = apiKey;
    }

    private final String key;

    /**
     * @param <T>     type d'objet Keolis.
     * @param url     url.
     * @param handler handler.
     * @return liste d'objets Keolis.
     * @throws KeolisReseauException en cas d'erreur réseau.
     * @throws KeolisException       en cas d'erreur lors de l'appel aux API Keolis.
     */
    private static <T> List<T> appelKeolis(String url, KeolisHandler<T> handler) throws KeolisReseauException {
        Answer<T> answer;
        try {
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(connection.getInputStream(), handler);
            answer = handler.getAnswer();
        } catch (IOException socketException) {
            throw new KeolisReseauException(socketException);
        } catch (SAXException saxException) {
            throw new KeolisReseauException(saxException);
        } catch (ParserConfigurationException exception) {
            throw new KeolisException("Erreur lors de l'appel à l'API keolis", exception);
        }
        if (answer == null || answer.getStatus() == null || !"0".equals(answer.getStatus().getCode())) {
            throw new KeolisReseauException();
        }
        return answer.getData();
    }

    /**
     * Appel les API Keolis pour récupérer les alertes.
     *
     * @return les alertes.
     * @throws KeolisReseauException pour toutes erreurs réseaux.
     */
    public List<Alert> getAlerts() throws KeolisReseauException {
        return appelKeolis(getUrl(COMMANDE_ALERTS), new GetAlertsHandler());
    }

    /**
     * Appel aux API Keolis pour récupérer les stations.
     *
     * @param url url à appeler.
     * @return la liste des stations.
     * @throws KeolisReseauException pour toutes erreurs réseaux.
     */
    private List<Station> getStation(String url) throws KeolisReseauException {
        return appelKeolis(url, new GetStationHandler());
    }

    /**
     * Appel aux API Keolis pour récupérer une station à partir de son number.
     *
     * @param number numéro de la station.
     * @return la station.
     * @throws KeolisReseauException pour toutes erreurs réseaux.
     */
    private Station getStationByNumber(String number) throws KeolisReseauException {
        List<Station> stations = getStation(getUrl(COMMANDE_STATIONS, new ParametreUrl("station", "number"), new ParametreUrl("value", number)));
        Station station = null;
        if (!stations.isEmpty()) {
            station = stations.get(0);
        }
        return station;
    }

    /**
     * Appel aux API Keolis pour récupérer les stations à partir de leurs
     * numéros.
     *
     * @param numbers numéros des stations.
     * @return la station.
     * @throws KeolisReseauException pour toutes erreurs réseaux.
     */
    public List<Station> getStationByNumbers(Collection<String> numbers) throws KeolisReseauException {
        List<Station> stations = new ArrayList<Station>();
        if (numbers.size() <= 2) {
            for (String number : numbers) {
                stations.add(getStationByNumber(number));
            }
        } else {
            for (Station station : getStations()) {
                if (numbers.contains(station.getNumber())) {
                    stations.add(station);
                }
            }
        }
        return stations;
    }

    /**
     * Appel aux API Keolis pour récupérer les stations.
     *
     * @return la listes des stations.
     * @throws KeolisReseauException pour toutes erreurs réseaux.
     */
    public List<Station> getStations() throws KeolisReseauException {
        return getStation(getUrl(COMMANDE_STATIONS));
    }

    /**
     * @return les parks relais.
     * @throws KeolisReseauException pour toutes erreurs réseaux.
     */
    public List<ParkRelai> getParkRelais() throws KeolisReseauException {
        return appelKeolis(getUrl(COMMANDE_PARK_RELAI), new GetParkRelaiHandler());
    }

    /**
     * @return les points de ventes.
     * @throws KeolisReseauException pour toutes erreurs réseaux.
     */
    public List<PointDeVente> getPointDeVente() throws KeolisReseauException {
        return appelKeolis(getUrl(COMMANDE_POS), new GetPointDeVenteHandler());
    }

    /**
     * Permet de récupérer l'URL d'accés aux API Keolis en fonction de la
     * commande à exécuter.
     *
     * @param commande commande à exécuter.
     * @return l'url.
     */
    private String getUrl(String commande) {
        StringBuilder stringBuilder = new StringBuilder(URL);
        stringBuilder.append("?version=").append(VERSION);
        stringBuilder.append("&key=").append(key);
        stringBuilder.append("&cmd=").append(commande);
        return stringBuilder.toString();
    }

    /**
     * Permet de récupérer l'URL d'accés aux API Keolis en fonction de la
     * commande à exécuter et d'un paramètre.
     *
     * @param commande commande à exécuter.
     * @param params   liste de paramètres de l'url.
     * @return l'url.
     */
    private String getUrl(String commande, ParametreUrl... params) {
        StringBuilder stringBuilder = new StringBuilder(getUrl(commande));
        for (ParametreUrl param : params) {

            try {
                stringBuilder.append("&param[").append(param.getName()).append("]=").append(URLEncoder.encode(param.getValue(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                throw new KeolisException("Erreur lors de la construction de l'URL", e);
            }
        }
        return stringBuilder.toString();
    }

}
