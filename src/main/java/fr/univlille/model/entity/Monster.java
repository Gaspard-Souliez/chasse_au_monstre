package main.java.fr.univlille.model.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import fr.univlille.iutinfo.cam.player.monster.IMonsterStrategy;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import main.java.fr.univlille.model.cell.CellEvent;
import main.java.fr.univlille.model.cell.Coordinate;
import main.java.fr.univlille.model.entity.iastrategy.IAMonstre;
import main.java.fr.univlille.model.exception.UnsupportedMove;
import main.java.fr.univlille.model.Game;
import main.java.fr.univlille.model.board.Board;
import main.java.fr.univlille.model.parameters.AllParameters;
import main.java.fr.univlille.model.parameters.Parameters;
import main.java.fr.univlille.utils.Subject;
import main.java.fr.univlille.view.gameview.AllViewEnum;

/**
 * 
 * Classe {@code Monster} peremttant l'initialisation du monstre.<br>
 * Cette classe hérité de la classe {@link Entity} ce qui permet d'avoir accès à ses méthode, et ce qui permet 
 * d'avoir accès aux classes et interfaces par l'accès à la classe {@link Entity}.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * @see Entity
 */
public class Monster extends Entity{
    private static Coordinate currentCoord;
    private static Coordinate lastCoord;
    private static ICoordinate exit;
    private final int[][] POSSIBILITES_FOR_CROSS;
    private ICoordinate lastShoot;
    private boolean[][] monsterBooleanMaze;
    private static Map<Coordinate,CellEvent> history = new HashMap<>();
    private List<ICoordinate> badShoots;

    /**
     * Constructeur principal de la classe {@code Monster} qui permet d'instancier un Monstre.<br>
     */
    public Monster(){
        this.POSSIBILITES_FOR_CROSS =  new int[][]{
            {-1,-1},
            {-1,1},
            {1,-1},
            {1,1}
        };
        this.lastShoot = null;
        this.badShoots = new ArrayList<>();
        this.setIsIA(false);
    }

    /**
     * Constructeur secondaire permettant d'intancier un monstre, mais jouer par une "Intelligence Artificielle".
     * 
     * @param ia Ce paramètre représente l'IA qui joue le Monstre.
     */
    public Monster(IAMonstre ia){
        this();
        this.setIA(ia);
        this.setIsIA(true);
    }

    /**
     * Méthode {@code play()} appelée lorsque le monstre est joué par une IA. <br>
     * 
     * @return Cette méthode retourne un {@code booléen} permettant de savoir si le monstre a atteint la sortie ou non.
     */
    public boolean play() {
        ICoordinate iaCoordinate = this.getIA().play();
        try {
            boolean temp = this.move(iaCoordinate);
            ICellEvent monsterEvent = new CellEvent(CellInfo.MONSTER, iaCoordinate, 0);
            this.getIA().update(monsterEvent);
            return temp;
        } catch (UnsupportedMove e) {
            this.play();
        }
        return false;
    }
    
    /**
     * Méthode {@code move} permettant le déplacement du monstre avec, en paramètre, les coordonnées de la case où le joueur veut se déplacer.<br>
     * Cette méthode permet également de vérifier si le Monstre a gagné.<br>
     * Cette méthode lève une exception si le mouvement n'est pas valable.
     * 
     * @param coord Ce paramètre représente les coordonnées de la case où le joueur veut se déplacer.
     * 
     * @return Cette méthode retourne un {@code booléen} permettant de savoir si le déplacement à fonctionné ou non. Ici {@code true} signifie que la case 
     * ou l'on souhaite se déplacer est la sortie, ce qui permet de déclencher la victoire du Monstre.
     * 
     * @throws UnsupportedMove Cette méthode lève l'{@code exception} lorsque le déplacement est impossible.
     */
    public boolean move(ICoordinate coord) throws UnsupportedMove{
        if (isGoodMove(coord)) {
            Coordinate tempCoord = new Coordinate(coord.getCol(), coord.getRow());
            Monster.lastCoord = Monster.currentCoord;
            Monster.currentCoord = tempCoord;
            if (tempCoord.equals(Monster.exit)) {
                notifyObservers(AllViewEnum.MONSTERWIN);
                return true;
            }
            else{
                CellEvent temp = new CellEvent(CellInfo.EMPTY, coord, ++Game.currentTurn);
                history.put(tempCoord,temp);
                notifyObservers(coord);
            }
        }
        else{
            this.badShoots.add(coord);
            throw new UnsupportedMove();
        }
        return false;
    }

