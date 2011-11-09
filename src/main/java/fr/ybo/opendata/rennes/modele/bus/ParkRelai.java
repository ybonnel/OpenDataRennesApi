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
 * @author ybonnel
 */
@SuppressWarnings("serial")
public class ParkRelai implements Serializable {

    /**
     * name.
     */
    private String name;
    /**
     * latitude.
     */
    private double latitude;
    /**
     * longitude.
     */
    private double longitude;
    /**
     * carParkAvailable.
     */
    private Integer carParkAvailable;
    /**
     * carParkCapacity.
     */
    private Integer carParkCapacity;
    /**
     * lastupdate.
     */
    private String lastupdate;
    /**
     * state.
     */
    private StateParkRelai state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getCarParkAvailable() {
        return carParkAvailable;
    }

    public void setCarParkAvailable(Integer carParkAvailable) {
        this.carParkAvailable = carParkAvailable;
    }

    public Integer getCarParkCapacity() {
        return carParkCapacity;
    }

    public void setCarParkCapacity(Integer carParkCapacity) {
        this.carParkCapacity = carParkCapacity;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    public StateParkRelai getState() {
        return state;
    }

    public void setState(StateParkRelai state) {
        this.state = state;
    }
}
