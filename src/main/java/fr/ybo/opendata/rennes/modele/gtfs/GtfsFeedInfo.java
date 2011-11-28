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

/**
 * Classe représentant le feed_info d'un fichier GTFS.
 */
@FichierCsv("feed_info.txt")
public class GtfsFeedInfo implements Serializable {
    /**
     * Serial.
     */
    private static final long serialVersionUID = 8815113618441661239L;
    /**
     * Publisher name.
     */
    @BaliseCsv("feed_publisher_name")
    private String publisherName;
    /**
     * Url.
     */
    @BaliseCsv("feed_publisher_url")
    private String publisherUrl;
    /**
     * Langue.
     */
    @BaliseCsv("feed_lang")
    private String lang;
    /**
     * Date de début.
     */
    @BaliseCsv("feed_start_date")
    private String startDate;
    /**
     * Date de fin.
     */
    @BaliseCsv("feed_end_date")
    private String endDate;
    /**
     * Version.
     */
    @BaliseCsv("feed_version")
    private String version;

    /**
     * Format des dates.
     */
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    /**
     * @return {@link GtfsFeedInfo#publisherName}.
     */
    public String getPublisherName() {
        return publisherName;
    }

    /**
     * @return {@link GtfsFeedInfo#publisherUrl}.
     */
    public String getPublisherUrl() {
        return publisherUrl;
    }

    /**
     * @return {@link GtfsFeedInfo#lang}.
     */
    public String getLang() {
        return lang;
    }

    /**
     * @return {@link GtfsFeedInfo#startDate}.
     */
    public Date getStartDate() {
        try {
            return SIMPLE_DATE_FORMAT.parse(startDate);
        } catch (ParseException ignore) {
            return null;
        }
    }

    /**
     * @return {@link GtfsFeedInfo#endDate}.
     */
    public Date getEndDate() {
        try {
            return SIMPLE_DATE_FORMAT.parse(endDate);
        } catch (ParseException ignore) {
            return null;
        }
    }

    /**
     * @return {@link GtfsFeedInfo#version}.
     */
    public String getVersion() {
        return version;
    }
}
