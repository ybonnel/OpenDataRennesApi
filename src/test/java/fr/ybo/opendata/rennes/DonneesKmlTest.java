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
import fr.ybo.opendata.rennes.modele.votes.CentreVote;
import fr.ybo.opendata.rennes.modele.votes.Nature;
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

    /**
     * Nombre d'arceaux.
     */
    private static final int NB_CENTRES = 29;
    /**
     * Numéro du centre de vote.
     */
    private static final int NUM_CENTRE = 31;
    /**
     * Nombre de cantons.
     */
    private static final int NB_CANTONS = 9;
    /**
     * Nombre de bureaux.
     */
    private static final int NB_BUREAUX = 4;
    /**
     * Numéro de premier bureau.
     */
    private static final int BUREAU_1 = 311;
    /**
     * Numéro de second bureau.
     */
    private static final int BUREAU_2 = 312;
    /**
     * Numéro de troisième bureau.
     */
    private static final int BUREAU_3 = 313;
    /**
     * Numéro de premier bureau.
     */
    private static final int BUREAU_4 = 314;

    /**
     * Test de la méthode {@link DonneesKml#getCentres()}.
     *
     * @throws KeolisReseauException problème réseau.
     */
    @Test
    public void testGetCentres() throws KeolisReseauException {
        donneesKml.setConnecteur(new FileConnecteur("/centres_vote_kml_wgs84.zip"));

        List<CentreVote> centres = donneesKml.getCentres();
        assertEquals(NB_CENTRES, centres.size());
        CentreVote centre = centres.get(0);
        assertEquals("kml_1", centre.getId());
        assertEquals(NUM_CENTRE, centre.getNumero());
        assertEquals("Ecole Elémentaire Albert de Mun", centre.getNom());
        assertEquals(Nature.ELEMENTAIRE, centre.getNature());
        assertEquals(NB_CANTONS, centre.getNbCantons());
        assertEquals(NB_BUREAUX, centre.getBureaux().size());
        int count = 0;
        assertEquals(BUREAU_1, centre.getBureaux().get(count++).intValue());
        assertEquals(BUREAU_2, centre.getBureaux().get(count++).intValue());
        assertEquals(BUREAU_3, centre.getBureaux().get(count++).intValue());
        assertEquals(BUREAU_4, centre.getBureaux().get(count).intValue());
    }

    /**
     * Test de la méthode {@link DonneesKml#getCentres()}.
     *
     * @throws KeolisReseauException problème réseau.
     */
    @Test
    public void testGetCentresHttp() throws KeolisReseauException {
        donneesKml.setConnecteur(new HttpConnecteur());
        List<CentreVote> centres = donneesKml.getCentres();
        assertFalse(centres.isEmpty());
    }
}
