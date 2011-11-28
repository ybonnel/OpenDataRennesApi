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

package fr.ybo.opendata.rennes.modele.parkrelais;

/**
 * Etat d'un park relai.
 */
public enum StateParkRelai {
    /**
     * Ouvert.
     */
    OUVERT(0),
    /**
     * Fermé.
     */
    FERME(1),
    /**
     * Complet.
     */
    COMPLET(2),
    /**
     * Indiponible.
     */
    INDISPONIBLE(3);

    /**
     * Valeur dans le xml.
     */
    private int value;

    /**
     * Constructeur.
     * @param value {@link StateParkRelai#value}.
     */
    StateParkRelai(int value) {
        this.value = value;
    }

    /**
     * Permet de récupérer l'état d'un park relai en fonction de la valeur dans le xml.
     * @param value valeur dans le xml.
     * @return état du park relai.
     */
    public static StateParkRelai fromValue(int value) {
        for (StateParkRelai state : values()) {
            if (state.value == value) {
                return state;
            }
        }
        return null;
    }
}
