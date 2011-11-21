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

package fr.ybo.opendata.rennes.modele.metros;

import fr.ybo.opendata.rennes.sax.BaliseData;
import fr.ybo.opendata.rennes.sax.BaliseType;
import fr.ybo.opendata.rennes.sax.BaliseXml;

/**
 * Représente le status d'une station de metro.
 */
@BaliseData("station")
public class MetroStationStatus {
    /**
     * Identifiant de la station de métro.
     */
    private String id;
    /**
     * Status de la station de métro.
     */
    private boolean on;
    /**
     * Dernière mise à jour.
     */
    private String lastUpdate;

    /**
     * @return {@link MetroStationStatus#id}.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id {@link MetroStationStatus#id}.
     */
    @BaliseXml(name = "id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return {@link MetroStationStatus#on}.
     */
    public boolean isOn() {
        return on;
    }

    /**
     * @param on {@link MetroStationStatus#on}.
     */
    @BaliseXml(name = "status", type = BaliseType.BOOLEAN)
    public void setOn(boolean on) {
        this.on = on;
    }

    /**
     * @return {@link MetroStationStatus#lastUpdate}.
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate {@link MetroStationStatus#lastUpdate}.
     */
    @BaliseXml(name = "lastUpdate")
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
