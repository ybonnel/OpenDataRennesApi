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
package fr.ybo.opendata.rennes.modele.velos;

import fr.ybo.opendata.rennes.sax.BaliseData;
import fr.ybo.opendata.rennes.sax.BaliseType;
import fr.ybo.opendata.rennes.sax.BaliseXml;

import java.io.Serializable;

/**
 * Classe représentant une station de velo star.
 *
 * @author ybonnel
 */
@SuppressWarnings("serial")
@BaliseData("station")
public class Station implements Serializable {
    /**
     * Numéro de la station.
     */
    private String number;
    /**
     * Nom de la station.
     */
    private String name;
    /**
     * adresse de la station.
     */
    private String adresse;

    /**
     * Etat de la station.
     */
    private boolean state;

    /**
     * Latitude.
     */
    private double latitude;

    /**
     * Longitude.
     */
    private double longitude;
    /**
     * Places libres.
     */
    private int slotsavailable;
    /**
     * Vélos libres.
     */
    private int bikesavailable;
    /**
     * Position.
     */
    private boolean pos;
    /**
     * Nom du district.
     */
    private String district;
    /**
     * Date de dernière mise à jour.
     */
    private String lastupdate;

    /**
     * @return {@link Station#number}.
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number {@link Station#number}.
     */
    @BaliseXml(name = "number")
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return {@link Station#name}.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name {@link Station#name}.
     */
    @BaliseXml(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return {@link Station#adresse}.
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse {@link Station#adresse}.
     */
    @BaliseXml(name = "address")
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @return {@link Station#state}.
     */
    public boolean isState() {
        return state;
    }

    /**
     * @param state {@link Station#state}.
     */
    @BaliseXml(name = "state", type = BaliseType.BOOLEAN)
    public void setState(boolean state) {
        this.state = state;
    }

    /**
     * @return {@link Station#latitude}.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude {@link Station#latitude}.
     */
    @BaliseXml(name = "latitude", type = BaliseType.DOUBLE)
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return {@link Station#longitude}.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude {@link Station#longitude}.
     */
    @BaliseXml(name = "longitude", type = BaliseType.DOUBLE)
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return {@link Station#slotsavailable}.
     */
    public int getSlotsavailable() {
        return slotsavailable;
    }

    /**
     * @param slotsavailable {@link Station#slotsavailable}.
     */
    @BaliseXml(name = "slotsavailable", type = BaliseType.INTEGER)
    public void setSlotsavailable(int slotsavailable) {
        this.slotsavailable = slotsavailable;
    }

    /**
     * @return {@link Station#bikesavailable}.
     */
    public int getBikesavailable() {
        return bikesavailable;
    }

    /**
     * @param bikesavailable {@link Station#bikesavailable}.
     */
    @BaliseXml(name = "bikesavailable", type = BaliseType.INTEGER)
    public void setBikesavailable(int bikesavailable) {
        this.bikesavailable = bikesavailable;
    }

    /**
     * @return {@link Station#pos}.
     */
    public boolean isPos() {
        return pos;
    }

    /**
     * @param pos {@link Station#pos}.
     */
    @BaliseXml(name = "pos", type = BaliseType.BOOLEAN)
    public void setPos(boolean pos) {
        this.pos = pos;
    }

    /**
     * @return {@link Station#district}.
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district {@link Station#district}.
     */
    @BaliseXml(name = "district")
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return {@link Station#lastupdate}.
     */
    public String getLastupdate() {
        return lastupdate;
    }

    /**
     * @param lastupdate {@link Station#lastupdate}.
     */
    @BaliseXml(name = "lastupdate")
    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }
}
