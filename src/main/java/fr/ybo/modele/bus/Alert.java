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
package fr.ybo.modele.bus;

import fr.ybo.util.Formatteur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Class représentant une alerte Keolis.
 *
 * @author ybonnel
 *
 */
@SuppressWarnings("serial")
public class Alert implements Serializable {

	/**
	 * title.
	 */
	private String title;
	/**
	 * starttime.
	 */
	private String starttime;
	/**
	 * endtime.
	 */
	private String endtime;
	/**
	 * lines.
	 */
	private List<String> lines;

	/**
	 * majordisturbance.
	 */
	private boolean majordisturbance;

	/**
	 * detail.
	 */
	private String detail;

	/**
	 * link.
	 */
	private String link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public List<String> getLines() {
        if (lines == null) {
            lines = new ArrayList<String>();
        }
        return lines;
    }

    public boolean isMajordisturbance() {
        return majordisturbance;
    }

    public void setMajordisturbance(boolean majordisturbance) {
        this.majordisturbance = majordisturbance;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDetailFormatte(Iterable<String> arrets) {
		StringBuilder lignes = new StringBuilder();
		for (String line : lines) {
			lignes.append(line);
			lignes.append(", ");
		}
		lignes.deleteCharAt(lignes.length() - 1);
		lignes.deleteCharAt(lignes.length() - 1);
		String detailFormatte =
				detail.replaceAll(" &nbsp;", "&nbsp;").replaceAll("&nbsp; ", "&nbsp;").replaceAll(" &nbsp;", "&nbsp;").replaceAll("&nbsp; ", "&nbsp;")
						.replaceAll("&nbsp;&nbsp;", "&nbsp;").replaceAll("&nbsp;", " ");
		StringBuilder resultat = new StringBuilder();
		char carOld = '\0';
		for (char car : detailFormatte.toCharArray()) {
			//noinspection OverlyComplexBooleanExpression
			if ((carOld >= '0' && carOld <= '9' || carOld >= 'a' && carOld <= 'z' || carOld == 'é') && car >= 'A' && car <= 'Z') {
				// Minuscule suivie d'une majuscule, ça doit être un retour à la ligne qui manque.
				resultat.append(".\n");
			}
			resultat.append(car);
			carOld = car;
		}

		String resultatChaine = resultat.toString();
		for (String arretToBold : arrets) {
			resultatChaine = resultatChaine.replaceAll(arretToBold, "<b>" + arretToBold + "</b>");
		}

		// recherche des lignes à mettre en gras.
		String[] champs = resultatChaine.split("\n");
		StringBuilder stringBuilder = new StringBuilder();
		for (String champ : champs) {
			if (champ.startsWith("Ligne")) {
				stringBuilder.append("<br/><b>");
			}
			stringBuilder.append(champ);
			if (champ.startsWith("Ligne")) {
				stringBuilder.append("</b>");
			}
			stringBuilder.append("<br/>");
		}
		return stringBuilder.toString();
	}

	private static final Collection<Character> CARAC_TO_DELETE = new HashSet<Character>(11);

	static {
		CARAC_TO_DELETE.add(' ');
		CARAC_TO_DELETE.add('0');
		CARAC_TO_DELETE.add('1');
		CARAC_TO_DELETE.add('2');
		CARAC_TO_DELETE.add('3');
		CARAC_TO_DELETE.add('4');
		CARAC_TO_DELETE.add('5');
		CARAC_TO_DELETE.add('6');
		CARAC_TO_DELETE.add('7');
		CARAC_TO_DELETE.add('8');
		CARAC_TO_DELETE.add('9');
	}

	public CharSequence getTitleFormate() {
		String titleFormate = title;
		while (titleFormate.length() > 0 && CARAC_TO_DELETE.contains(titleFormate.charAt(0))) {
			titleFormate = titleFormate.substring(1);
			if (titleFormate.startsWith("TTZ")) {
				titleFormate = titleFormate.substring(3);
			} else if (titleFormate.startsWith("kl")) {
				titleFormate = titleFormate.substring(2);
			}
		}
		return Formatteur.formatterChaine(titleFormate);
	}

}
