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
import fr.ybo.opendata.rennes.modele.bus.Alert;
import fr.ybo.opendata.rennes.modele.bus.LignePicto;
import fr.ybo.opendata.rennes.modele.bus.PictoSize;
import fr.ybo.opendata.rennes.modele.equipements.Equipement;
import fr.ybo.opendata.rennes.modele.equipements.EquipementStatus;
import fr.ybo.opendata.rennes.modele.equipements.TypeEquipement;
import fr.ybo.opendata.rennes.modele.metros.MetroStation;
import fr.ybo.opendata.rennes.modele.metros.MetroStationStatus;
import fr.ybo.opendata.rennes.modele.parkrelais.ParkRelai;
import fr.ybo.opendata.rennes.modele.parkrelais.StateParkRelai;
import fr.ybo.opendata.rennes.modele.pointsdevente.PointDeVente;
import fr.ybo.opendata.rennes.modele.velos.Station;
import fr.ybo.opendata.rennes.modele.velos.StationDistrict;
import fr.ybo.opendata.rennes.modele.villes.Quartier;
import fr.ybo.opendata.rennes.modele.villes.Ville;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.*;

public class KeolisTest {

    private Keolis keolis;

    @Before
    public void setup() {
        keolis = new Keolis("key");
    }

    @Test
    public void testGetAlerts() throws KeolisReseauException {
        keolis.setConnecteur(new FileConnecteur("/getAlert.xml"));

        List<Alert> alerts = keolis.getAlerts();
        assertEquals(2, alerts.size());
        Alert alert1 = alerts.get(0);
        assertEquals("Marché À Bourgbarré", alert1.getTitleFormate());
        assertEquals("2010-09-27T00:00:00+02:00", alert1.getStarttime());
        assertEquals("2012-07-27T00:00:00+02:00", alert1.getEndtime());
        assertEquals(1, alert1.getLines().size());
        assertEquals("74", alert1.getLines().get(0));
        assertFalse(alert1.isMajordisturbance());
        assertNotNull(alert1.getDetail());
        Alert alert2 = alerts.get(1);
        assertEquals(2, alert2.getLines().size());
        assertTrue(alert2.isMajordisturbance());
    }

    @Test
    public void testGetStationByNumbers() throws KeolisReseauException {
        keolis.setConnecteur(new FileConnecteur("/getStations.xml"));

        List<Station> stations = keolis.getStationByNumbers(Arrays.asList("75", "65", "66"));

        assertEquals(1, stations.size());
        Station station1 = stations.get(0);
        assertEquals("75", station1.getNumber());
        assertEquals("ZAC SAINT SULPICE", station1.getName());
        assertEquals("RUE DE FOUGÈRES", station1.getAdresse());
        assertTrue(station1.isState());
        assertEquals(48.1321, station1.getLatitude());
        assertEquals(-1.63528, station1.getLongitude());
        assertEquals(14, station1.getSlotsavailable());
        assertEquals(16, station1.getBikesavailable());
        assertFalse(station1.isPos());
        assertEquals("Maurepas - Patton", station1.getDistrict());
        assertEquals("2011-11-09T19:59:06+01:00", station1.getLastupdate());
    }

    @Test
    public void testGetStations() throws KeolisReseauException {
        keolis.setConnecteur(new FileConnecteur("/getStations.xml"));

        List<Station> stations = keolis.getStations();

        assertEquals(2, stations.size());
        Station station1 = stations.get(0);
        assertEquals("75", station1.getNumber());
        assertEquals("ZAC SAINT SULPICE", station1.getName());
        assertEquals("RUE DE FOUGÈRES", station1.getAdresse());
        assertTrue(station1.isState());
        assertEquals(48.1321, station1.getLatitude());
        assertEquals(-1.63528, station1.getLongitude());
        assertEquals(14, station1.getSlotsavailable());
        assertEquals(16, station1.getBikesavailable());
        assertFalse(station1.isPos());
        assertEquals("Maurepas - Patton", station1.getDistrict());
        assertEquals("2011-11-09T19:59:06+01:00", station1.getLastupdate());

    }

