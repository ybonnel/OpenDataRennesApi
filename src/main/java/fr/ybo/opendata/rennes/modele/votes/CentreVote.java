package fr.ybo.opendata.rennes.modele.votes;

import fr.ybo.opendata.rennes.sax.BaliseData;
import fr.ybo.opendata.rennes.sax.BaliseSimpleData;
import fr.ybo.opendata.rennes.sax.BaliseType;
import fr.ybo.opendata.rennes.sax.BaliseXml;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un centre de vote.
 */
@BaliseData("Placemark")
public class CentreVote {
    /**
     * Identifiant.
     */
    private String id;
    /**
     * Numéro.
     */
    private int numero;
    /**
     * Nom.
     */
    private String nom;
    /**
     * Nature.
     */
    private Nature nature;
    /**
     * Nombre de cantons.
     */
    private int nbCantons;
    /**
     * Liste des bureaux.
     */
    private List<Integer> bureaux;
    /**
     * Latitude.
     */
    private double latitude;
    /**
     * Longitude.
     */
    private double longitude;

    /**
     * @return {@link CentreVote#id}.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id {@link CentreVote#id}.
     */
    @BaliseXml(name = "name")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return {@link CentreVote#numero}.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero {@link CentreVote#numero}.
     */
    @BaliseSimpleData(name = "NUM", type = BaliseType.INTEGER)
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return {@link CentreVote#nom}.
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom {@link CentreVote#nom}.
     */
    @BaliseSimpleData(name = "NOM")
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return {@link CentreVote#nature}.
     */
    public Nature getNature() {
        return nature;
    }

    /**
     * @param nature {@link CentreVote#nature}.
     */
    @BaliseSimpleData(name = "NATURE")
    public void setNature(String nature) {
        this.nature = Nature.fromValue(nature);
    }

    /**
     * @return {@link CentreVote#nbCantons}.
     */
    public int getNbCantons() {
        return nbCantons;
    }

    /**
     * @param nbCantons {@link CentreVote#nbCantons}.
     */
    @BaliseSimpleData(name = "NCANTON", type = BaliseType.INTEGER)
    public void setNbCantons(int nbCantons) {
        this.nbCantons = nbCantons;
    }

    /**
     * @return {@link CentreVote#bureaux}.
     */
    public List<Integer> getBureaux() {
        if (bureaux == null) {
            bureaux = new ArrayList<Integer>();
        }
        return bureaux;
    }

    /**
     * @param bureaux {@link CentreVote#bureaux}.
     */
    @BaliseSimpleData(name = "BURX_LISTE")
    public void setBureaux(String bureaux) {
        String[] champs = bureaux.replaceAll(" ", "").split(",");
        for (String champ : champs) {
            getBureaux().add(Integer.parseInt(champ));
        }
    }

    /**
     * @return {@link CentreVote#latitude}.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @return {@link CentreVote#longitude}.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Permet de mettre à jour les coordonnées (latitude et longitude).
     *
     * @param coordonnees les coordonnées.
     */
    @BaliseXml(name = "coordinates")
    public void setCoordonnees(String coordonnees) {
        String[] champs = coordonnees.split(",");
        longitude = Double.parseDouble(champs[0]);
        latitude = Double.parseDouble(champs[1]);
    }
}
