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
package fr.ybo.opendata.rennes.modele.pointsdevente;

import fr.ybo.opendata.rennes.sax.BaliseData;
import fr.ybo.opendata.rennes.sax.BaliseType;
import fr.ybo.opendata.rennes.sax.BaliseXml;

import java.io.Serializable;

/**
 * Un point de vente.
 *
 * @author ybonnel
 */
@SuppressWarnings("serial")
@BaliseData("pos")
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

    /**
     * @return {@link PointDeVente#name}.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name {@link PointDeVente#name}.
     */
    @BaliseXml(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return {@link PointDeVente#type}.
     */
    public String getType() {
        return type;
    }

    /**
     * @param type {@link PointDeVente#type}.
     */
    @BaliseXml(name = "type")
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return {@link PointDeVente#adresse}.
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse {@link PointDeVente#adresse}.
     */
    @BaliseXml(name = "address")
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @return {@link PointDeVente#codePostal}.
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * @param codePostal {@link PointDeVente#codePostal}.
     */
    @BaliseXml(name = "zipcode")
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * @return {@link PointDeVente#ville}.
     */
    public String getVille() {
        return ville;
    }

    /**
     * @param ville {@link PointDeVente#ville}.
     */
    @BaliseXml(name = "city")
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * @return {@link PointDeVente#district}.
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district {@link PointDeVente#district}.
     */
    @BaliseXml(name = "district")
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return {@link PointDeVente#telephone}.
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone {@link PointDeVente#telephone}.
     */
    @BaliseXml(name = "phone")
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return {@link PointDeVente#schedule}.
     */
    public String getSchedule() {
        return schedule;
    }

    /**
     * @param schedule {@link PointDeVente#schedule}.
     */
    @BaliseXml(name = "schedule")
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    /**
     * @return {@link PointDeVente#latitude}.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude {@link PointDeVente#latitude}.
     */
    @BaliseXml(name = "latitude", type = BaliseType.DOUBLE)
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return {@link PointDeVente#longitude}.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude {@link PointDeVente#longitude}.
     */
    @BaliseXml(name = "longitude", type = BaliseType.DOUBLE)
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
