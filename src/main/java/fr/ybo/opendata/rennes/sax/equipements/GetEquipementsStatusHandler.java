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
package fr.ybo.opendata.rennes.sax.equipements;

import fr.ybo.opendata.rennes.modele.equipements.EquipementStatus;
import fr.ybo.opendata.rennes.sax.KeolisHandler;
import fr.ybo.opendata.rennes.sax.RemplirBalise;

/**
 * Handler SAX pour la réponse du getequipmentsstatus.
 *
 * @author ybonnel
 */
public class GetEquipementsStatusHandler extends KeolisHandler<EquipementStatus> {

    /**
     * Type enuméré pour les balises xml.
     */
    private enum Balise implements RemplirBalise<EquipementStatus> {
        /**
         * {@link EquipementStatus#id}.
         */
        ID("id") {
            @Override
            public void remplirObjectKeolis(EquipementStatus currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setId(contenuOfBalise);
            }
        },
        /**
         * {@link EquipementStatus#on}.
         */
        STATE("state") {
            @Override
            public void remplirObjectKeolis(EquipementStatus currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setOn("1".equals(contenuOfBalise));
            }
        },
        /**
         * {@link EquipementStatus#lastUpdate}.
         */
        LAST_UPDATE("lastupdate") {
            @Override
            public void remplirObjectKeolis(EquipementStatus currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setLastUpdate(contenuOfBalise);
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
     * Balise equipment.
     */
    private static final String EQUIPMENT = "equipment";

    @Override
    protected String getBaliseData() {
        return EQUIPMENT;
    }

    @Override
    protected EquipementStatus getNewObjetKeolis() {
        return new EquipementStatus();
    }

    @Override
    protected void remplirObjectKeolis(EquipementStatus currentObjectKeolis, String baliseName,
                                       String contenuOfBalise) {
        Balise balise = Balise.fromValue(baliseName);
        if (balise != null) {
            balise.remplirObjectKeolis(currentObjectKeolis, contenuOfBalise);
        }
    }
}
