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
package fr.ybo.modele.velos;

import java.io.Serializable;

/**
 * Classe représentant une station de velo star.
 *
 * @author ybonnel
 */
@SuppressWarnings("serial")
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
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

    public int getSlotsavailable() {
        return slotsavailable;
    }

    public void setSlotsavailable(int slotsavailable) {
        this.slotsavailable = slotsavailable;
    }

    public int getBikesavailable() {
        return bikesavailable;
    }

    public void setBikesavailable(int bikesavailable) {
        this.bikesavailable = bikesavailable;
    }

    public boolean isPos() {
        return pos;
    }

    public void setPos(boolean pos) {
        this.pos = pos;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }
}
