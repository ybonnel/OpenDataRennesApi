/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.ybo.opendata.rennes.modele.bus;

public enum StateParkRelai {
    OUVERT(0),
    FERME(1),
    COMPLET(2),
    INDISPONIBLE(3);

    private int value;

    StateParkRelai(int value) {
        this.value = value;
    }

    public static StateParkRelai fromValue(int value) {
        for (StateParkRelai state : values()) {
            if (state.value == value) {
                return state;
            }
        }
        return null;
    }
}
