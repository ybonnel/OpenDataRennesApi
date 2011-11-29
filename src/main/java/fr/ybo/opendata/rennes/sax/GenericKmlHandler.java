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

import fr.ybo.opendata.rennes.exceptions.KeolisException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Handler SAX générique pour les KMLs.
 *
 * @param <T> Objet représentant le xml.
 * @author ybonnel
 */
public class GenericKmlHandler<T> extends KmlHandler<T> {

    /**
     * Balise data.
     */
    private String baliseData;

    /**
     * Map contenant la méthode à appelée pour chaque balise xml.
     */
    private Map<String, Method> mapBaliseMethod = new HashMap<String, Method>();
    /**
     * Map contenant le type associé à chaque balise xml.
     */
    private Map<String, BaliseType> mapBaliseType = new HashMap<String, BaliseType>();

    /**
     * Map contenant la méthode à appelée pour chaque balise xml SimpleData.
     */
    private Map<String, Method> mapBaliseSimpleDataMethod = new HashMap<String, Method>();
    /**
     * Map contenant le type associé à chaque balise xml SimpleData.
     */
    private Map<String, BaliseType> mapBaliseSimpleDataType = new HashMap<String, BaliseType>();

    /**
     * Balise SimpleData.
     */
    private static final String BALISE_SIMPLE_DATA = "SimpleData";
    /**
     * Attribut de la balise SimpleData à regarder.
     */
    private static final String ATT_SIMPLE_DATA = "name";

    /**
     * Constructeur de l'objet représentant le xml.
     */
    private Constructor<T> constructor;

    /**
     * @param clazz Classe représentant le xml.
     */
    public GenericKmlHandler(Class<T> clazz) {
        BaliseData annotationData = clazz.getAnnotation(BaliseData.class);
        if (annotationData == null) {
            throw new KeolisException("Pas d'annotation BaliseData trouvée pour la classe " + clazz);
        }
        baliseData = annotationData.value();
        try {
            constructor = clazz.getDeclaredConstructor();
        } catch (NoSuchMethodException exception) {
            throw new KeolisException("Le constructeur n'a pas été trouvé", exception);
        }

        for (Method method : clazz.getDeclaredMethods()) {
            BaliseXml baliseXml = method.getAnnotation(BaliseXml.class);
            if (baliseXml != null) {
                if (mapBaliseMethod.containsKey(baliseXml.name())) {
                    throw new KeolisException("Deux méthodes trouvées avec la même baliseXml");
                }
                mapBaliseMethod.put(baliseXml.name(), method);
                mapBaliseType.put(baliseXml.name(), baliseXml.type());
            }
            BaliseSimpleData baliseSimpleData = method.getAnnotation(BaliseSimpleData.class);
            if (baliseSimpleData != null) {
                if (mapBaliseSimpleDataMethod.containsKey(baliseSimpleData.name())) {
                    throw new KeolisException("Deux méthodes trouvées avec la même BaliseSimpleData");
                }
                mapBaliseSimpleDataMethod.put(baliseSimpleData.name(), method);
                mapBaliseSimpleDataType.put(baliseSimpleData.name(), baliseSimpleData.type());
            }
        }
    }

    @Override
    protected String getBaliseData() {
        return baliseData;
    }

    @Override
    protected T getNewObjet() {
        try {
            return constructor.newInstance();
        } catch (InstantiationException e) {
            throw new KeolisException("Problème lors de l'appel au constructeur", e);
        } catch (IllegalAccessException e) {
            throw new KeolisException("Problème lors de l'appel au constructeur", e);
        } catch (InvocationTargetException e) {
            throw new KeolisException("Problème lors de l'appel au constructeur", e);
        }
    }

    /**
     * Attributs de SimpleData courant.
     */
    private String simpleDataCurrentName;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (BALISE_SIMPLE_DATA.equals(qName)) {
            if (attributes != null) {
                simpleDataCurrentName = attributes.getValue(ATT_SIMPLE_DATA);
            }
        }
    }

    @Override
    protected void remplirObject(T currentObject, String baliseName, String contenuOfBalise) {
        if (contenuOfBalise.length() > 0) {
            if (mapBaliseMethod.containsKey(baliseName)) {
                try {
                    if (Modifier.isStatic(mapBaliseMethod.get(baliseName).getModifiers()) || currentObject != null) {
                        mapBaliseMethod.get(baliseName)
                                .invoke(currentObject, mapBaliseType.get(baliseName).convertir(contenuOfBalise));
                    }
                } catch (IllegalAccessException e) {
                    throw new KeolisException(
                            "Problème lors de l'appel à la méthode " + mapBaliseMethod.get(baliseName).getName(), e);
                } catch (InvocationTargetException e) {
                    throw new KeolisException(
                            "Problème lors de l'appel à la méthode " + mapBaliseMethod.get(baliseName).getName(), e);
                }
            } else if (BALISE_SIMPLE_DATA.equals(baliseName) && simpleDataCurrentName != null
                    && mapBaliseSimpleDataMethod.containsKey(simpleDataCurrentName)) {
                try {
                    if (Modifier.isStatic(mapBaliseSimpleDataMethod.get(simpleDataCurrentName).getModifiers())
                            || currentObject != null) {
                        mapBaliseSimpleDataMethod.get(simpleDataCurrentName).invoke(currentObject,
                                mapBaliseSimpleDataType.get(simpleDataCurrentName).convertir(contenuOfBalise));
                    }
                } catch (IllegalAccessException e) {
                    throw new KeolisException("Problème lors de l'appel à la méthode " + mapBaliseSimpleDataMethod
                            .get(simpleDataCurrentName).getName(), e);
                } catch (InvocationTargetException e) {
                    throw new KeolisException("Problème lors de l'appel à la méthode " + mapBaliseSimpleDataMethod
                            .get(simpleDataCurrentName).getName(), e);
                }
            }
        }
    }
}
