package main.java.fr.univlille.model.parameters;

/**
 * 
 * Enumération {@code AllParameters} qui nous permet de définir les paramètres du jeu, et plus précisement, de les utilisés lors dans des conditions de tests
 * pour définir la façon dont les ifnormations vont être traitées ou affichées.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * @see Enum
 * @see Parameters
 */
public enum AllParameters {
    /**
     * Définit le mode de vue du Monstre. Le monstre connait tout le labyrinthe.
     */
    COMPLETE_KNOWLEDGE,
    /**
     * Définit le mode de vue du Monstre. Le monstre connait un nombre définit de case autour de lui 
     * (Ce nombre sera définit à 2 au lancement du jeu, mais pourra être changé par l'utilisateur dans les paramètres).
     */
    PARTIAL_KNOWLEDGE,
    /**
     * Définit le mode de déplacement du Monstre. Il peut se déplace en haut, en bas, à gauche, à droite et en diagonale.
     */
    MOVEMENT_8_WITH_PASSING_WALL,
    /**
     * Définit le mode de déplacement du Monstre. Il peut se déplace en haut, en bas, à gauche, à droite et en diagonale.
     */
    MOVEMENT_8_WITHOUT_PASSING_WALL,
    /**
     * Définit le mode de déplacement du Monstre. Il peut se déplace en haut, en bas, à gauche, à droite.
     */
    MOVEMENT_4,
    /**
     * Définit le mode de jeu à hunter en tant que joueur et monster en tant qu'intelligence artificielle.
     */
    MONSTER_AI_ONLY,
    /**
     * Définit le mode de jeu à monster en tant que joueur et hunter en tant qu'intelligence artificielle.
     */
    HUNTER_AI_ONLY,
     /**
     * Définit le mode de jeu à monster en tant que intelligence artificielle et aussi hunter en tant que intelligence artificielle.
     */
    BOTH_AI,
    /**
     * Définit le mode de jeu à monster en tant que joueur et aussi hunter en tant que joueur.
     */
    BOTH_PLAYER,
    /**
     * Définit le labyrinthe aléatoire
     */
    LABYRAND,
    /**
     * Définit le labyrinthe prédéfinis numéro 1
     */
    LABYPREDEF1,
    /**
     * Définit le labyrinthe prédéfinis numéro 2
     */
    LABYPREDEF2,
    /**
     * Définit le labyrinthe prédéfinis numéro 3
     */
    LABYPREDEF3
}
