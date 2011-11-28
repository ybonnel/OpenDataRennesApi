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

import fr.ybo.opendata.rennes.exceptions.KeolisReseauException;
import fr.ybo.opendata.rennes.modele.velos.Arceau;
import fr.ybo.opendata.rennes.sax.GenericKmlHandler;
import fr.ybo.opendata.rennes.sax.KmlHandler;
import fr.ybo.opendata.rennes.util.Connecteur;
import fr.ybo.opendata.rennes.util.HttpConnecteur;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Permet de récupérer les infos sur les arceaux vélos (position).
 */
public class DonneesKml {

    /**
     * Url du Kml.
     */
    private static final String URL_KML = "http://www.data.rennes-metropole.fr/fileadmin/user_upload/data/"
            + "data_sig/deplacement/velo_arceaux/velo_arceaux_kml_wgs84.zip";

    /**
     * Nom du fichier kml.
     */
    private static final String KML_NAME = "velo_arceaux.kml";

    /**
     * Constructeur.
     */
    public DonneesKml() {
        connecteur = new HttpConnecteur();
    }

    /**
     * Connecteur.
     */
    private Connecteur connecteur;

    /**
     * @param connecteur {@link DonneesKml#connecteur}.
     */
    protected void setConnecteur(Connecteur connecteur) {
        this.connecteur = connecteur;
    }

    /**
     * M�thode permettant de récupérer les arceaux de vélos.
     *
     * @return la liste des arceaux.
     * @throws KeolisReseauException problème réseaux.
     */
    public List<Arceau> getArceaux() throws KeolisReseauException {
        return appelKml(URL_KML, KML_NAME, new GenericKmlHandler<Arceau>(Arceau.class));
    }

    /**
     * M�thode r�alisant la transformation d'un kml en objets.
     * @param url url.
     * @param kmlName nom du fichier kml.
     * @param handler handler.
     * @param <T> objet représentant le kml.
     * @return la liste des objets associés au kml.
     * @throws KeolisReseauException problème réseaux.
     */
    private <T> List<T> appelKml(String url, String kmlName, KmlHandler<T> handler) throws KeolisReseauException {
        ZipInputStream kmlZip = new ZipInputStream(connecteur.openInputStream(url));
        try {
            ZipEntry entry = kmlZip.getNextEntry();
            while (entry != null) {
                if (entry.getName().endsWith(kmlName)) {

                    SAXParserFactory factory = SAXParserFactory.newInstance();
                    SAXParser parser = factory.newSAXParser();
                    parser.parse(kmlZip, handler);
                    return handler.getObjets();
                }
                entry = kmlZip.getNextEntry();
            }
        } catch (ParserConfigurationException confException) {
            throw new KeolisReseauException(confException);
        } catch (SAXException saxException) {
            throw new KeolisReseauException(saxException);
        } catch (IOException ioException) {
            throw new KeolisReseauException(ioException);
        } finally {
            try {
                kmlZip.close();
            } catch (IOException ignore) {
                // On ignore l'exception.
            }
        }
        throw new KeolisReseauException("Pas de kml " + kmlName + " trouvé dans le zip");
    }


}
