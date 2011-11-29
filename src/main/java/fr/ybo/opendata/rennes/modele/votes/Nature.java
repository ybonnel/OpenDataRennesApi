package fr.ybo.opendata.rennes.modele.votes;

/**
 * Nature d'un bureaux de vote.
 */
public enum Nature {
    /**
     * Ecole élémentaire.
     */
    ELEMENTAIRE("ELEMENTAIRE"),
    /**
     * Groupe scolaire.
     */
    GROUPE_SCOLAIRE("GROUPE SCOLAIRE"),
    /**
     * Centre culturel.
     */
    CENTRE_CULTUREL("CENTRE CULTUREL"),
    /**
     * Collège.
     */
    COLLEGE("COLLEGE"),
    /**
     * Gymnase moyen.
     */
    GYMNASE_MOYEN("GYMNASE MOYEN");

    /**
     * Valeur dans le XML.
     */
    private String value;

    /**
     * Constructeur.
     * @param value {@link Nature#value}.
     */
    Nature(String value) {
        this.value = value;
    }

    /**
     * Renvoie l'enum en fonction de la valeur dans le xml.
     * @param value valeur dans le xml.
     * @return l'enum.
     */
    public static Nature fromValue(String value) {
        for (Nature nature : values()) {
            if (nature.value.equals(value)) {
                return nature;
            }
        }
        throw new IllegalArgumentException("La valeur " + value + " est inconnue");
    }
}
