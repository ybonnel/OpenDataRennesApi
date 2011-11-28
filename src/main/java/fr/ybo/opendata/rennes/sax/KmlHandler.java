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
package fr.ybo.opendata.rennes.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Handler pour les KMLs.
 *
 * @param <T>
 * @author ybonnel
 */
public abstract class KmlHandler<T> extends DefaultHandler {

    /**
     * Objet courant.
     */
    private T currentObjet;

    /**
     * List des objets.
     */
    private List<T> objets;

    /**
     * StringBuilder servant au parsing xml.
     */
    private StringBuilder contenu;

    @Override
    public void characters(char[] cars, int start, int length) throws SAXException {
        super.characters(cars, start, length);
        contenu.append(cars, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName.equals(getBaliseData())) {
                objets.add(currentObjet);
        } else if (contenu != null) {
            remplirObject(currentObjet, qName, contenu.toString());
        }
        if (contenu != null) {
            contenu.setLength(0);
        }
    }

    /**
     * Getter.
     *
     * @return la liste des objets récupérés.
     */
    public List<T> getObjets() {
        return objets;
    }

    /**
     * Méthode à implémenter donnant le nom de la balise englobante.
     *
     * @return le nom de la balise englobante.
     */
    protected abstract String getBaliseData();

    /**
     * Méthode à implémenter créant un nouvel objet.
     *
     * @return nouvel objet.
     */
    protected abstract T getNewObjet();

    /**
     * Méthode à implémenter remplissant le contenu d'un objet.
     *
     * @param currentObject objet courant.
     * @param baliseName          nom de la balise.
     * @param contenuOfBalise     contenu de la balise.
     */
    protected abstract void remplirObject(T currentObject, String baliseName, String contenuOfBalise);

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        contenu = new StringBuilder();
        objets = new ArrayList<T>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals(getBaliseData())) {
            currentObjet = getNewObjet();
        }
        contenu.setLength(0);
    }

}
