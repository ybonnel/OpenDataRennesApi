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

package fr.ybo.opendata.rennes.modele.gtfs;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe représentant un fichier GTFS.
 */
public class GtfsFile implements Serializable {

    /**
     * Serial.
     */
    private static final long serialVersionUID = -5452814256977509096L;
    /**
     * Date du GTFS.
     */
    private Date date;
    /**
     * Url du GTFS.
     */
    private String url;

    /**
     * @return {@link GtfsFile#date}.
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date {@link GtfsFile#date}.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return {@link GtfsFile#url}.
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url {@link GtfsFile#url}.
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
