/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.ybo.opendata.rennes;

import fr.ybo.opendata.rennes.modele.Answer;
import fr.ybo.opendata.rennes.modele.ParametreUrl;
import fr.ybo.opendata.rennes.modele.bus.Alert;
import fr.ybo.opendata.rennes.modele.bus.LignePicto;
import fr.ybo.opendata.rennes.modele.bus.ParkRelai;
import fr.ybo.opendata.rennes.modele.bus.PictoSize;
import fr.ybo.opendata.rennes.modele.bus.PointDeVente;
import fr.ybo.opendata.rennes.modele.equipements.Equipement;
import fr.ybo.opendata.rennes.modele.equipements.EquipementStatus;
import fr.ybo.opendata.rennes.modele.metros.MetroStation;
import fr.ybo.opendata.rennes.modele.metros.MetroStationStatus;
import fr.ybo.opendata.rennes.modele.velos.Station;
import fr.ybo.opendata.rennes.modele.velos.StationDistrict;
import fr.ybo.opendata.rennes.sax.GetAlertsHandler;
import fr.ybo.opendata.rennes.sax.GetEquipementsHandler;
import fr.ybo.opendata.rennes.sax.GetEquipementsStatusHandler;
import fr.ybo.opendata.rennes.sax.GetLinesHandler;
import fr.ybo.opendata.rennes.sax.GetMetroStationHandler;
import fr.ybo.opendata.rennes.sax.GetMetroStationsStatusHandler;
import fr.ybo.opendata.rennes.sax.GetParkRelaiHandler;
import fr.ybo.opendata.rennes.sax.GetPointDeVenteHandler;
import fr.ybo.opendata.rennes.sax.GetStationDistrictHandler;
import fr.ybo.opendata.rennes.sax.GetStationHandler;
import fr.ybo.opendata.rennes.sax.KeolisHandler;
import fr.ybo.opendata.rennes.util.Connecteur;
import fr.ybo.opendata.rennes.util.HttpConnecteur;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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
     * Commande pour récupérer les stations.
     */
    private static final String COMMANDE_STATIONS_DISTRICT = "getbikedistricts";
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
     * Commande pour récupérer les pictos.
     */
    private static final String COMMANDE_LINES = "getlines";
    /**
     * Commande pour récupérer les equipements.
     */
    private static final String COMMANDE_EQUIPMENTS = "getequipments";
    /**
     * Commande pour récupérer les status des equipements.
     */
    private static final String COMMANDE_EQUIPMENTS_STATUS = "getequipmentsstatus";
    /**
     * Commande pour récupérer les stations de métro.
     */
    private static final String COMMANDE_METRO_STATION = "getmetrostations";
    /**
     * Commande pour récupérer les status des stations de métro.
     */
    private static final String COMMANDE_METRO_STATION_STATUS = "getmetrostationsstatus";


    private Connecteur connecteur;

    protected void setConnecteur(Connecteur connecteur) {
        this.connecteur = connecteur;
    }

    /**
     * Constructeur.
     *
     * @param apiKey clé fournie par le site.
     */
    public Keolis(String apiKey) {
        key = apiKey;
        connecteur = new HttpConnecteur();
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
    private <T> List<T> appelKeolis(String url, KeolisHandler<T> handler) throws KeolisReseauException {
        Answer<T> answer;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            InputStream inputStream = connecteur.openInputStream(url);
            try {
                parser.parse(inputStream, handler);
                answer = handler.getAnswer();
            } finally {
                try {
                    inputStream.close();
                } catch (Exception ignore) {
                }
            }
        } catch (IOException ioException) {
            throw new KeolisReseauException(ioException);
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
     * Appel aux API Keolis pour récupérer les districts des stations..
     *
     * @return la listes des districts.
     * @throws KeolisReseauException pour toutes erreurs réseaux.
     */
    public List<StationDistrict> getStationsDistrict() throws KeolisReseauException {
        return appelKeolis(getUrl(COMMANDE_STATIONS_DISTRICT), new GetStationDistrictHandler());
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
     * @param size taille des pictos demandé.
     * @return la liste des pictos.
     * @throws KeolisReseauException pour toutes erreurs réseaux.
     */
    public List<LignePicto> getLigne(PictoSize size) throws KeolisReseauException {
        return appelKeolis(getUrl(COMMANDE_LINES, new ParametreUrl("size", Integer.toString(size.getSize()))), new GetLinesHandler());
    }

    /**
     * @return la liste des equipements.
     * @throws KeolisReseauException pour toutes erreurs réseaux.
     */
    public List<Equipement> getEquipements() throws KeolisReseauException {
        return appelKeolis(getUrl(COMMANDE_EQUIPMENTS), new GetEquipementsHandler());
    }

    /**
     * @param station station pour laquelle on veux récupérer les équipements
     * @return la liste des equipements de la station.
     * @throws KeolisReseauException pour toutes erreurs réseaux.
     */
    public List<Equipement> getEquipements(String station) throws KeolisReseauException {
        return appelKeolis(getUrl(COMMANDE_EQUIPMENTS, new ParametreUrl("mode", "station"), new ParametreUrl("station", station)), new GetEquipementsHandler());
    }

    /**
     * @return la liste des status des equipements.
     * @throws KeolisReseauException pour toutes erreurs réseaux.
     */
    public List<EquipementStatus> getEquipementsStatus() throws KeolisReseauException {
        return appelKeolis(getUrl(COMMANDE_EQUIPMENTS_STATUS), new GetEquipementsStatusHandler());
    }

    /**
     * @param id identifiant de l'équipements.
     * @return le status des equipements.
     * @throws KeolisReseauException pour toutes erreurs réseaux.
     */
    public EquipementStatus getEquipementsStatus(String id) throws KeolisReseauException {
        List<EquipementStatus> equipementStatuses = appelKeolis(getUrl(COMMANDE_EQUIPMENTS_STATUS,
                new ParametreUrl("mode", "id"),
                new ParametreUrl("id", id)), new GetEquipementsStatusHandler());
        if (equipementStatuses.isEmpty()) {
            return null;
        }
        return equipementStatuses.get(0);
    }

    /**
     * @param station la sation.
     * @return la liste des status des equipements de la station.
     * @throws KeolisReseauException pour toutes erreurs réseaux.
     */
    public List<EquipementStatus> getEquipementsStatusByStation(String station) throws KeolisReseauException {
        return appelKeolis(getUrl(COMMANDE_EQUIPMENTS_STATUS,
                new ParametreUrl("mode", "station"),
                new ParametreUrl("station", station)), new GetEquipementsStatusHandler());
    }

    /**
     * @return la liste des stations de métro.
     * @throws KeolisReseauException pour toutes erreurs réseaux.
     */
    public List<MetroStation> getMetroStations() throws KeolisReseauException {
        return appelKeolis(getUrl(COMMANDE_METRO_STATION), new GetMetroStationHandler());
    }

    /**
     * @return la liste des status des stations de métro.
     * @throws KeolisReseauException pour toutes erreurs réseaux.
     */
    public List<MetroStationStatus> getMetroStationsStatus() throws KeolisReseauException {
        return appelKeolis(getUrl(COMMANDE_METRO_STATION_STATUS), new GetMetroStationsStatusHandler());
    }

    /**
     * @param id identifiant de la station de metro.
     * @return le status de la station de métro.
     * @throws KeolisReseauException pour toutes erreurs réseaux.
     */
    public MetroStationStatus getMetroStationsStatus(String id) throws KeolisReseauException {
        List<MetroStationStatus> stationStatuses = appelKeolis(getUrl(COMMANDE_METRO_STATION_STATUS,
                new ParametreUrl("mode", "station"),
                new ParametreUrl("station", id)), new GetMetroStationsStatusHandler());
        if (stationStatuses.isEmpty()) {
            return null;
        }
        return stationStatuses.get(0);
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
