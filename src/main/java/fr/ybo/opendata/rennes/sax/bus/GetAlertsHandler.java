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
package fr.ybo.opendata.rennes.sax.bus;

import fr.ybo.opendata.rennes.modele.bus.Alert;
import fr.ybo.opendata.rennes.sax.KeolisHandler;
import fr.ybo.opendata.rennes.sax.RemplirBalise;

/**
 * Handler SAX pour la réponse du getdistrict.
 *
 * @author ybonnel
 */
public class GetAlertsHandler extends KeolisHandler<Alert> {

    /**
     * Type enuméré pour les balises xml.
     */
    private enum Balise implements RemplirBalise<Alert> {
        /**
         * {@link Alert#title}.
         */
        TITLE("title") {
            @Override
            public void remplirObjectKeolis(Alert currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setTitle(contenuOfBalise);
            }
        },
        /**
         * {@link Alert#starttime}.
         */
        STARTTIME("starttime") {
            @Override
            public void remplirObjectKeolis(Alert currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setStarttime(contenuOfBalise);
            }
        },
        /**
         * {@link Alert#endtime}.
         */
        ENDTIME("endtime") {
            @Override
            public void remplirObjectKeolis(Alert currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setEndtime(contenuOfBalise);
            }
        },
        /**
         * {@link Alert#lines}.
         */
        LINE("line") {
            @Override
            public void remplirObjectKeolis(Alert currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.getLines().add(contenuOfBalise);
            }
        },
        /**
         * {@link Alert#majordisturbance}.
         */
        MAJORDISTURBANCE("majordisturbance") {
            @Override
            public void remplirObjectKeolis(Alert currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setMajordisturbance("1".equals(contenuOfBalise));
            }
        },
        /**
         * {@link Alert#detail}.
         */
        DETAIL("detail") {
            @Override
            public void remplirObjectKeolis(Alert currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setDetail(contenuOfBalise);
            }
        },
        /**
         * {@link Alert#link}.
         */
        LINK("link") {
            @Override
            public void remplirObjectKeolis(Alert currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setLink(contenuOfBalise);
            }
        };

        /**
         * Balise xml.
         */
        private String value;

        /**
         * Constructeur.
         * @param value balise xml.
         */
        Balise(String value) {
            this.value = value;
        }

        /**
         * Renvoie l'enum en fonction de la balise xml.
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
