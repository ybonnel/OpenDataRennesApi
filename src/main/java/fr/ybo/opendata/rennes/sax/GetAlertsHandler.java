/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Contributors:
 *     ybonnel - initial API and implementation
 */
package fr.ybo.opendata.rennes.sax;

import fr.ybo.opendata.rennes.modele.bus.Alert;

/**
 * Handler SAX pour la réponse du getdistrict.
 *
 * @author ybonnel
 */
public class GetAlertsHandler extends KeolisHandler<Alert> {

    private enum Balise {
        TITLE("title") {
            @Override
            void remplirObjectKeolis(Alert currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setTitle(contenuOfBalise);
            }
        },
        STARTTIME("starttime") {
            @Override
            void remplirObjectKeolis(Alert currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setStarttime(contenuOfBalise);
            }
        },
        ENDTIME("endtime") {
            @Override
            void remplirObjectKeolis(Alert currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setEndtime(contenuOfBalise);
            }
        },
        LINE("line") {
            @Override
            void remplirObjectKeolis(Alert currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.getLines().add(contenuOfBalise);
            }
        },
        MAJORDISTURBANCE("majordisturbance") {
            @Override
            void remplirObjectKeolis(Alert currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setMajordisturbance(Boolean.parseBoolean(contenuOfBalise));
            }
        },
        DETAIL("detail") {
            @Override
            void remplirObjectKeolis(Alert currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setDetail(contenuOfBalise);
            }
        },
        LINK("link") {
            @Override
            void remplirObjectKeolis(Alert currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setLink(contenuOfBalise);
            }
        };

        abstract void remplirObjectKeolis(Alert currentObjectKeolis, String contenuOfBalise);

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
     * ALERT.
     */
    private static final String ALERT = "alert";

    @Override
    protected String getBaliseData() {
        return ALERT;
    }

    @Override
    protected Alert getNewObjetKeolis() {
        return new Alert();
    }

    @Override
    protected void remplirObjectKeolis(Alert currentObjectKeolis, String baliseName, String contenuOfBalise) {
        Balise balise = Balise.fromValue(baliseName);
        if (balise != null) {
            balise.remplirObjectKeolis(currentObjectKeolis, contenuOfBalise);
        }
    }
}
