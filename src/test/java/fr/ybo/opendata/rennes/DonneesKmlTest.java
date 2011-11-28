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
import fr.ybo.opendata.rennes.util.HttpConnecteur;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * Test de la classe {@link DonneesKml}.
 */
public class DonneesKmlTest {

    /**
     * Class à tester.
     */
    private DonneesKml donneesKml;

    /**
     * Construction d'une instance de la class à tester.
     */
    @Before
    public void setup() {
        donneesKml = new DonneesKml();
    }

    /**
     * Nombre d'arceaux.
     */
    private static final int NB_ARCEAUX = 207;

    /**
     * Latitude de l'arceau.
     */
    private static final double LATITUDE = 48.1123133477146;
    /**
     * Longitude de l'arceau.
     */
    private static final double LONGITUDE = -1.67099959723433;

    /**
     * Test de la méthode {@link DonneesKml#getArceaux()}.
     *
     * @throws KeolisReseauException problème réseau.
     */
    @Test
    public void testGetArceaux() throws KeolisReseauException {
        donneesKml.setConnecteur(new FileConnecteur("/velo_arceaux_kml_wgs84.zip"));

        List<Arceau> arceaux = donneesKml.getArceaux();
        assertEquals(NB_ARCEAUX, arceaux.size());
        Arceau arceau = arceaux.get(0);
        assertEquals("kml_1", arceau.getId());
        assertEquals(LATITUDE, arceau.getLatitude());
        assertEquals(LONGITUDE, arceau.getLongitude());
    }

    /**
     * Test de la méthode {@link DonneesKml#getArceaux()}.
     *
     * @throws KeolisReseauException problème réseau.
     */
    @Test
    public void testGetArceauxHttp() throws KeolisReseauException {
        donneesKml.setConnecteur(new HttpConnecteur());
        List<Arceau> arceaux = donneesKml.getArceaux();
        assertFalse(arceaux.isEmpty());
    }
}
