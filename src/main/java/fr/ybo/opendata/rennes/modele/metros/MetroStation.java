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

public class MetroStation {
    private String id;
    private String name;
    private double latitude;
    private double longitude;
    private boolean platformDirection1;
    private boolean platformDirection2;
    private Integer rankingPlatformDirection1;
    private Integer rankingPlatformDirection2;
    private int etage;
    private String lastupdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean hasPlatformDirection1() {
        return platformDirection1;
    }

    public void setPlatformDirection1(boolean platformDirection1) {
        this.platformDirection1 = platformDirection1;
    }

    public boolean hasPlatformDirection2() {
        return platformDirection2;
    }

    public void setPlatformDirection2(boolean platformDirection2) {
        this.platformDirection2 = platformDirection2;
    }

    public Integer getRankingPlatformDirection1() {
        return rankingPlatformDirection1;
    }

    public void setRankingPlatformDirection1(int rankingPlatformDirection1) {
        this.rankingPlatformDirection1 = rankingPlatformDirection1;
    }

    public Integer getRankingPlatformDirection2() {
        return rankingPlatformDirection2;
    }

    public void setRankingPlatformDirection2(int rankingPlatformDirection2) {
        this.rankingPlatformDirection2 = rankingPlatformDirection2;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }
}
