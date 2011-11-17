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

package fr.ybo.opendata.rennes.modele.equipements;

/**
 * Classe repr�sentant un �quipement.
 */
public class Equipement {
    /**
     * Identifiant de l'�quipement.
     */
    private String id;
    /**
     * Station de m�tro associ�e � l'�quipement.
     */
    private String station;
    /**
     * Type de l'�quipement.
     */
    private TypeEquipement type;
    /**
     * Etage de d�part.
     */
    private int etageDepart;
    /**
     * Etage d'arriv�e.
     */
    private int etageArrivee;
    /**
     * Plateforme?
     */
    private int plateform;
    /**
     * Derni�re mise � jour.
     */
    private String lastUpdate;

    /**
     * @return {@link Equipement#id}
     */
    public String getId() {
        return id;
    }

    /**
     * @param id {@link Equipement#id}
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return {@link Equipement#station}
     */
    public String getStation() {
        return station;
    }

    /**
     * @param station {@link Equipement#station}
     */
    public void setStation(String station) {
        this.station = station;
    }

    /**
     * @return {@link Equipement#type}
     */
    public TypeEquipement getType() {
        return type;
    }

    /**
     * @param type {@link Equipement#type}
     */
    public void setType(TypeEquipement type) {
        this.type = type;
    }

    /**
     * @return {@link Equipement#etageDepart}
     */
    public int getEtageDepart() {
        return etageDepart;
    }

    /**
     * @param etageDepart {@link Equipement#etageDepart}
     */
    public void setEtageDepart(int etageDepart) {
        this.etageDepart = etageDepart;
    }

    /**
     * @return {@link Equipement#etageArrivee}
     */
    public int getEtageArrivee() {
        return etageArrivee;
    }

    /**
     * @param etageArrivee {@link Equipement#etageArrivee}
     */
    public void setEtageArrivee(int etageArrivee) {
        this.etageArrivee = etageArrivee;
    }

    /**
     * @return {@link Equipement#plateform}
     */
    public int getPlateform() {
        return plateform;
    }

    /**
     * @param plateform {@link Equipement#plateform}
     */
    public void setPlateform(int plateform) {
        this.plateform = plateform;
    }

    /**
     * @return {@link Equipement#lastUpdate}
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate {@link Equipement#lastUpdate}
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
