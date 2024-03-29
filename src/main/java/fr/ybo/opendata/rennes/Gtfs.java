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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import fr.ybo.moteurcsv.MoteurCsv;
import fr.ybo.opendata.rennes.exceptions.KeolisReseauException;
import fr.ybo.opendata.rennes.modele.gtfs.GtfsFeedInfo;
import fr.ybo.opendata.rennes.modele.gtfs.GtfsFile;
import fr.ybo.opendata.rennes.util.Connecteur;
import fr.ybo.opendata.rennes.util.HttpConnecteur;

/**
 * Point d'entrée pour la gestion des fichiers GTFS.
 */
public class Gtfs {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Gtfs.class.getSimpleName());

    /**
     * Format de la date dans le nom du fichier.
     */
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");

    /**
     * Taille du format de la date.
     */
    private static final int TRAILLE_FORMAT_DATE = 8;

    /**
     * Chemin de fichier GTFS.
     */
    private static final String URL_RELATIVE = "fileadmin/OpenDataFiles/GTFS/GTFS-";
    /**
     * Url du site Keolis.
     */
    private static final String URL_KEOLIS = "http://data.keolis-rennes.com/";
    /**
     * Url de base pour le fichier.
     */
    private static final String BASE_URL = URL_KEOLIS + URL_RELATIVE;
    /**
     * URL de la page des données téléchargeables.
     */
    private static final String URL_DONNEES_TELECHARGEABLES = URL_KEOLIS
            + "fr/les-donnees/donnees-telechargeables.html";
    /**
     * Extension du fichier.
     */
    private static final String EXTENSION_URL = ".zip";

    /**
     * Connecteur.
     */
    private Connecteur connecteur;

    /**
     * Constructeur.
     */
    public Gtfs() {
        connecteur = new HttpConnecteur();
    }

    /**
     * @param connecteur {@link Gtfs#connecteur}.
     */
    protected void setConnecteur(Connecteur connecteur) {
        this.connecteur = connecteur;
    }

    /**
     * Récupère la liste gtfs disponible.
     *
     * @return liste des fichiers GTFS fournis.
     * @throws KeolisReseauException erreur réseau.
     */
    public List<GtfsFile> getUpdates() throws KeolisReseauException {
        List<GtfsFile> files = new ArrayList<GtfsFile>();
        try {
            BufferedReader bufReader =
                    new BufferedReader(new InputStreamReader(connecteur.openInputStream(URL_DONNEES_TELECHARGEABLES)));
            try {
                String ligne = bufReader.readLine();
                while (ligne != null) {
					for (String champ : ligne.split("<br />")) {
						traiterLigne(champ, files);
					}
                    ligne = bufReader.readLine();
                }
            } finally {
                bufReader.close();
            }
        } catch (Exception exception) {
            throw new KeolisReseauException(exception);
        }
        return files;
    }

    /**
     * Traite une ligne du fichier html.
     * @param ligne une ligne.
     * @param files fichiers gtfs.
     * @throws ParseException problème dans le parsing de la date.
     */
    private void traiterLigne(String ligne, List<GtfsFile> files) throws ParseException {
        if (ligne.contains(URL_RELATIVE)) {
            String chaineDate = ligne.substring(
                    ligne.indexOf(URL_RELATIVE) + URL_RELATIVE.length(),
                    ligne.indexOf(URL_RELATIVE) + URL_RELATIVE.length() + TRAILLE_FORMAT_DATE);
            GtfsFile file = new GtfsFile();
            file.setDate(SDF.parse(chaineDate));
            file.setUrl(BASE_URL + chaineDate + EXTENSION_URL);
            files.add(file);
        }
    }

    /**
     * Permet de récupérer les infos sur un fichier GTFS.
     *
     * @param file fichier GTFS.
     * @return listes des infos (généralement une seule).
     * @throws KeolisReseauException erreur réseau.
     */
    public List<GtfsFeedInfo> getFeedInfo(GtfsFile file) throws KeolisReseauException {
        List<Class<?>> classesCsv = new ArrayList<Class<?>>();
        List<GtfsFeedInfo> infos = new ArrayList<GtfsFeedInfo>();
        classesCsv.add(GtfsFeedInfo.class);
        MoteurCsv moteurCsv = new MoteurCsv(classesCsv);
        ZipInputStream zipInputStream = new ZipInputStream(connecteur.openInputStream(file.getUrl()));
        try {
            ZipEntry entry = zipInputStream.getNextEntry();
            while (entry != null) {
                if (moteurCsv.getClassByFileName(entry.getName()) != null) {
                    infos.addAll(moteurCsv.parseInputStream(zipInputStream, GtfsFeedInfo.class));
                    break;
                }
                entry = zipInputStream.getNextEntry();
            }
        } catch (IOException ioException) {
            throw new KeolisReseauException(ioException);
        } finally {
            try {
                zipInputStream.close();
            } catch (Exception exception) {
                LOGGER.warning(exception.getMessage());
            }
        }
        return infos;
    }

}
