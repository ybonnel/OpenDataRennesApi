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

import fr.ybo.opendata.rennes.modele.equipements.Equipement;
import fr.ybo.opendata.rennes.modele.equipements.TypeEquipement;
import fr.ybo.opendata.rennes.sax.KeolisHandler;
import fr.ybo.opendata.rennes.sax.RemplirBalise;

/**
 * Handler SAX pour la réponse du getequipments.
 *
 * @author ybonnel
 */
public class GetEquipementsHandler extends KeolisHandler<Equipement> {

    /**
     * Type enuméré pour les balises xml.
     */
    private enum Balise implements RemplirBalise<Equipement> {
        /**
         * {@link Equipement#id}.
         */
        ID("id") {
            @Override
            public void remplirObjectKeolis(Equipement currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setId(contenuOfBalise);
            }
        },
        /**
         * {@link Equipement#station}.
         */
        STATION("station") {
            @Override
            public void remplirObjectKeolis(Equipement currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setStation(contenuOfBalise);
            }
        },
        /**
         * {@link Equipement#type}.
         */
        TYPE("type") {
            @Override
            public void remplirObjectKeolis(Equipement currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setType(TypeEquipement.valueOf(contenuOfBalise));
            }
        },
        /**
         * {@link Equipement#etageDepart}.
         */
        FROM_FLOOR("fromfloor") {
            @Override
            public void remplirObjectKeolis(Equipement currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setEtageDepart(Integer.parseInt(contenuOfBalise));
            }
        },
        /**
         * {@link Equipement#etageArrivee}.
         */
        TO_FLOOR("tofloor") {
            @Override
            public void remplirObjectKeolis(Equipement currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setEtageArrivee(Integer.parseInt(contenuOfBalise));
            }
        },
        /**
         * {@link Equipement#plateform}.
         */
        PLATFORM("platform") {
            @Override
            public void remplirObjectKeolis(Equipement currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setPlateform(Integer.parseInt(contenuOfBalise));
            }
        },
        /**
         * {@link Equipement#lastUpdate}.
         */
        LAST_UPDATE("lastupdate") {
            @Override
            public void remplirObjectKeolis(Equipement currentObjectKeolis, String contenuOfBalise) {
                currentObjectKeolis.setLastUpdate(contenuOfBalise);
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
     * Balise equipment.
     */
    private static final String EQUIPMENT = "equipment";

    @Override
    protected String getBaliseData() {
        return EQUIPMENT;
    }

    @Override
    protected Equipement getNewObjetKeolis() {
        return new Equipement();
    }

    @Override
    protected void remplirObjectKeolis(Equipement currentObjectKeolis, String baliseName, String contenuOfBalise) {
        Balise balise = Balise.fromValue(baliseName);
        if (balise != null) {
            balise.remplirObjectKeolis(currentObjectKeolis, contenuOfBalise);
        }
    }
}