    /**
     * Méthode {@code isGoodMove} permettant de défnir si le déplacement du joueur est valable ou non. <br>
     * Selon les paramètres saisis par le joueur, on vérifie soit que le coup peut se jouer pour un déplacement de 4 case autour de la position actuelle
     * ( c'est à dire, soit à gauche, soit à droite, soit en haut, soit en bas ), soit que le coup peut se jouer pour un 
     * déplacement de 8 cases autour de la position actuelle.
     * 
     * @param coord Ce paramètre représente les coordonnées de la case que le joueur veut atteindre.
     * 
     * @return Cette méthode retourne un {@code booléen} selon si le mouvement demandé estfaisable ou non.
     */
    private boolean isGoodMove(ICoordinate coord){
        if (Parameters.movement.equals(AllParameters.MOVEMENT_4)) {
            return isGoodMoveFour(coord);
        }
        else{
            return isGoodMoveEight(coord);
        }
    }

    /**
     * Méthode privée {@code isGoodMoveFour} permettant de défnir si le déplacement du joueur est valable ou non pour un déplacement de 4 case autour de la position actuelle
     * ( c'est à dire, soit à gauche, soit à droite, soit en haut, soit en bas ). <br>
     * Cette méthode vérifie également si la case souhaitée n'est pas un mur.
     * 
     * @param coord Ce paramètre représente les coordonnées de la case que le joueur veut atteindre.
     * 
     * @return Cette méthode retourne un {@code booléen} selon si le mouvement demandé estfaisable ou non.
     */
    private boolean isGoodMoveFour(ICoordinate coord){
        int col = coord.getCol() - Monster.currentCoord.getCol();
        int row = coord.getRow() - Monster.currentCoord.getRow();
        boolean goodCol = (col<=1 && col>=-1);
        boolean goodRow = (row<=1 && row>=-1);
        boolean isntWall = this.monsterBooleanMaze[coord.getRow()][coord.getCol()];
        if(Monster.currentCoord.getCol()!=coord.getCol() && Monster.currentCoord.getRow()!=coord.getRow()){
            return false;
        }
        return goodCol && goodRow && isntWall;
    }
    
    /**
     * Méthode privée {@code isGoodMoveEight} permettant de défnir si le déplacement du joueur est valable ou non pour un 
     * déplacement de 8 cases autour de la position actuelle.
     * Cette méthode vérifie également si la case souhaitée n'est pas un mur.
     * 
     * @param coord Ce paramètre représente les coordonnées de la case que le joueur veut atteindre.
     * 
     * @return Cette méthode retourne un {@code booléen} selon si le mouvement demandé estfaisable ou non.
     */
    private boolean isGoodMoveEight(ICoordinate coord){
        int col = coord.getCol() - Monster.currentCoord.getCol();
        int row = coord.getRow() - Monster.currentCoord.getRow();
        boolean goodCol = (col<=1 && col>=-1);
        boolean goodRow = (row<=1 && row>=-1);
        boolean isntWall = this.monsterBooleanMaze[coord.getRow()][coord.getCol()];
        return goodCol && goodRow && isntWall && dontCrossWall(col, row,coord);
        
    }

    /**
     * Méthode {@code dontCrossWall} permettant de vérifier que le Monstre ne puisse pas se déplacer à travers deux murs posés en diagonale comme dans l'exemple ci-dessous : <br>
     *      M | W <br>
     *      ----- <br>
     *      W | * <br>
     * Avec M étant le monstre, W un mur et * l'endroit ou il veut aller.
     * 
     * Le paramètre de déplacement {@code MOVEMENT_8_WITH_PASSING_WALL} de l'énumération {@link AllParameters} définit ici le fait de pouvoir
     * traverser les mûrs posées en diagonales. <br>
     * 
     * Le paramètre de déplacement {@code MOVEMENT_8_WITHOUT_PASSING_WALL} de l'énumération {@link AllParameters} définit, quant à lui,
     * l'impossibilité de traverser les mûrs posées en diagonales. <br>
     * 
     * @param col Ce paramètre représente la comparaison entre la colonne courante du Monstre et la colonne de la nouvelle coordonnée.
     * @param row Ce paramètre représente la comparaison entre la ligne courante du Monstre et la ligne de la nouvelle coordonnée.
     * @param coord Ce paramètre représente la coordonnée où le Monstre veut se rendre.
     * 
     * @return Cette méthode retourne un {@code booléen} selon si le mouvement souhaité traverse un mûr ou non. (Diffère selon le paramètre de déplacement définit)
     */
    private boolean dontCrossWall(int col, int row, ICoordinate coord){
        if(Parameters.movement.equals(AllParameters.MOVEMENT_8_WITH_PASSING_WALL)) return true;
        for (int[] cross : this.POSSIBILITES_FOR_CROSS) {
            if(cross[0]==col && cross[1]==row){
                return (this.monsterBooleanMaze[coord.getRow()-cross[1]][coord.getCol()] || this.monsterBooleanMaze[coord.getRow()][coord.getCol()-cross[0]]);
            }
        }
        return true;
    }

