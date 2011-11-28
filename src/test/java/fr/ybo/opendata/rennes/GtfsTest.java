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
import fr.ybo.opendata.rennes.modele.gtfs.GtfsFeedInfo;
import fr.ybo.opendata.rennes.modele.gtfs.GtfsFile;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Test de la classe {@link Gtfs}.
 */
public class GtfsTest {

    /**
     * Instance à tester.
     */
    private Gtfs gtfs;

    /**
     * Création de l'instance à tester.
     */
    @Before
    public void setup() {
        gtfs = new Gtfs();
    }

    /**
     * Test de la méthode {@link Gtfs#getUpdates()}.
     *
     * @throws KeolisReseauException problème réseaux.
     */
    @Test
    public void testGetUpdates() throws KeolisReseauException {
        gtfs.setConnecteur(new FileConnecteur("/gtfs.html"));
        List<GtfsFile> files = gtfs.getUpdates();

        assertEquals(2, files.size());
        assertEquals("05092011", new SimpleDateFormat("ddMMyyyy").format(files.get(0).getDate()));
        assertEquals("http://data.keolis-rennes.com/fileadmin/OpenDataFiles/GTFS/GTFS-20110905.zip",
                files.get(0).getUrl());
        assertEquals("14112011", new SimpleDateFormat("ddMMyyyy").format(files.get(1).getDate()));
        assertEquals("http://data.keolis-rennes.com/fileadmin/OpenDataFiles/GTFS/GTFS-20111114.zip",
                files.get(1).getUrl());
    }

    /**
     * Test de la méthode {@link Gtfs#getFeedInfo(GtfsFile)}.
     *
     * @throws KeolisReseauException problème réseaux.
     */
    @Test
    public void testGetFeedInfos() throws KeolisReseauException {
        gtfs.setConnecteur(new FileConnecteur("/GTFS.zip"));
        GtfsFile file = new GtfsFile();
        file.setUrl("urlBidon");
        List<GtfsFeedInfo> infos = gtfs.getFeedInfo(file);
        assertEquals(1, infos.size());
        GtfsFeedInfo info = infos.get(0);
        assertEquals("Keolis Rennes", info.getPublisherName());
        assertEquals("http://data.keolis-rennes.com/fr/les-donnees/donnees-telechargeables.html",
                info.getPublisherUrl());
        assertEquals("FR", info.getLang());
        assertEquals("20111114", new SimpleDateFormat("yyyyMMdd").format(info.getStartDate()));
        assertEquals("20111218", new SimpleDateFormat("yyyyMMdd").format(info.getEndDate()));
        assertEquals("Horaires d'hiver 2011 - Modifications du 14/11/2011.", info.getVersion());
    }
}
