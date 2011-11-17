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

package fr.ybo.opendata.rennes.util;

import fr.ybo.opendata.rennes.exceptions.KeolisReseauException;

import java.io.InputStream;

/**
 * Interface permettant des g�rer les connexion ext�rieurs.
 */
public interface Connecteur {

    /**
     * Renvoi l'InputStream associ� � une url.
     * @param url l'url.
     * @return l'inputStream associ�.
     * @throws KeolisReseauException si un probl�me r�seaux est rencontr�.
     */
    InputStream openInputStream(String url) throws KeolisReseauException;

}
