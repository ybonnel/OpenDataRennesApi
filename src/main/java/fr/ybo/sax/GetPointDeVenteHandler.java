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
package fr.ybo.sax;

import fr.ybo.modele.bus.PointDeVente;

/**
 * Handler permettant de rÃ©cupÃ©rer les points de vente.
 *
 * @author ybonnel
 */
public class GetPointDeVenteHandler extends KeolisHandler<PointDeVente> {


    private enum Balise {
        NAME("name") {
            @Override
            void remplirObjectKeolis(PointDeVente currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setName(contenuOfBalise);
            }
        },
        TYPE("type") {
            @Override
            void remplirObjectKeolis(PointDeVente currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setType(contenuOfBalise);
            }
        },
        ADRESSE("address") {
            @Override
            void remplirObjectKeolis(PointDeVente currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setAdresse(contenuOfBalise);
            }
        },
        CODE_POSTAL("zipcode") {
            @Override
            void remplirObjectKeolis(PointDeVente currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setCodePostal(contenuOfBalise);
            }
        },
        VILLE("city") {
            @Override
            void remplirObjectKeolis(PointDeVente currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setVille(contenuOfBalise);
            }
        },
        DISTRICT("district") {
            @Override
            void remplirObjectKeolis(PointDeVente currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setDistrict(contenuOfBalise);
            }
        },
        TELEPHONE("phone") {
            @Override
            void remplirObjectKeolis(PointDeVente currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setTelephone(contenuOfBalise);
            }
        },
        SCHEDULE("schedule") {
            @Override
            void remplirObjectKeolis(PointDeVente currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setSchedule(contenuOfBalise);
            }
        },
        LATITUDE("latitude") {
            @Override
            void remplirObjectKeolis(PointDeVente currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setLatitude(Double.parseDouble(contenuOfBalise));
            }
        },
        LONGITUDE("longitude") {
            @Override
            void remplirObjectKeolis(PointDeVente currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setLongitude(Double.parseDouble(contenuOfBalise));
            }
        };

        abstract void remplirObjectKeolis(PointDeVente currentObjectKeolis, String contenuOfBalise);

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
     * POS.
     */
    private static final String POS = "pos";

    @Override
    protected String getBaliseData() {
        return POS;
    }

    @Override
    protected PointDeVente getNewObjetKeolis() {
        return new PointDeVente();
    }

    @Override
    protected void remplirObjectKeolis(PointDeVente currentObjectKeolis, String baliseName, String contenuOfBalise) {
        Balise balise = Balise.fromValue(baliseName);
        if (balise != null) {
            balise.remplirObjectKeolis(currentObjectKeolis, contenuOfBalise);
        }
    }
}
