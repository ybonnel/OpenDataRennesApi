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

import fr.ybo.opendata.rennes.modele.villes.Quartier;
import fr.ybo.opendata.rennes.sax.KeolisHandler;

/**
 * Handler SAX pour la réponse du getcities.
 *
 * @author ybonnel
 */
public class GetQuartiersHandler extends KeolisHandler<Quartier> {

    private enum Balise {
        NAME("name") {
            @Override
            void remplirObjectKeolis(Quartier currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setName(contenuOfBalise);
            }
        },
        ID("id") {
            @Override
            void remplirObjectKeolis(Quartier currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setId(contenuOfBalise);
            }
        };

        abstract void remplirObjectKeolis(Quartier currentObjectKeolis, String contenuOfBalise);

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
    private static final String DISTRICT = "district";

    @Override
    protected String getBaliseData() {
        return DISTRICT;
    }

    @Override
    protected Quartier getNewObjetKeolis() {
        return new Quartier();
    }

    @Override
    protected void remplirObjectKeolis(Quartier currentObjectKeolis, String baliseName, String contenuOfBalise) {
        Balise balise = Balise.fromValue(baliseName);
        if (balise != null) {
            balise.remplirObjectKeolis(currentObjectKeolis, contenuOfBalise);
        }
    }
}
