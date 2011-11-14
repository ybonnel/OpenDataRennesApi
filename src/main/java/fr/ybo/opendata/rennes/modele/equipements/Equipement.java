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


public class Equipement {
    private String id;
    private String station;
    private TypeEquipement type;
    private int etageDepart;
    private int etageArrivee;
    private int plateform;
    private String lastUpdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public TypeEquipement getType() {
        return type;
    }

    public void setType(TypeEquipement type) {
        this.type = type;
    }

    public int getEtageDepart() {
        return etageDepart;
    }

    public void setEtageDepart(int etageDepart) {
        this.etageDepart = etageDepart;
    }

    public int getEtageArrivee() {
        return etageArrivee;
    }

    public void setEtageArrivee(int etageArrivee) {
        this.etageArrivee = etageArrivee;
    }

    public int getPlateform() {
        return plateform;
    }

    public void setPlateform(int plateform) {
        this.plateform = plateform;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
