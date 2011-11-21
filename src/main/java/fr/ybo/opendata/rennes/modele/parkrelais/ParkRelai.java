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
package fr.ybo.opendata.rennes.modele.parkrelais;

import fr.ybo.opendata.rennes.sax.BaliseData;
import fr.ybo.opendata.rennes.sax.BaliseType;
import fr.ybo.opendata.rennes.sax.BaliseXml;

import java.io.Serializable;

/**
 * @author ybonnel
 */
@SuppressWarnings("serial")
@BaliseData("relaypark")
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

    /**
     * @return {@link ParkRelai#name}.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name {@link ParkRelai#name}.
     */
    @BaliseXml(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return {@link ParkRelai#latitude}.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude {@link ParkRelai#latitude}.
     */
    @BaliseXml(name = "latitude", type = BaliseType.DOUBLE)
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return {@link ParkRelai#longitude}.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude {@link ParkRelai#longitude}.
     */
    @BaliseXml(name = "longitude", type = BaliseType.DOUBLE)
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return {@link ParkRelai#carParkAvailable}.
     */
    public Integer getCarParkAvailable() {
        return carParkAvailable;
    }

    /**
     * @param carParkAvailable {@link ParkRelai#carParkAvailable}.
     */
    @BaliseXml(name = "carparkavailable", type = BaliseType.INTEGER)
    public void setCarParkAvailable(Integer carParkAvailable) {
        this.carParkAvailable = carParkAvailable;
    }

    /**
     * @return {@link ParkRelai#carParkCapacity}.
     */
    public Integer getCarParkCapacity() {
        return carParkCapacity;
    }

    /**
     * @param carParkCapacity {@link ParkRelai#carParkCapacity}.
     */
    @BaliseXml(name = "carparkcapacity", type = BaliseType.INTEGER)
    public void setCarParkCapacity(Integer carParkCapacity) {
        this.carParkCapacity = carParkCapacity;
    }

    /**
     * @return {@link ParkRelai#lastupdate}.
     */
    public String getLastupdate() {
        return lastupdate;
    }

    /**
     * @param lastupdate {@link ParkRelai#lastupdate}.
     */
    @BaliseXml(name = "lastupdate")
    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    /**
     * @return {@link ParkRelai#state}.
     */
    public StateParkRelai getState() {
        return state;
    }

    /**
     * @param state {@link ParkRelai#state}.
     */
    @BaliseXml(name = "state")
    public void setState(String state) {
        this.state = StateParkRelai.fromValue(Integer.parseInt(state));
    }
}
