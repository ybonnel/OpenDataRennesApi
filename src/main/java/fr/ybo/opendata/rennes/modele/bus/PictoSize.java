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

public enum PictoSize {
    TAILLE_21(21),
    TAILLE_100(100),
    TAILLE_300(300),
    TAILLE_1000(1000);

    private int size;

    PictoSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
