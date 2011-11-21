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
import fr.ybo.opendata.rennes.sax.BaliseXml;

/**
 * Représente un quartier.
 */
@BaliseData("district")
public class Quartier {
    /**
     * Nom du quartier.
     */
    private String name;
    /**
     * Identifiant.
     */
    private String id;

    /**
     * @return {@link Quartier#name}.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name {@link Quartier#name}.
     */
    @BaliseXml(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return {@link Quartier#id}.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id {@link Quartier#id}.
     */
    @BaliseXml(name = "id")
    public void setId(String id) {
        this.id = id;
    }
}
