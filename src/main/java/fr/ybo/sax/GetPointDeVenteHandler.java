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

	/**
	 * POS.
	 */
	private static final String POS = "pos";
	/**
	 * NAME.
	 */
	private static final String NAME = "name";
	/**
	 * TYPE.
	 */
	private static final String TYPE = "type";
	/**
	 * ADRESSE.
	 */
	private static final String ADRESSE = "address";
	/**
	 * CODE_POSTAL.
	 */
	private static final String CODE_POSTAL = "zipcode";
	/**
	 * VILLE.
	 */
	private static final String VILLE = "city";
	/**
	 * DISTRICT.
	 */
	private static final String DISTRICT = "district";
	/**
	 * TELEPHONE.
	 */
	private static final String TELEPHONE = "phone";
	/**
	 * SCHEDULE.
	 */
	private static final String SCHEDULE = "schedule";
	/**
	 * LATITUDE.
	 */
	private static final String LATITUDE = "latitude";
	/**
	 * LONGITUDE.
	 */
	private static final String LONGITUDE = "longitude";

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
		if (baliseName.equals(NAME)) {
			currentObjectKeolis.setName(contenuOfBalise);
		} else if (baliseName.equals(TYPE)) {
			currentObjectKeolis.setType(contenuOfBalise);
		} else if (baliseName.equals(ADRESSE)) {
			currentObjectKeolis.setAdresse(contenuOfBalise);
		} else if (baliseName.equals(CODE_POSTAL)) {
			currentObjectKeolis.setCodePostal(contenuOfBalise);
		} else if (baliseName.equals(VILLE)) {
			currentObjectKeolis.setVille(contenuOfBalise);
		} else if (baliseName.equals(DISTRICT)) {
			currentObjectKeolis.setDistrict(contenuOfBalise);
		} else if (baliseName.equals(TELEPHONE)) {
			currentObjectKeolis.setTelephone(contenuOfBalise);
		} else if (baliseName.equals(SCHEDULE)) {
			currentObjectKeolis.setSchedule(contenuOfBalise);
		} else if (baliseName.equals(LATITUDE)) {
			currentObjectKeolis.setLatitude(Double.parseDouble(contenuOfBalise));
		} else if (baliseName.equals(LONGITUDE)) {
			currentObjectKeolis.setLongitude(Double.parseDouble(contenuOfBalise));
		}
	}
}
