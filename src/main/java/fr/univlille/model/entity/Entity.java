package main.java.fr.univlille.model.entity;

import fr.univlille.iutinfo.cam.player.IStrategy;
import main.java.fr.univlille.utils.Observer;
import main.java.fr.univlille.utils.Subject;

/**
 * Classe abstraite {@code Entity} qui hérite de la casse {@code Subject} et qui implémente l'interface {@code Observer}.
 * Cette classe sert principalement à éviter la redondance de code dans les classe {@code Monster} et {@code Hunter}
 * en ce qui concerne leur IA respective.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * @see Subject
 * @see Observer
 */
public abstract class Entity extends Subject implements Observer{
    private IStrategy ia;
    private boolean isIA;

    /**
     * Méthode {@code setIA()} permettant de définir une IA propre à l'entité.
     * 
     * @param ia Ce paramètre représente l'IA associé à l'entité.
     * 
     */
    public void setIA(IStrategy ia){
        this.ia = ia;
    }

    /**
     * Méthode {@code getIA()} permettant d'obtenir l'IA associée à l'entité.
     * 
     * @return Cette méthode retourne l'IA associée à l'entité.
     */
    public IStrategy getIA(){
        return this.ia;
    }

    /**
     * Méthode {@code setIsIA()} permettant de définir le booléen qui, à son tour, définit le fait qu'une entité 
     * soit jouée par un Humain ou une "Intelligence Artificielle".
     * 
     * @param isIA Ce paramètre représente le {@code booléen} qui définit si l'entité est jouée ou non par une IA.
     */
    public void setIsIA(boolean isIA){
        this.isIA = isIA;
    }

    /**
     * Méthode {@code getIsIA()} permettant de savoir si une entité est jouée ou non par une IA.
     * 
     * @return Cette méthode retourne un {@code booléen} selon si oui ou non l'entité est jouée par une IA.
     */
    public boolean isIA(){
        return this.isIA;
    }
} 
