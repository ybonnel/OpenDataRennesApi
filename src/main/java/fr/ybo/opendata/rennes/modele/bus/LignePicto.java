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

import fr.ybo.opendata.rennes.sax.BaliseData;
import fr.ybo.opendata.rennes.sax.BaliseXml;

/**
 * Picto pour une ligne de bus.
 */
@BaliseData("line")
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
    @BaliseXml(name = "name")
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
    @BaliseXml(name = "picto")
    public void setPicto(String picto) {
        this.picto = picto;
        pictoUrl = baseUrl + picto;
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

    /**
     * Base de l'url.
     */
    private static String baseUrl;

    /**
     * @param baseUrl {@link LignePicto#baseUrl}.
     */
    @BaliseXml(name = "baseurl")
    public static void setBaseUrl(String baseUrl) {
        LignePicto.baseUrl = baseUrl;
    }
}
