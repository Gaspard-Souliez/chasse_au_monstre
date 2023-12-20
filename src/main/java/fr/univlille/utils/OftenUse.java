package main.java.fr.univlille.utils;

import java.util.Random;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import main.java.fr.univlille.view.gameview.allview.MainView;
import main.java.fr.univlille.view.gameview.allview.otherview.NameView;

/**
 * 
 * Classe {@code OftenUse} qui nous permet de définir les des paramètres utilisées dans plusieurs classe différentes ou qui sont utilisé fréquement dans une classe.<br>
 * Cette classe est ici pour éviter la redondance de code, d'avoir moins d'intanciation d'objet inutile et d'éviter d'écrire du code en "dur".
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 */
public class OftenUse {
    /**
     * Attribut {@code RANDOM} permettant de définir un Objet Random pouvant être utilisé dans différentes classe.
    */
    public static final Random RANDOM = new Random();
    /**
     * Attribut {@code TEXT_SIZE} permettant de définir un entier pouvant être utilisé dans différentes classe pour définir la taille de police d'un texte.
    */
    public static final int TEXT_SIZE = 20;

    /**
     * Attribut {@code EMPTY_TEXT_FOR_BUTTON} permettant de définir le texte pour un bouton vide.
     */
    public static final String EMPTY_TEXT_FOR_BUTTON = "    ";

    /**
     * Attribut {@code M_TEXT_FOR_BUTTON} permettant de définir le texte pour un bouton représentant le Monstre.
     */
    public static final String M_TEXT_FOR_BUTTON = " M";

    /**
     * Attribut {@code S_TEXT_FOR_BUTTON} permettant de définir le texte pour un bouton représentant la Sortie.
     */
    public static final String S_TEXT_FOR_BUTTON = " S ";

    /**
     * Attribut {@code T_TEXT_FOR_BUTTON} permettant de définir le texte pour un bouton représentant un tir du Chasseur.
     */
    public static final String T_TEXT_FOR_BUTTON = " T ";

    /**
     * Attribut {@code GREY_COLOR_FOR_BORDER} permettant de définir lé couleur des bordures des boutons du labyrinthe.
     */
    public static final Color GREY_COLOR_FOR_BORDER = Color.GREY;

    /**
     * Attribut {@code BLACK_COLOR_FOR_TEXT} permettant de définir la couleur de textes qui seront affichés dans des boutons vides.
     */
    public static final Color BLACK_COLOR_FOR_TEXT = Color.BLACK;

    /**
     * Attribut {@code WHITE_COLOR_FOR_TEXT} permettant de définir la couleur de textes qui seront affichés dans des boutons représentant des murs.
     */
    public static final Color WHITE_COLOR_FOR_TEXT = Color.WHITE;

    public static final String STYLE_FOR_GRIDPANE = "-fx-grid-lines-visible : true";
    /**
     * Attribut {@code BORDER_FOR_NVAIGABLE_BUTTON} permettant de définir les bordures pour les boutons de navigations.
     */
    public static final Border BORDER_FOR_NVAIGABLE_BUTTON = OftenUse.strokeBorder(BLACK_COLOR_FOR_TEXT);

    /**
     * Attribut {@code FONT_FOR_TEXT_IN_BUTTON} permettant de définir la police d'écriture des textes des boutons dans le labyrinthe.
     */
    public static final Font FONT_FOR_TEXT_IN_BUTTON = Font.font(Math.min(MainView.screenWidth, MainView.screenHeight) * 0.015);

    /**
     * Attribut {@code FONT_FOR_TITLE} permettant de définir la police d'écriture des titres.
     */
    public static final Font FONT_FOR_TITLE = Font.font(70);

    /**
     * Attribut {@code FONT_FOR_WIN} permettant de définir la police d'écriture des phrases dans les vues gagnantes.
     */
    public static final Font FONT_FOR_WIN = Font.font(30);

    /**
     * Attribut {@code FONT_FOR_BUTTON} permettant de définir la police d'écriture des textes dans les boutons normaux.
     */
    public static final Font FONT_FOR_BUTTON = Font.font(20);

    /**
     * Attribut {@code FONT_FOR_AUTHOR} permettant de définir la police d'écriture des crédits dans le Menu.
     */
    public static final Font FONT_FOR_AUTHOR = Font.font(TEXT_SIZE);

    /**
     * Attribut {@code FONT_FOR_PARAMETERSVIEW} permettant de définir la police d'écriture des textes dans les vues des paramètres.
     */
    public static final Font FONT_FOR_PARAMETERSVIEW = Font.font(TEXT_SIZE);

    /**
     * Attribut {@code STYLE_BACKGROUND_BLACK} permettant de définir le style à attribuer pour une couleur de fond noir. Utiliser pour l'affichage des murs.
     */
    public static final String STYLE_BACKGROUND_BLACK = "-fx-background-color : black";

    /**
     * Attribut {@code STYLE_BACKGROUND_WHITE} permettant de définir le style à attribuer pour une couleur de fond blanc. Utiliser pour l'affichage des cases vides.
     */
    public static final String STYLE_BACKGROUND_WHITE = "-fx-background-color : white";

    /**
     * Attribut {@code STYLE_BACKGROUND_RED} permettant de définir le style à attribuer pour une couleur de fond rouge. Utiliser pour l'affichage des cases où le déplacement est impossible.
     */
    public static final String STYLE_BACKGROUND_RED = "-fx-background-color : red";

    /**
     * Attribut {@code STYLE_BACKGROUND_GREY} permettant de définir le style à attribuer pour une couleur de fond grise. Utiliser pour l'affichage des cases inconnue pour la vue partielle du Monstre.
     */
    public static final String STYLE_BACKGROUND_GREY = "-fx-background-color : grey";

    /**
     * Attribut {@code SPACE_FOR_NAME_PLAYERS} permettant de définir la taille d'espace utilisé dans la classe {@link NameView}.
     */
    public static final int SPACE_FOR_NAME_PLAYERS = 30;

    /**
     * Attribut {@code SPACE_FOR_BUTTON} permettant de définir la taille d'espace utilisé dans la classe {@link NameView}.
     */
    public static final int SPACE_FOR_BUTTON = 20; 

    /**
     * Attribut {@code SPACE_FOR_LABEL_PLAYER} permettant de définir la taille d'espace utilisé dans la classe {@link NameView}.
     */
    public static final int SPACE_FOR_LABEL_PLAYER = 10;


    private static Border strokeBorder(Paint var0){
        return new Border(new BorderStroke[]{new BorderStroke(var0, BorderStrokeStyle.SOLID, (CornerRadii)null, (BorderWidths)null)});
    }

}
