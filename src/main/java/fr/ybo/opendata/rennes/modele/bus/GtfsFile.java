package fr.ybo.opendata.rennes.modele.bus;

import java.util.Date;

public class GtfsFile {

    private Date date;
    private String url;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
