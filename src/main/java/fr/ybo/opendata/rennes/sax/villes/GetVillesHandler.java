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
package fr.ybo.opendata.rennes.sax.villes;

import fr.ybo.opendata.rennes.modele.villes.Ville;
import fr.ybo.opendata.rennes.sax.KeolisHandler;

/**
 * Handler SAX pour la réponse du getcities.
 *
 * @author ybonnel
 */
public class GetVillesHandler extends KeolisHandler<Ville> {

    private enum Balise {
        NAME("name") {
            @Override
            void remplirObjectKeolis(Ville currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setName(contenuOfBalise);
            }
        },
        DISTRICT("district") {
            @Override
            void remplirObjectKeolis(Ville currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setNombreDistricts(Integer.parseInt(contenuOfBalise));
            }
        },
        ID("id") {
            @Override
            void remplirObjectKeolis(Ville currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setId(contenuOfBalise);
            }
        };

        abstract void remplirObjectKeolis(Ville currentObjectKeolis, String contenuOfBalise);

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
     * Balise station.
     */
    private static final String CITY = "city";

    @Override
    protected String getBaliseData() {
        return CITY;
    }

    @Override
    protected Ville getNewObjetKeolis() {
        return new Ville();
    }

    @Override
    protected void remplirObjectKeolis(Ville currentObjectKeolis, String baliseName, String contenuOfBalise) {
        Balise balise = Balise.fromValue(baliseName);
        if (balise != null) {
            balise.remplirObjectKeolis(currentObjectKeolis, contenuOfBalise);
        }
    }
}
