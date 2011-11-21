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

package fr.ybo.opendata.rennes.modele.villes;

import fr.ybo.opendata.rennes.sax.BaliseData;
import fr.ybo.opendata.rennes.sax.BaliseType;
import fr.ybo.opendata.rennes.sax.BaliseXml;

/**
 * Représente un ville.
 */
@BaliseData("city")
public class Ville {
    /**
     * Nom de la ville.
     */
    private String name;

    /**
     * Nombre de quartiers.
     */
    private int nombreQuartiers;

    /**
     * Identifiant de la ville.
     */
    private String id;

    /**
     * @return {@link Ville#name}.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name {@link Ville#name}.
     */
    @BaliseXml(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return {@link Ville#nombreQuartiers}.
     */
    public int getNombreQuartiers() {
        return nombreQuartiers;
    }

    /**
     * @param nombreQuartiers {@link Ville#nombreQuartiers}.
     */
    @BaliseXml(name = "district", type = BaliseType.INTEGER)
    public void setNombreQuartiers(int nombreQuartiers) {
        this.nombreQuartiers = nombreQuartiers;
    }

    /**
     * @return {@link Ville#id}.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id {@link Ville#id}.
     */
    @BaliseXml(name = "id")
    public void setId(String id) {
        this.id = id;
    }
}
