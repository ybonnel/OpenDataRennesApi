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

import fr.ybo.moteurcsv.annotation.BaliseCsv;
import fr.ybo.moteurcsv.annotation.FichierCsv;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    public String getPublisherName() {
        return publisherName;
    }

    public String getPublisherUrl() {
        return publisherUrl;
    }

    public String getLang() {
        return lang;
    }

    public Date getStartDate() {
        try {
            return SIMPLE_DATE_FORMAT.parse(startDate);
        } catch (ParseException ignore) {
            return null;
        }
    }

    public Date getEndDate() {
        try {
            return SIMPLE_DATE_FORMAT.parse(endDate);
        } catch (ParseException ignore) {
            return null;
        }
    }

    public String getVersion() {
        return version;
    }
}
