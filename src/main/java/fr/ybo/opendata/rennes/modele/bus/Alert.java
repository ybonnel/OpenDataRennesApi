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
package fr.ybo.opendata.rennes.modele.bus;

import fr.ybo.opendata.rennes.util.Formatteur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Class représentant une alerte Keolis.
 *
 * @author ybonnel
 */
@SuppressWarnings("serial")
public class Alert implements Serializable {

    /**
     * Titre de l'alerte.
     */
    private String title;
    /**
     * Démarage de l'alerte..
     */
    private String starttime;
    /**
     * Fin de l'alerte.
     */
    private String endtime;
    /**
     * Liste des lignes concernées.
     */
    private List<String> lines;

    /**
     * Problème majeure?.
     */
    private boolean majordisturbance;

    /**
     * Détail de l'alerte.
     */
    private String detail;

    /**
     * Lien.
     */
    private String link;

    /**
     * @return {@link Alert#title}.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title {@link Alert#title}.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return {@link Alert#starttime}.
     */
    public String getStarttime() {
        return starttime;
    }

    /**
     * @param starttime {@link Alert#starttime}.
     */
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    /**
     * @return {@link Alert#endtime}.
     */
    public String getEndtime() {
        return endtime;
    }

    /**
     * @param endtime {@link Alert#endtime}.
     */
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    /**
     * @return {@link Alert#lines}.
     */
    public List<String> getLines() {
        if (lines == null) {
            lines = new ArrayList<String>();
        }
        return lines;
    }

    /**
     * @return {@link Alert#majordisturbance}.
     */
    public boolean isMajordisturbance() {
        return majordisturbance;
    }

    /**
     * @param majordisturbance {@link Alert#majordisturbance}.
     */
    public void setMajordisturbance(boolean majordisturbance) {
        this.majordisturbance = majordisturbance;
    }

    /**
     * @return {@link Alert#detail}.
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail {@link Alert#detail}.
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return {@link Alert#link}.
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link {@link Alert#link}.
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * D&eacute;tail de l'alert formatt&eacute;.<br/>
     * Si une minuscule est suivie d'une majuscule (sans espace), on ins&egrave;re un retour &agrave; la ligne.<br/>
     * Tous les arr&ecirc;ts pass&eacute;s en param&egrave;tre sont mis en gras (&lt;b&gt;).<br/>
     * Toutes lignes commen&ccedil;ant par "Ligne" est mise en gras.<br/>
     * Les retour &agrave; la ligne sont transform&eacute;s en &lt;br/&gt;.<br/>
     *
     * @param arrets liste des arr&ecirc;ts.
     * @return d&eacute;tail formatt&eacute;.
     */
    public String getDetailFormatte(Iterable<String> arrets) {
        StringBuilder lignes = new StringBuilder();
        for (String line : lines) {
            lignes.append(line);
            lignes.append(", ");
        }
        lignes.deleteCharAt(lignes.length() - 1);
        lignes.deleteCharAt(lignes.length() - 1);
        String detailFormatte =
                detail.replaceAll(" &nbsp;", "&nbsp;").replaceAll("&nbsp; ", "&nbsp;").replaceAll(" &nbsp;", "&nbsp;")
                        .replaceAll("&nbsp; ", "&nbsp;").replaceAll("&nbsp;&nbsp;", "&nbsp;").replaceAll("&nbsp;",
                        " ");
        StringBuilder resultat = new StringBuilder();
        char carOld = '\0';
        for (char car : detailFormatte.toCharArray()) {
            //noinspection OverlyComplexBooleanExpression
            if ((carOld >= '0' && carOld <= '9' || carOld >= 'a' && carOld <= 'z' || carOld == 'é') && car >= 'A'
                    && car <= 'Z') {
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

    /**
     * Caractères à supprimer en début de titre.
     */
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

    /**
     * Ligne TTZ.
     */
    private static final String LIGNE_TTZ = "TTZ";
    /**
     * Ligne KL.
     */
    private static final String LIGNE_KL = "KL";

    /**
     * Les lignes de bus en début de titre sont supprimées.
     *
     * @return le titre formatté.
     */
    public CharSequence getTitleFormate() {
        String titleFormate = title;
        while (titleFormate.length() > 0 && CARAC_TO_DELETE.contains(titleFormate.charAt(0))) {
            titleFormate = titleFormate.substring(1);
            if (titleFormate.startsWith(LIGNE_TTZ)) {
                titleFormate = titleFormate.substring(LIGNE_TTZ.length());
            } else if (titleFormate.startsWith(LIGNE_KL)) {
                titleFormate = titleFormate.substring(LIGNE_KL.length());
            }
        }
        return Formatteur.formatterChaine(titleFormate);
    }

}
