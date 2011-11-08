package fr.ybo.opendata.rennes.util;


import fr.ybo.opendata.rennes.KeolisReseauException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class HttpConnecteur implements Connecteur {
    @Override
    public InputStream openInputStream(String url) throws KeolisReseauException {
        try {
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            return connection.getInputStream();
        } catch (IOException socketException) {
            throw new KeolisReseauException(socketException);
        }
    }
}
