package fr.ybo.opendata.rennes;

import fr.ybo.opendata.rennes.modele.bus.GtfsFeedInfo;
import fr.ybo.opendata.rennes.modele.bus.GtfsFile;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class GtfsTest {

    private Gtfs gtfs;

    @Before
    public void setup() {
         gtfs = new Gtfs();
    }

    @Test
    public void testGetUpdates() {
        gtfs.setConnecteur(new FileConnecteur("/gtfs.html"));
        List<GtfsFile> files = gtfs.getUpdates();

        assertEquals( 2, files.size());
        assertEquals( "05092011", new SimpleDateFormat("ddMMyyyy").format(files.get(0).getDate()));
        assertEquals("http://data.keolis-rennes.com/fileadmin/OpenDataFiles/GTFS/GTFS-20110905.zip", files.get(0).getUrl());
        assertEquals( "14112011", new SimpleDateFormat("ddMMyyyy").format(files.get(1).getDate()));
        assertEquals("http://data.keolis-rennes.com/fileadmin/OpenDataFiles/GTFS/GTFS-20111114.zip", files.get(1).getUrl());
    }

    @Test
    public void testGetFeedInfos() throws KeolisReseauException {
        gtfs.setConnecteur(new FileConnecteur("/GTFS.zip"));
        GtfsFile file = new GtfsFile();
        file.setUrl("urlBidon");
        List<GtfsFeedInfo> infos = gtfs.getFeedInfo(file);
        assertEquals(1, infos.size());
        GtfsFeedInfo info = infos.get(0);
        assertEquals("Keolis Rennes", info.getPublisherName());
        assertEquals("http://data.keolis-rennes.com/fr/les-donnees/donnees-telechargeables.html", info.getPublisherUrl());
        assertEquals("FR", info.getLang());
        assertEquals("20111114", info.getStartDate());
        assertEquals("20111218", info.getEndDate());
        assertEquals("Horaires d'hiver 2011 - Modifications du 14/11/2011.", info.getVersion());
    }
}