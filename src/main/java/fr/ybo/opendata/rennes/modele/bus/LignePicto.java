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

package fr.ybo.opendata.rennes.modele.bus;

/**
 * Picto pour une ligne de bus.
 */
public class LignePicto {

    /**
     * Nom de la ligne de bus.
     */
    private String name;
    /**
     * Nom de l'image.
     */
    private String picto;
    /**
     * Url complète de l'image.
     */
    private String pictoUrl;

    /**
     * @return {@link LignePicto#name}.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name {@link LignePicto#name}.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return {@link LignePicto#picto}.
     */
    public String getPicto() {
        return picto;
    }

    /**
     * @param picto {@link LignePicto#picto}.
     */
    public void setPicto(String picto) {
        this.picto = picto;
    }

    /**
     * @return {@link LignePicto#pictoUrl}.
     */
    public String getPictoUrl() {
        return pictoUrl;
    }

    /**
     * @param pictoUrl {@link LignePicto#pictoUrl}.
     */
    public void setPictoUrl(String pictoUrl) {
        this.pictoUrl = pictoUrl;
    }
}