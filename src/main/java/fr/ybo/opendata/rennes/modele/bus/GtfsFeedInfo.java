package fr.ybo.opendata.rennes.modele.bus;

import fr.ybo.moteurcsv.annotation.BaliseCsv;
import fr.ybo.moteurcsv.annotation.FichierCsv;

@FichierCsv( "feed_info.txt")
public class GtfsFeedInfo {
    @BaliseCsv( "feed_publisher_name")
    private String publisherName;
    @BaliseCsv( "feed_publisher_url")
    private String publisherUrl;
    @BaliseCsv( "feed_lang" )
    private String lang;
    @BaliseCsv( "feed_start_date" )
    private String startDate;
    @BaliseCsv( "feed_end_date" )
    private String endDate;
    @BaliseCsv( "feed_version" )
    private String version;

    public String getPublisherName() {
        return publisherName;
    }

    public String getPublisherUrl() {
        return publisherUrl;
    }

    public String getLang() {
        return lang;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getVersion() {
        return version;
    }
}
