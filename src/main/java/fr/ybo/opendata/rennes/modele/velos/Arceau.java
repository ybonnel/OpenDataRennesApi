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
import fr.ybo.opendata.rennes.sax.BaliseXml;

/**
 * Représente un arceau pour les velos.
 */
@BaliseData("Placemark")
public class Arceau {

    /**
     * Identifiant.
     */
    private String id;

    /**
     * Latitude.
     */
    private double latitude;

    /**
     * Longitude.
     */
    private double longitude;

    /**
     * @return {@link Arceau#id}.
     */
    public String getId() {
        return id;
    }

    /**
     * @return {@link Arceau#latitude}.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @return {@link Arceau#longitude}.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param id {@link Arceau#id}.
     */
    @BaliseXml(name = "name")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Permet de mettre à jour les coordonnées (latitude et longitude).
     *
     * @param coordonnees les coordonnées.
     */
    @BaliseXml(name = "coordinates")
    public void setCoordonnees(String coordonnees) {
        String[] champs = coordonnees.split(",");
        longitude = Double.parseDouble(champs[0]);
        latitude = Double.parseDouble(champs[1]);
    }
}
