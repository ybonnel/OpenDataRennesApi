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

import fr.ybo.modele.bus.ParkRelai;

/**
 * Handler pour rÃ©cupÃ©rer les parks relais.
 *
 * @author ybonnel
 */
public class GetParkRelaiHandler extends KeolisHandler<ParkRelai> {

	/**
	 * RELAY_PARK.
	 */
	private static final String RELAY_PARK = "relaypark";
	/**
	 * NAME.
	 */
	private static final String NAME = "name";
	/**
	 * LATITUDE.
	 */
	private static final String LATITUDE = "latitude";
	/**
	 * LONGITUDE.
	 */
	private static final String LONGITUDE = "longitude";
	/**
	 * CAR_PARK_AVAILABLE.
	 */
	private static final String CAR_PARK_AVAILABLE = "carparkavailable";
	/**
	 * CAR_PARK_CAPACITY.
	 */
	private static final String CAR_PARK_CAPACITY = "carparkcapacity";
	/**
	 * LAST_UPDATE.
	 */
	private static final String LAST_UPDATE = "lastupdate";
	/**
	 * STATE.
	 */
	private static final String STATE = "state";

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
		if (baliseName.equals(NAME)) {
			currentObjectKeolis.setName(contenuOfBalise);
		} else if (baliseName.equals(LATITUDE)) {
			currentObjectKeolis.setLatitude(Double.parseDouble(contenuOfBalise));
		} else if (baliseName.equals(LONGITUDE)) {
			currentObjectKeolis.setLongitude(Double.parseDouble(contenuOfBalise));
		} else if (baliseName.equals(CAR_PARK_AVAILABLE)) {
			currentObjectKeolis.setCarParkAvailable(Integer.parseInt(contenuOfBalise));
		} else if (baliseName.equals(CAR_PARK_CAPACITY)) {
			currentObjectKeolis.setCarParkCapacity(Integer.parseInt(contenuOfBalise));
		} else if (baliseName.equals(LAST_UPDATE)) {
			currentObjectKeolis.setLastupdate(contenuOfBalise);
		} else if (baliseName.equals(STATE)) {
			currentObjectKeolis.setState(Integer.parseInt(contenuOfBalise));
		}
	}
}