    /**
     * Méthode {@code initialize} permettant d'initialiser un labyrinthe à partir d'un tableau de booléen.
     * 
     * @param maze Ce paramètre représente le tableau de booléen représentant le labyrinthe.
     */
    public void initialize(boolean[][] maze) {
        this.monsterBooleanMaze = maze;
        if (this.isIA()) {
            ((IMonsterStrategy)this.getIA()).initialize(maze);
        }
    }

    /**
     * Méthode {@code update} non utilisé ici.
     */
    @Override
    public void update(Subject subj) {
        // méthode non utilisée ici
    }

    /**
     * Méthode {@code lastShoot} permettant de définir la dernière coordonnée des tirs éffectués par le Chasseur, et de notifier le Chasseur
     * avec les informations de cette case.<br>
     * De plus, si la case est la sortie, le Chasseur n'en est pas informé. 
     * 
     * @param data Ce paramètre représente les coordonées du dernier tir du Chasseur.
     */
    @Override
    public void update(Subject subj, Object data) {
        if(data instanceof ICoordinate && subj instanceof Hunter) {
            Coordinate temp = new Coordinate(((ICoordinate)data).getCol(), ((ICoordinate)data).getRow());
            this.lastShoot = temp;
            notifyObservers(new CellEvent(CellInfo.HUNTER, temp, -1));

            if(temp.equals(Monster.currentCoord)) notifyObservers(new CellEvent(CellInfo.MONSTER, temp, Game.currentTurn));
            else if(Monster.history.containsKey(temp)) notifyObservers(Monster.history.get(temp));
            else if(this.monsterBooleanMaze[temp.getRow()][temp.getCol()]) notifyObservers(new CellEvent(CellInfo.EMPTY, temp, -1));
            else notifyObservers(new CellEvent(CellInfo.WALL, temp, -1));
        }
    }

    /**
     * Méthode {@code getCurrentCoord} permettant de retourner les coodonnées courantes du Monstre.
     * 
     * @return Cette méthode retourne les coodonnées courantes du Monstre.
     */
    public ICoordinate getCurrentCoord(){
        return Monster.currentCoord;
    }

    /**
     * Méthode {@code getLastCoord} permettant de retourner les coodonnées précédentes du Monstre.
     * 
     * @return Cette méthode retourne les coodonnées précédents du Monstre.
     */
    public ICoordinate getLastCoord(){
        return Monster.lastCoord;
    }

    /**
     * Méthode {@code setCurrentCoord} permettant de de définir les coordonnées principales du Monstre, de défnir les coordonnées précédentes du Monste
     * ( qui ici sont les mêmes que ls coordonnées courantes ) et d'ajouter au chemin parcouru par le Monstre, la dernière case parcourue ainsi que le tour correspondant.<br>
     * Cette méthode n'est appelée qu'une seule fois. Dans la classe {@link Board};
     * 
     * @param c Ce paramètre représente les coordonnées permettant de définir l'ensemble des opérations définit dans l'explication.
     */
    public static void setCurrentCoord(ICoordinate c){
        Monster.currentCoord = new Coordinate(c.getCol(), c.getRow());
        Monster.lastCoord = Monster.currentCoord;
        Monster.history.put(Monster.currentCoord,new CellEvent(CellInfo.EMPTY, c, Game.currentTurn));
    }
    
    /**
     * Méthode {@code getLastShoot} permettant de retourner les coordonées du dernier tir du chasseur.
     * 
     * @return Cette méthode retourne les coordonées du dernier tir du Chasseur.
     */
    public ICoordinate getLastShoot(){
        return this.lastShoot;
    }

    /**
     * Méthode {@code setExit()} permettant de définir les coordonées de la sortie pour le Monstre.
     * 
     * @param c Ce paramètre représente les coordonnées de la sortie.
     */
    public static void setExit(ICoordinate c){
        Monster.exit = c;
    }

    /**
     * Méthode {@code getExit()} permettant d'obtenir les coordonées de la sortie.
     * 
     * @return Cette méthode permet d'obtenir les coordonées de la sortie.
     */
    public ICoordinate getExit(){
        return Monster.exit;
    }

    /**
     * Méthode {@code getMaze()} permet d'obtenir les tableau à doubles dimensions de booléen représentant le labyrinthe.
     * 
     * @return Cette méthode retourne le tableau de booléen à double dimensions.s
     */
    public boolean[][] getMaze(){
        return this.monsterBooleanMaze;
    }

    /**
     * Méthode {@code getBadShoots()} permettant d'obtenir la {@code liste} des mauvais déplacements tentés au tour précédent par le Monstre.
     * 
     * @return Cette méthode retourne la {@code liste} des mauvais déplacements tentés au tour précédent par le Monstre.
     */
    public List<ICoordinate> getBadShoots(){
        return this.badShoots;
    }

}
