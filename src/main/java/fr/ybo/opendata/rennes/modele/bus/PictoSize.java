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
 * Taille du picto.
 */
public enum PictoSize {
    /**
     * 21 pixels.
     */
    TAILLE_21(21),
    /**
     * 100 pixels.
     */
    TAILLE_100(100),
    /**
     * 300 pixels.
     */
    TAILLE_300(300),
    /**
     * 1000 pixels.
     */
    TAILLE_1000(1000);

    /**
     * Taille à envoyer à l'api getlines.
     */
    private int size;

    /**
     * Constructeur.
     * @param size {@link PictoSize#size}.
     */
    PictoSize(int size) {
        this.size = size;
    }

    /**
     * @return {@link PictoSize#size}.
     */
    public int getSize() {
        return size;
    }
}
