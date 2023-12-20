package main.java.fr.univlille.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Classe abstraite {@code Subject} peremttant la communication entre un ou plusieurs objet.<br>
 * Cette classe permet de relier deux objets ensemble et de les faire communiquer.<br>
 * Lorsque que l'objet observé (le sujet) change d'état, il en informe les objet qui l'observent (les observeurs) pour qu'il puissent agir en conséquence.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * @see Observer
 */
public abstract class Subject {
    /**
     * La liste des objets liés au sujet.
     */
    protected List<Observer> attached;

    /**
     * Constructeur de la classe abstraite {@link Subject} qui permet d'instancier une nouvemlle liste d'observeurs.
     */
    protected Subject() {
        attached = new ArrayList<>();
    }

    /**
     * Méthode {@code attach} permettant de liés l'objet passé en paramètre avec l'objet courant.
     * 
     * @param obs L'objet avec qui lié l'objet courant.
     */
    public void attach(Observer obs) {
        if (! attached.contains(obs)) {
            attached.add(obs);
        }
    }

    /**
     * Méthode {@code detach} permettant de déliés l'objet passé en paramètre avec l'objet courant.
     * 
     * @param obs L'objet avec qui déliés l'objet courant.
     */
    public void detach(Observer obs) {
        if (attached.contains(obs)) {
            attached.remove(obs);
        }
    }

    /**
     * Méthode {@code notifyObservers} permettant de notifié tous les objet de la liste {@code attached} d'un changement d'état de l'objet courant.
     */
    protected void notifyObservers() {
        for (Observer o : attached) {
            o.update(this);
        }
    }

    /**
     * Méthode {@code notifyObservers} permettant de notifié tous les objet de la liste {@code attached} d'un changement d'état de l'objet courant avec en
     * paramètre, la donnée qui a été changée.
     * 
     * @param data La donnée qui a été changée et qui est transmise aux objets liés.
     */
    protected void notifyObservers(Object data) {
        for (Observer o : attached) {
            o.update(this, data);
        }
    }

}
