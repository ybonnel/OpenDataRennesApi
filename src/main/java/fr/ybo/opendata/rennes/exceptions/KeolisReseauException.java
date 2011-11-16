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
package fr.ybo.opendata.rennes.exceptions;

/**
 * Exception sur les problèmes réseaux.
 *
 * @author ybonnel
 */
public class KeolisReseauException extends Exception {

    /**
     * Serial.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur.
     */
    public KeolisReseauException() {
    }

    /**
     * Constructeur.
     * @param exception exception.
     */
    public KeolisReseauException(Exception exception) {
        super(exception);
    }
}