    @Test
    public void testGetParkRelais() throws KeolisReseauException {
        keolis.setConnecteur(new FileConnecteur("/getParkRelais.xml"));

        List<ParkRelai> parkRelais = keolis.getParkRelais();

        assertEquals(4, parkRelais.size());
        ParkRelai parkRelai = parkRelais.get(0);
        assertEquals("Henri Fréville", parkRelai.getName());
        assertEquals(48.0875369773, parkRelai.getLatitude());
        assertEquals(-1.6745548715, parkRelai.getLongitude());
        assertEquals(317, parkRelai.getCarParkAvailable().intValue());
        assertEquals(406, parkRelai.getCarParkCapacity().intValue());
        assertEquals("2011-11-09T20:06:07+01:00", parkRelai.getLastupdate());
        assertEquals(StateParkRelai.OUVERT, parkRelai.getState());
        assertEquals(StateParkRelai.FERME, parkRelais.get(1).getState());
        assertEquals(StateParkRelai.COMPLET, parkRelais.get(2).getState());
        assertEquals(StateParkRelai.INDISPONIBLE, parkRelais.get(3).getState());
    }

    @Test
    public void testGetPointDeVente() throws KeolisReseauException {
        keolis.setConnecteur(new FileConnecteur("/getPos.xml"));

        List<PointDeVente> pointDeVentes = keolis.getPointDeVente();

        assertEquals(2, pointDeVentes.size());
        PointDeVente pointDeVente = pointDeVentes.get(0);
        assertEquals("Relais H / Gare SNCF", pointDeVente.getName());
        assertEquals("Tabac Presse", pointDeVente.getType());
        assertEquals("Place de la gare", pointDeVente.getAdresse());
        assertEquals("35000", pointDeVente.getCodePostal());
        assertEquals("RENNES", pointDeVente.getVille());
        assertEquals("Gares", pointDeVente.getDistrict());
        assertEquals("02 99 41 91 44", pointDeVente.getTelephone());
        assertEquals("", pointDeVente.getSchedule());
        assertEquals(48.1041574, pointDeVente.getLatitude());
        assertEquals(-1.6726879, pointDeVente.getLongitude());
    }

    @Test
    public void testGetStationsDistrict() throws KeolisReseauException {
        keolis.setConnecteur(new FileConnecteur("/getBikesDistrict.xml"));
        List<StationDistrict> districts = keolis.getStationsDistrict();
        assertEquals(2, districts.size());
        assertEquals("34", districts.get(0).getId());
        assertEquals("Sud-Gare", districts.get(0).getName());
        assertEquals("35", districts.get(1).getId());
        assertEquals("Francisco Ferrer-Vern-Poterie", districts.get(1).getName());
    }

    @Test
    public void testGetLignes() throws KeolisReseauException {
        keolis.setConnecteur(new FileConnecteur("/getLines.xml"));
        List<LignePicto> pictos = keolis.getLigne(PictoSize.TAILLE_100);
        assertEquals(2, pictos.size());
        assertEquals("1", pictos.get(0).getName());
        assertEquals("L1.png", pictos.get(0).getPicto());
        assertEquals("http://data.keolis-rennes.com/" +
                "fileadmin/documents/Picto_lignes/Pictos_lignes_100x100/L1.png", pictos.get(0).getPictoUrl());
        assertEquals("11", pictos.get(1).getName());
        assertEquals("L11.png", pictos.get(1).getPicto());
        assertEquals("http://data.keolis-rennes.com/" +
                "fileadmin/documents/Picto_lignes/Pictos_lignes_100x100/L11.png", pictos.get(1).getPictoUrl());
    }

