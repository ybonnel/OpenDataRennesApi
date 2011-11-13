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

import fr.ybo.moteurcsv.annotation.BaliseCsv;
import fr.ybo.moteurcsv.annotation.FichierCsv;

import java.io.Serializable;

@FichierCsv("feed_info.txt")
public class GtfsFeedInfo implements Serializable {
    private static final long serialVersionUID = 8815113618441661239L;
    @BaliseCsv("feed_publisher_name")
    private String publisherName;
    @BaliseCsv("feed_publisher_url")
    private String publisherUrl;
    @BaliseCsv("feed_lang")
    private String lang;
    @BaliseCsv("feed_start_date")
    private String startDate;
    @BaliseCsv("feed_end_date")
    private String endDate;
    @BaliseCsv("feed_version")
    private String version;

    public String getPublisherName() {
        return publisherName;
    }

    public String getPublisherUrl() {
        return publisherUrl;
    }

    public String getLang() {
        return lang;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getVersion() {
        return version;
    }
}
