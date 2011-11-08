/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Contributors:
 *     ybonnel - initial API and implementation
 */
package fr.ybo.opendata.rennes.modele.bus;

import java.io.Serializable;

/**
 * Un point de vente.
 *
 * @author ybonnel
 */
@SuppressWarnings("serial")
public class PointDeVente implements Serializable {
    /**
     * Nom du point de vente.
     */
    private String name;
    /**
     * Type du point de vente.
     */
    private String type;
    /**
     * Adresse du point de vente.
     */
    private String adresse;
    /**
     * Code postal du point de vente.
     */
    private String codePostal;
    /**
     * Ville du point de vente.
     */
    private String ville;
    /**
     * District du point de vente.
     */
    private String district;
    /**
     * Téléphone du point de vente.
     */
    private String telephone;
    /**
     * Schedule du point de vente.
     */
    private String schedule;
    /**
     * Latitude du point de vente.
     */
    private double latitude;
    /**
     * Longitude du point de vente.
     */
    private double longitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
