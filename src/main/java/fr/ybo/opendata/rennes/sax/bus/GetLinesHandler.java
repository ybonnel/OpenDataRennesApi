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

import fr.ybo.opendata.rennes.modele.bus.LignePicto;
import fr.ybo.opendata.rennes.sax.KeolisHandler;
import fr.ybo.opendata.rennes.sax.RemplirBalise;

/**
 * Handler SAX pour l'api getlines.
 *
 * @author ybonnel
 */
public class GetLinesHandler extends KeolisHandler<LignePicto> {

    /**
     * Url de base pour les icônes.
     */
    private static String baseUrl;

    /**
     * Type enuméré pour les balises xml.
     */
    private enum Balise implements RemplirBalise<LignePicto> {
        /**
         * {@link LignePicto#name}.
         */
        NAME("name") {
            @Override
            public void remplirObjectKeolis(LignePicto currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setName(contenuOfBalise);
            }
        },
        /**
         * {@link LignePicto#picto}.
         */
        PICTO("picto") {
            @Override
            public void remplirObjectKeolis(LignePicto currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setPicto(contenuOfBalise);
                currentObjectKeolis.setPictoUrl(baseUrl + contenuOfBalise);
            }
        },
        /**
         * {@link LignePicto#pictoUrl}.
         */
        BASEURL("baseurl") {
            @Override
            public void remplirObjectKeolis(LignePicto currentObjectKeolis, String contenuOfBalise) {
                baseUrl = contenuOfBalise;
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
     * Nom de la balise line.
     */
    private static final String LINE = "line";

    @Override
    protected String getBaliseData() {
        return LINE;
    }

    @Override
    protected LignePicto getNewObjetKeolis() {
        return new LignePicto();
    }

    @Override
    protected void remplirObjectKeolis(LignePicto currentObjectKeolis, String baliseName, String contenuOfBalise) {
        Balise balise = Balise.fromValue(baliseName);
        if (balise != null) {
            balise.remplirObjectKeolis(currentObjectKeolis, contenuOfBalise);
        }
    }
}
