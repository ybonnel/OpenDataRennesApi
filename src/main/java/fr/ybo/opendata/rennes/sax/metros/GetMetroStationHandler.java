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
package fr.ybo.opendata.rennes.sax.metros;

import fr.ybo.opendata.rennes.modele.metros.MetroStation;
import fr.ybo.opendata.rennes.sax.KeolisHandler;
import fr.ybo.opendata.rennes.sax.RemplirBalise;

/**
 * Handler SAX pour la réponse du getmetrostations.
 *
 * @author ybonnel
 */
public class GetMetroStationHandler extends KeolisHandler<MetroStation> {

    /**
     * Type enuméré pour les balises xml.
     */
    private enum Balise implements RemplirBalise<MetroStation> {
        /**
         * {@link MetroStation#id}.
         */
        ID("id") {
            @Override
            public void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setId(contenuOfBalise);
            }
        },
        /**
         * {@link MetroStation#name}.
         */
        NAME("name") {
            @Override
            public void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setName(contenuOfBalise);
            }
        },
        /**
         * {@link MetroStation#latitude}.
         */
        LATITUDE("latitude") {
            @Override
            public void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setLatitude(Double.parseDouble(contenuOfBalise));
            }
        },
        /**
         * {@link MetroStation#longitude}.
         */
        LONGITUDE("longitude") {
            @Override
            public void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setLongitude(Double.parseDouble(contenuOfBalise));
            }
        },
        /**
         * {@link MetroStation#platformDirection1}.
         */
        HAS_PLATFORM_DIRECTION_1("hasPlatformDirection1") {
            @Override
            public void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setPlatformDirection1("1".equals(contenuOfBalise));
            }
        },
        /**
         * {@link MetroStation#platformDirection2}.
         */
        HAS_PLATFORM_DIRECTION_2("hasPlatformDirection2") {
            @Override
            public void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setPlatformDirection2("1".equals(contenuOfBalise));
            }
        },
        /**
         * {@link MetroStation#rankingPlatformDirection1}.
         */
        RANKING_PLATFORM_DIRECTION_1("rankingPlatformDirection1") {
            @Override
            public void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise) {
                if (contenuOfBalise.length() > 0) {
                    currentObjectKeolis.setRankingPlatformDirection1(Integer.parseInt(contenuOfBalise));
                }
            }
        },
        /**
         * {@link MetroStation#rankingPlatformDirection2}.
         */
        RANKING_PLATFORM_DIRECTION_2("rankingPlatformDirection2") {
            @Override
            public void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise) {
                if (contenuOfBalise.length() > 0) {
                    currentObjectKeolis.setRankingPlatformDirection2(Integer.parseInt(contenuOfBalise));
                }
            }
        },
        /**
         * {@link MetroStation#nombreEtages}.
         */
        FLOORS("floors") {
            @Override
            public void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setNombreEtages(Integer.parseInt(contenuOfBalise));
            }
        },
        /**
         * {@link MetroStation#lastupdate}.
         */
        LAST_UPDATE("lastupdate") {
            @Override
            public void remplirObjectKeolis(MetroStation currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setLastupdate(contenuOfBalise);
            }
        };

        /**
         * Balise xml.
         */
        private String value;

        /**
         * Constructeur.
         *
         * @param value balise xml.
         */
        Balise(String value) {
            this.value = value;
        }

        /**
         * Renvoie l'enum en fonction de la balise xml.
         *
         * @param val balise xml.
         * @return l'enum.
         */
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
     * Balise station.
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
