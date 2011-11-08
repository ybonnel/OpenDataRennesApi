package fr.ybo.opendata.rennes;

import fr.ybo.opendata.rennes.util.Connecteur;

import java.io.InputStream;

public class FileConnecteur implements Connecteur {

    private String file;

    public FileConnecteur(String file) {
        this.file = file;
    }

    @Override
    public InputStream openInputStream(String url) throws KeolisReseauException {
        return FileConnecteur.class.getResourceAsStream(file);
    }
}
