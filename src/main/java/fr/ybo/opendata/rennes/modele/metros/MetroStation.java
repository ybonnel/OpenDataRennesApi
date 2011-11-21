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

package fr.ybo.opendata.rennes.modele.metros;

import fr.ybo.opendata.rennes.sax.BaliseData;
import fr.ybo.opendata.rennes.sax.BaliseType;
import fr.ybo.opendata.rennes.sax.BaliseXml;

/**
 * Classe représentant une station de métro.
 */
@BaliseData("station")
public class MetroStation {
    /**
     * Identifiant de la station de métro.
     */
    private String id;
    /**
     * Nom de la station de métro.
     */
    private String name;
    /**
     * Latitude.
     */
    private double latitude;
    /**
     * Longitude.
     */
    private double longitude;
    /**
     * Plateform Direction 1.
     */
    private boolean platformDirection1;
    /**
     * Plateform Direction 2.
     */
    private boolean platformDirection2;
    /**
     * Ordre au sein de la direction 1.
     */
    private Integer rankingPlatformDirection1;
    /**
     * Ordre au sein de la direction 2.
     */
    private Integer rankingPlatformDirection2;
    /**
     * Nombre d'étages.
     */
    private int nombreEtages;
    /**
     * Dernière mise à jour.
     */
    private String lastupdate;

    /**
     * @return {@link MetroStation#id}.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id {@link MetroStation#id}.
     */
    @BaliseXml(name = "id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return {@link MetroStation#name}.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name {@link MetroStation#name}.
     */
    @BaliseXml(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return {@link MetroStation#latitude}.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude {@link MetroStation#latitude}.
     */
    @BaliseXml(name = "latitude", type = BaliseType.DOUBLE)
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return {@link MetroStation#longitude}.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude {@link MetroStation#longitude}
     */
    @BaliseXml(name = "longitude", type = BaliseType.DOUBLE)
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return {@link MetroStation#platformDirection1}.
     */
    public boolean hasPlatformDirection1() {
        return platformDirection1;
    }

    /**
     * @param platformDirection1 {@link MetroStation#platformDirection1}.
     */
    @BaliseXml(name = "hasPlatformDirection1", type = BaliseType.BOOLEAN)
    public void setPlatformDirection1(boolean platformDirection1) {
        this.platformDirection1 = platformDirection1;
    }

    /**
     * @return {@link MetroStation#platformDirection2}.
     */
    public boolean hasPlatformDirection2() {
        return platformDirection2;
    }

    /**
     * @param platformDirection2 {@link MetroStation#platformDirection2}.
     */
    @BaliseXml(name = "hasPlatformDirection2", type = BaliseType.BOOLEAN)
    public void setPlatformDirection2(boolean platformDirection2) {
        this.platformDirection2 = platformDirection2;
    }

    /**
     * @return {@link MetroStation#rankingPlatformDirection1}.
     */
    public Integer getRankingPlatformDirection1() {
        return rankingPlatformDirection1;
    }

    /**
     * @param rankingPlatformDirection1 {@link MetroStation#rankingPlatformDirection1}.
     */
    @BaliseXml(name = "rankingPlatformDirection1", type = BaliseType.INTEGER)
    public void setRankingPlatformDirection1(int rankingPlatformDirection1) {
        this.rankingPlatformDirection1 = rankingPlatformDirection1;
    }

    /**
     * @return {@link MetroStation#rankingPlatformDirection2}.
     */
    public Integer getRankingPlatformDirection2() {
        return rankingPlatformDirection2;
    }

    /**
     * @param rankingPlatformDirection2 {@link MetroStation#rankingPlatformDirection2}.
     */
    @BaliseXml(name = "rankingPlatformDirection2", type = BaliseType.INTEGER)
    public void setRankingPlatformDirection2(int rankingPlatformDirection2) {
        this.rankingPlatformDirection2 = rankingPlatformDirection2;
    }

    /**
     * @return {@link MetroStation#nombreEtages}.
     */
    public int getNombreEtages() {
        return nombreEtages;
    }

    /**
     * @param nombreEtages {@link MetroStation#nombreEtages}.
     */
    @BaliseXml(name = "floors", type = BaliseType.INTEGER)
    public void setNombreEtages(int nombreEtages) {
        this.nombreEtages = nombreEtages;
    }

    /**
     * @return {@link MetroStation#lastupdate}.
     */
    public String getLastupdate() {
        return lastupdate;
    }

    /**
     * @param lastupdate {@link MetroStation#lastupdate}.
     */
    @BaliseXml(name = "lastupdate")
    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }
}
