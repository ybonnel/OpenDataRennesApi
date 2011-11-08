package fr.ybo.opendata.rennes;


import fr.ybo.opendata.rennes.modele.bus.Alert;
import org.junit.Before;
import org.junit.Test;

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

}
