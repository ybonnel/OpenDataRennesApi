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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Handler SAX générique pour les apis Keolis..
 *
 * @param <T> Objet représentant le xml.
 * @author ybonnel
 */
public class GenericHandler<T> extends KeolisHandler<T> {

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
     * Constructeur de l'objet représentant le xml.
     */
    private Constructor<T> constructor;

    /**
     * @param classKeolis Classe représentant le xml.
     */
    public GenericHandler(Class<T> classKeolis) {
        BaliseData annotationData = classKeolis.getAnnotation(BaliseData.class);
        if (annotationData == null) {
            throw new KeolisException("Pas d'annotation BaliseData trouvée pour la classe " + classKeolis);
        }
        baliseData = annotationData.value();
        try {
            constructor = classKeolis.getDeclaredConstructor();
        } catch (NoSuchMethodException exception) {
            throw new KeolisException("Le constructeur n'a pas été trouvé", exception);
        }

        for (Method method : classKeolis.getDeclaredMethods()) {
            BaliseXml baliseXml = method.getAnnotation(BaliseXml.class);
            if (baliseXml != null) {
                if (mapBaliseMethod.containsKey(baliseXml.name())) {
                    throw new KeolisException("Deux méthodes trouvées avec la même baliseXml");
                }
                mapBaliseMethod.put(baliseXml.name(), method);
                mapBaliseType.put(baliseXml.name(), baliseXml.type());
            }
        }
    }

    @Override
    protected String getBaliseData() {
        return baliseData;
    }

    @Override
    protected T getNewObjetKeolis() {
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

    @Override
    protected void remplirObjectKeolis(T currentObjectKeolis, String baliseName, String contenuOfBalise) {
        if (mapBaliseMethod.containsKey(baliseName)) {
            try {
                mapBaliseMethod.get(baliseName)
                        .invoke(currentObjectKeolis, mapBaliseType.get(baliseName).convertir(contenuOfBalise));
            } catch (IllegalAccessException e) {
                throw new KeolisException(
                        "Problème lors de l'appel à la méthode " + mapBaliseMethod.get(baliseName).getName(), e);
            } catch (InvocationTargetException e) {
                throw new KeolisException(
                        "Problème lors de l'appel à la méthode " + mapBaliseMethod.get(baliseName).getName(), e);
            }
        }
    }
}
