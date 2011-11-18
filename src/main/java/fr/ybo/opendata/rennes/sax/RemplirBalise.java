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

package fr.ybo.opendata.rennes.sax;

/**
 * Interface pour les type enumérés associés aux balises xml.
 * @param <T> Type d'objet Keolis.
 */
public interface RemplirBalise<T> {


    /**
     * Rempli un objet Keolis.
     *
     * @param currentObjectKeolis objet à remplir.
     * @param contenuOfBalise     contenu de la balise xml.
     * @see KeolisHandler#remplirObjectKeolis(Object, String, String).
     */
    void remplirObjectKeolis(T currentObjectKeolis, String contenuOfBalise);
}
