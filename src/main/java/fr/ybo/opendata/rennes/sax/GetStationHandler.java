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

import fr.ybo.opendata.rennes.modele.velos.Station;

/**
 * Handler SAX pour l'api getstation.
 *
 * @author ybonnel
 */
public class GetStationHandler extends KeolisHandler<Station> {


    private enum Balise {
        NAME("name") {
            @Override
            void remplirObjectKeolis(Station currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setName(contenuOfBalise);
            }
        },
        NUMBER("number") {
            @Override
            void remplirObjectKeolis(Station currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setNumber(contenuOfBalise);
            }
        },
        ADRESSE("address") {
            @Override
            void remplirObjectKeolis(Station currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setAdresse(contenuOfBalise);
            }
        },
        STATE("state") {
            @Override
            void remplirObjectKeolis(Station currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setState("1".equals(contenuOfBalise));
            }
        },
        LATITUDE("latitude") {
            @Override
            void remplirObjectKeolis(Station currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setLatitude(Double.parseDouble(contenuOfBalise));
            }
        },
        LONGITUDE("longitude") {
            @Override
            void remplirObjectKeolis(Station currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setLongitude(Double.parseDouble(contenuOfBalise));
            }
        },
        SLOTSAVAILABLE("slotsavailable") {
            @Override
            void remplirObjectKeolis(Station currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setSlotsavailable(Integer.parseInt(contenuOfBalise));
            }
        },
        BIKESAVAILABLE("bikesavailable") {
            @Override
            void remplirObjectKeolis(Station currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setBikesavailable(Integer.parseInt(contenuOfBalise));
            }
        },
        POS("pos") {
            @Override
            void remplirObjectKeolis(Station currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setPos("1".equals(contenuOfBalise));
            }
        },
        DISTRICT("district") {
            @Override
            void remplirObjectKeolis(Station currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setDistrict(contenuOfBalise);
            }
        },
        LASTUPDATE("lastupdate") {
            @Override
            void remplirObjectKeolis(Station currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setLastupdate(contenuOfBalise);
            }
        };

        abstract void remplirObjectKeolis(Station currentObjectKeolis, String contenuOfBalise);

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
     * Nom de la balise station.
     */
    private static final String STATION = "station";

    @Override
    protected String getBaliseData() {
        return STATION;
    }

    @Override
    protected Station getNewObjetKeolis() {
        return new Station();
    }

    @Override
    protected void remplirObjectKeolis(Station currentObjectKeolis, String baliseName, String contenuOfBalise) {
        Balise balise = Balise.fromValue(baliseName);
        if (balise != null) {
            balise.remplirObjectKeolis(currentObjectKeolis, contenuOfBalise);
        }
    }
}
