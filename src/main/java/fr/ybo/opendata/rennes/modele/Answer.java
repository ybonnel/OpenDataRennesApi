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
package fr.ybo.opendata.rennes.modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Réponse Kéolis.
 *
 * @param <T> type d'objet kéolis.
 * @author ybonnel
 */
public class Answer<T> {

    /**
     * Status.
     */
    private StatusKeolis status;
    /**
     * Liste d'objet Keolis.
     */
    private List<T> data;

    /**
     * @return les liste d'objet Keolis.
     */
    public List<T> getData() {
        if (data == null) {
            data = new ArrayList<T>();
        }
        return data;
    }

    /**
     * Getter.
     *
     * @return le status.
     */
    public StatusKeolis getStatus() {
        return status;
    }

    /**
     * Setter.
     *
     * @param pStatus le status.
     */
    public void setStatus(StatusKeolis pStatus) {
        status = pStatus;
    }
}
