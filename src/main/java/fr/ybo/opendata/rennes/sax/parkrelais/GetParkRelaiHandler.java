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
package fr.ybo.opendata.rennes.sax.parkrelais;

import fr.ybo.opendata.rennes.modele.parkrelais.ParkRelai;
import fr.ybo.opendata.rennes.modele.parkrelais.StateParkRelai;
import fr.ybo.opendata.rennes.sax.KeolisHandler;

/**
 * Handler pour récupérer les parks relais.
 *
 * @author ybonnel
 */
public class GetParkRelaiHandler extends KeolisHandler<ParkRelai> {
    private enum Balise {
        NAME("name") {
            @Override
            void remplirObjectKeolis(ParkRelai currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setName(contenuOfBalise);
            }
        },
        LATITUDE("latitude") {
            @Override
            void remplirObjectKeolis(ParkRelai currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setLatitude(Double.parseDouble(contenuOfBalise));
            }
        },
        LONGITUDE("longitude") {
            @Override
            void remplirObjectKeolis(ParkRelai currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setLongitude(Double.parseDouble(contenuOfBalise));
            }
        },
        CAR_PARK_AVAILABLE("carparkavailable") {
            @Override
            void remplirObjectKeolis(ParkRelai currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setCarParkAvailable(Integer.parseInt(contenuOfBalise));
            }
        },
        CAR_PARK_CAPACITY("carparkcapacity") {
            @Override
            void remplirObjectKeolis(ParkRelai currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setCarParkCapacity(Integer.parseInt(contenuOfBalise));
            }
        },
        LAST_UPDATE("lastupdate") {
            @Override
            void remplirObjectKeolis(ParkRelai currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setLastupdate(contenuOfBalise);
            }
        },
        STATE("state") {
            @Override
            void remplirObjectKeolis(ParkRelai currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setState(StateParkRelai.fromValue(Integer.parseInt(contenuOfBalise)));
            }
        };

        abstract void remplirObjectKeolis(ParkRelai currentObjectKeolis, String contenuOfBalise);

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
     * RELAY_PARK.
     */
    private static final String RELAY_PARK = "relaypark";

    @Override
    protected String getBaliseData() {
        return RELAY_PARK;
    }

    @Override
    protected ParkRelai getNewObjetKeolis() {
        return new ParkRelai();
    }

    @Override
    protected void remplirObjectKeolis(ParkRelai currentObjectKeolis, String baliseName, String contenuOfBalise) {
        Balise balise = Balise.fromValue(baliseName);
        if (balise != null) {
            balise.remplirObjectKeolis(currentObjectKeolis, contenuOfBalise);
        }
    }
}
