package fr.ybo.opendata.rennes.util;

import fr.ybo.opendata.rennes.KeolisReseauException;

import java.io.InputStream;

public interface Connecteur {

    InputStream openInputStream(String url) throws KeolisReseauException;

}
