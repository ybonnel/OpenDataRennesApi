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

import fr.ybo.opendata.rennes.modele.metros.MetroStation;

/**
 * Handler SAX pour la réponse du getmetrostations.
 *
 * @author ybonnel
 */
public class GetMetroStationHandler extends KeolisHandler<MetroStation> {

    private enum Balise {
        ID("id") {
            @Override
            void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setId(contenuOfBalise);
            }
        },
        NAME("name") {
            @Override
            void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setName(contenuOfBalise);
            }
        },
        LATITUDE("latitude") {
            @Override
            void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setLatitude(Double.parseDouble(contenuOfBalise));
            }
        },
        LONGITUDE("longitude") {
            @Override
            void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setLongitude(Double.parseDouble(contenuOfBalise));
            }
        },
        HAS_PLATFORM_DIRECTION_1("hasPlatformDirection1") {
            @Override
            void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setPlatformDirection1("1".equals(contenuOfBalise));
            }
        },
        HAS_PLATFORM_DIRECTION_2("hasPlatformDirection2") {
            @Override
            void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setPlatformDirection2("1".equals(contenuOfBalise));
            }
        },
        RANKING_PLATFORM_DIRECTION_1("rankingPlatformDirection1") {
            @Override
            void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise) {
                if (contenuOfBalise.length() > 0) {
                    currentObjectKeolis.setRankingPlatformDirection1(Integer.parseInt(contenuOfBalise));
                }
            }
        },
        RANKING_PLATFORM_DIRECTION_2("rankingPlatformDirection2") {
            @Override
            void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise) {
                if (contenuOfBalise.length() > 0) {
                    currentObjectKeolis.setRankingPlatformDirection2(Integer.parseInt(contenuOfBalise));
                }
            }
        },
        FLOORS("floors") {
            @Override
            void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setEtage(Integer.parseInt(contenuOfBalise));
            }
        },
        LAST_UPDATE("lastupdate") {
            @Override
            void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setLastupdate(contenuOfBalise);
            }
        };

        abstract void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise);

        private String value;

        Balise(String value) {
            this.value = value;
        }

        public static Balise fromValue(String val) {
            for (Balise balise : values()) {
                if (balise.value.equals(val)) {
                    return balise;
                }
            }
            return null;
        }
    }

    /**
     * STATION.
     */
    private static final String STATION = "station";

    @Override
    protected String getBaliseData() {
        return STATION;
    }

    @Override
    protected MetroStation getNewObjetKeolis() {
        return new MetroStation();
    }

    @Override
    protected void remplirObjectKeolis(MetroStation currentObjectKeolis, String baliseName, String contenuOfBalise) {
        Balise balise = Balise.fromValue(baliseName);
        if (balise != null) {
            balise.remplirObjectKeolis(currentObjectKeolis, contenuOfBalise);
        }
    }
}