    @Test
    public void testGetEquipements() throws KeolisReseauException {
        keolis.setConnecteur(new FileConnecteur("/getEquipments.xml"));
        List<Equipement> equipements = keolis.getEquipements();
        assertEquals(2, equipements.size());
        assertEquals("ASC_ANF_1", equipements.get(0).getId());
        assertEquals("ANF", equipements.get(0).getStation());
        assertEquals(TypeEquipement.ASCENSEUR, equipements.get(0).getType());
        assertEquals(-1, equipements.get(0).getEtageDepart());
        assertEquals(0, equipements.get(0).getEtageArrivee());
        assertEquals(1, equipements.get(0).getPlateform());
        assertEquals("2011-11-14 05:00:51", equipements.get(0).getLastUpdate());
        assertEquals(TypeEquipement.ESCALATOR, equipements.get(1).getType());

        assertEquals(2, keolis.getEquipements("ANF").size());
    }

    @Test
    public void testGetEquipmentsStatus() throws KeolisReseauException {
        keolis.setConnecteur(new FileConnecteur("/getEquipmentsStatus.xml"));
        List<EquipementStatus> status = keolis.getEquipementsStatus();
        assertEquals(2, status.size());
        assertEquals("ASC_ANF_1", status.get(0).getId());
        assertTrue(status.get(0).isOn());
        assertEquals("2011-11-14 05:00:51", status.get(0).getLastUpdate());
        assertFalse(status.get(1).isOn());
        assertEquals(2, keolis.getEquipementsStatusByStation("station").size());
        assertNotNull(keolis.getEquipementsStatus("id"));
    }

    @Test
    public void testGetMetroStations() throws KeolisReseauException {
        keolis.setConnecteur(new FileConnecteur("/getMetroStation.xml"));
        List<MetroStation> stations = keolis.getMetroStations();
        assertEquals(2, stations.size());
        assertEquals("ANF", stations.get(0).getId());
        assertEquals("Anatole France", stations.get(0).getName());
        assertEquals(48.11812000, stations.get(0).getLatitude());
        assertEquals(-1.687540000, stations.get(0).getLongitude());
        assertTrue(stations.get(0).hasPlatformDirection1());
        assertTrue(stations.get(0).hasPlatformDirection2());
        assertEquals(12, stations.get(0).getRankingPlatformDirection1().intValue());
        assertEquals(18, stations.get(0).getRankingPlatformDirection2().intValue());
        assertEquals(-1, stations.get(0).getNombreEtages());
        assertEquals("2011-11-15T21:38:03+01:00", stations.get(0).getLastupdate());
        assertFalse(stations.get(1).hasPlatformDirection1());
        assertFalse(stations.get(1).hasPlatformDirection2());
        assertNull(stations.get(1).getRankingPlatformDirection1());
        assertNull(stations.get(1).getRankingPlatformDirection2());
    }

    @Test
    public void testGetMetroStationsStatus() throws KeolisReseauException {
        keolis.setConnecteur(new FileConnecteur("/getMetroStatus.xml"));

        List<MetroStationStatus> statuses = keolis.getMetroStationsStatus();
        assertEquals(2, statuses.size());
        assertEquals("ANF", statuses.get(0).getId());
        assertTrue(statuses.get(0).isOn());
        assertEquals("2011-11-15T21:59:36+01:00", statuses.get(0).getLastUpdate());
        assertFalse(statuses.get(1).isOn());

        MetroStationStatus status = keolis.getMetroStationsStatus("ANF");
        assertNotNull(status);
        assertEquals("ANF", status.getId());
        assertTrue(status.isOn());
        assertEquals("2011-11-15T21:59:36+01:00", status.getLastUpdate());
    }

    @Test
    public void testGetVilles() throws KeolisReseauException {
        keolis.setConnecteur(new FileConnecteur("/getCities.xml"));

        List<Ville> villes = keolis.getVilles();
        assertEquals(2, villes.size());
        assertEquals("RENNES", villes.get(0).getName());
        assertEquals(26, villes.get(0).getNombreDistricts());
        assertEquals("1", villes.get(0).getId());
    }

    @Test
    public void testGetQuartiers() throws KeolisReseauException {
        keolis.setConnecteur(new FileConnecteur("/getCityDistricts.xml"));

        List<Quartier> quartiers = keolis.getQuartier("1");
        assertEquals(2, quartiers.size());
        assertEquals("Beauregard", quartiers.get(0).getName());
        assertEquals("1", quartiers.get(0).getId());
    }

}
