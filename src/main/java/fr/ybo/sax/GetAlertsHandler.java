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

import fr.ybo.modele.bus.Alert;

/**
 * Handler SAX pour la réponse du getdistrict.
 *
 * @author ybonnel
 */
public class GetAlertsHandler extends KeolisHandler<Alert> {

	/**
	 * TITLE.
	 */
	private static final String TITLE = "title";
	/**
	 * STARTTIME.
	 */
	private static final String STARTTIME = "starttime";
	/**
	 * ENDTIME.
	 */
	private static final String ENDTIME = "endtime";
	/**
	 * LINE.
	 */
	private static final String LINE = "line";
	/**
	 * MAJORDISTURBANCE.
	 */
	private static final String MAJORDISTURBANCE = "majordisturbance";
	/**
	 * DETAIL.
	 */
	private static final String DETAIL = "detail";
	/**
	 * LINK.
	 */
	private static final String LINK = "link";
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
		if (baliseName.equals(TITLE)) {
			currentObjectKeolis.setTitle(contenuOfBalise);
		} else if (baliseName.equals(STARTTIME)) {
			currentObjectKeolis.setStarttime(contenuOfBalise);
		} else if (baliseName.equals(ENDTIME)) {
			currentObjectKeolis.setEndtime(contenuOfBalise);
		} else if (baliseName.equals(LINE)) {
			currentObjectKeolis.getLines().add(contenuOfBalise);
		} else if (baliseName.equals(MAJORDISTURBANCE)) {
			currentObjectKeolis.setMajordisturbance(Boolean.parseBoolean(contenuOfBalise));
		} else if (baliseName.equals(DETAIL)) {
			currentObjectKeolis.setDetail(contenuOfBalise);
		} else if (baliseName.equals(LINK)) {
			currentObjectKeolis.setLink(contenuOfBalise);
		}
	}
}
