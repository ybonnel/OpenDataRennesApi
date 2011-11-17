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
 * Status d'un équipement.
 */
public class EquipementStatus {
    /**
     * Identifiant de l'équipement.
     */
    private String id;
    /**
     * True si l'équipement est en marche.
     */
    private boolean on;
    /**
     * Dernière mise à jour.
     */
    private String lastUpdate;

    /**
     * @return {@link EquipementStatus#id}
     */
    public String getId() {
        return id;
    }

    /**
     * @param id {@link EquipementStatus#id}
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return {@link EquipementStatus#on}
     */
    public boolean isOn() {
        return on;
    }

    /**
     * @param on {@link EquipementStatus#on}
     */
    public void setOn(boolean on) {
        this.on = on;
    }

    /**
     * @return {@link EquipementStatus#lastUpdate}
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate {@link EquipementStatus#lastUpdate}
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
