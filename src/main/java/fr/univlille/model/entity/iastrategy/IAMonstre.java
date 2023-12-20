package main.java.fr.univlille.model.entity.iastrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import fr.univlille.iutinfo.cam.player.monster.IMonsterStrategy;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import main.java.fr.univlille.model.entity.Monster;


/**
 * Classe {@code IAMonstre} permettant l'interface {@code IMonsterStrategy} et permettant de créer une "Intelligence Artificielle" 
 * qui jouera contre le Joueur humain ou contre l'IA du Monstre. <br>
 * Elle implémente l'algorithme de parcours en largeur pour trouver le plus court chemin vers la sortie. <br>
 * Grâce à ce chemin, le monstre ce déplace de façon "intelligente".
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 */
public class IAMonstre implements IMonsterStrategy{
    private ICellEvent[][] mazeMonster;
    private ICoordinate currentCoord;
    private ICoordinate exit;
    private static final Random RD = new Random();

    /**
     * Méthode {@code play()} hérité de {@code IMonsterStrategy} qui permet à l'IA du Monstre de se déplacer 
     * dans le labyrinthe. <br>
     * 
     * @return Cette méthode retourne une instance de {@code InnerIAMonsterCoordinate}. Autrement dit une coordonnée.
     */
    @Override
    public ICoordinate play() {
        return chooseNextMove();
    }

    /**
     * Méthode {@code update()} hérité de l'interface {@code IMonsterStrategy} qui permet de mettre à jour 
     * la position courante du Monstre.
     */
    @Override
    public void update(ICellEvent info) {
        if (info != null && info.getCoord() != null) {
            this.currentCoord = info.getCoord();
        }
    }

    /**
     * Méthode {@code initialize} permettant d'initialiser un labyrinthe à partir d'un tableau de booléen.
     * 
     * @param maze Le tableau de booléen représentant le labyrinthe.
     */
    @Override
    public void initialize(boolean[][] maze) {
        this.mazeMonster = new ICellEvent[maze.length][maze[0].length]; 

        Monster monster = new Monster();

        this.currentCoord = monster.getCurrentCoord();
        this.exit = monster.getExit();

        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                this.mazeMonster[row][col] = new InnerIAMonstreCellEvent(!maze[row][col] ? CellInfo.WALL : CellInfo.EMPTY);
            }
        }
    }

    /**
     * Methode {@code findShortestPath} permettant de rechercher le plus court chemin entre deux points. <br>
     * 
     * @param start Ce paramètre représente le point de départ.
     * @param goal Ce paramètre représente le point d'arrivée.
     * 
     * @return Cette méthode retourne la {@code liste} des coordonnées représentant le plus court chemin.
     */
    private List<ICoordinate> findShortestPath(ICoordinate start, ICoordinate goal) {
        Queue<List<ICoordinate>> queue = new LinkedList<>();
        Set<ICoordinate> visited = new HashSet<>();
        queue.add(new ArrayList<>(Collections.singletonList(start)));
        visited.add(start);
    
        while (!queue.isEmpty()) {
            List<ICoordinate> path = queue.poll();
            ICoordinate current = path.get(path.size() - 1);
    
            if (current.getCol() == goal.getCol() && current.getRow() == exit.getRow()) {
                // Chemin trouvé
                return path;
            }
    
            for (ICoordinate neighbor : possibility(current, path)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    List<ICoordinate> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }
        // Pas de chemin trouvé
        return Collections.emptyList();
    }

    /**
     * Méthode {@code chooseNextMove} permettant de choisir le prochain mouvement du monstre.
     * 
     * @return Cette méthode retourne le prochain mouvement du monstre.
     */
    private ICoordinate chooseNextMove() {
        List<ICoordinate> path = findShortestPath(currentCoord, exit);
    
        // Si un chemin est trouvé, retourne le prochain mouvement
        if (!path.isEmpty() && path.size() > 1) {
            return path.get(1); // Le premier élément est la position actuelle
        } else {
            // Aucun chemin trouvé, retourne un mouvement aléatoire parmi les possibilités
            List<ICoordinate> possibleMoves = possibility(currentCoord, path);  
            return possibleMoves.isEmpty() ? currentCoord : possibleMoves.get(RD.nextInt(possibleMoves.size()));
        }
    }

    /**
     * Méthode {@code possibility} permettant de lister les mouvements possibles à partir d'une position donnée, en excluant le chemin actuel.
     * 
     * @param current Ce paramètre représente la position actuelle.
     * @param path Ce paramètre représente le chemin actuel.
     * 
     * @return Cette méthode retourne la {@code liste} des mouvements possibles.
     */
    public List<ICoordinate> possibility(ICoordinate current, List<ICoordinate> path) {
        List<ICoordinate> liste = new ArrayList<>();
        for (ICoordinate iCoordinate : around(current)) {
            if (inRange(iCoordinate) && getCellEvent(iCoordinate).getState() != CellInfo.WALL && !path.contains(iCoordinate)) {
                liste.add(iCoordinate);
            }
        }
        return liste;
    }

    /**
     * Méthode {@code around} permettant de lister les voisins d'une coordonnée donnée.
     * 
     * @param coord Ce paramètre représente la coordonnée.
     * 
     * @return Cette méthode retourne la {@code liste} des voisins de la coordonée passée en paramètre.
     */
    private List<ICoordinate> around(ICoordinate coord) {
        List<ICoordinate> liste = new ArrayList<>();
        int row = coord.getRow();
        int col = coord.getCol();
        liste.add(new InnerIAMonstreCoordinate(col + 1, row));
        liste.add(new InnerIAMonstreCoordinate(col - 1, row));
        liste.add(new InnerIAMonstreCoordinate(col, row + 1));
        liste.add(new InnerIAMonstreCoordinate(col, row - 1));
        return liste;
    }

    /**
     * Méthode {@code inRange} permettant de vérifier si une coordonnée est dans les limites du labyrinthe.
     * 
     * @param coord Ce paramètre représente la coordonnée.
     * 
     * @return Cette méthode retourne un {@code booléen} selon si la coordonnée est valide ou non.
     */
    public boolean inRange(ICoordinate coord){
        return (coord.getRow() >= 0 && coord.getRow() < mazeMonster.length) && 
                (coord.getCol() >= 0 && coord.getCol() < mazeMonster[0].length);
    }

    /**
     * Méthode {@code getCellEvent} permettant de récupèrer l'événement de cellule pour une coordonnée donnée.
     * 
     * @param coord Ce paramètre représente la coordonnée donnée pour récuperer l'événement correspondant.
     * 
     * @return Cette méthode retourne l'événement de cellule correspondant à la coordonnée.
     */
    public ICellEvent getCellEvent(ICoordinate coord){
        return this.mazeMonster[coord.getRow()][coord.getCol()];
    }    

    /**
     * 
     * Classe {@code InnerIAMonstreCellEvent} permettant l'implémentation de linterface {@code ICellEvent}.<br>
     * Cette implémentation permet d'avoir accès au méthodes de {@code ICellEvent}. <br>
     * Cette classes est une classe interne à la classe {@code IAMonster} ce qui permet la portabilité de la classe {@code IAMonster}
     * dans le jeu des autres équipes.
     * 
     * @author Baptiste Bertout
     * @author Pierre Planchon
     * @author Arthur Keller
     * @author Gaspard Souliez
     * @author Mathis Decoster
     */
    public class InnerIAMonstreCellEvent implements ICellEvent{
        private CellInfo info;
        private ICoordinate coord;
        private int turn;

        public InnerIAMonstreCellEvent(CellInfo info){
            this.info = info;
        }

        @Override
        public ICoordinate getCoord() {
            return this.coord;
        }

        @Override
        public CellInfo getState() {
            return this.info;
        }

        @Override
        public int getTurn() {
            return this.turn;
        }
    }

    
    /**
     * 
     * Classe {@code InnerIAMonstreCoordinate} permettant l'implémentation de linterface {@code ICoordinate}.<br>
     * Cette implémentation permet d'avoir accès au méthodes de {@code ICoordinate}. <br>
     * Cette classes est une classe interne à la classe {@code IAMonster} ce qui permet la portabilité de la classe {@code IAMonster}
     * dans le jeu des autres équipes.
     * 
     * @author Baptiste Bertout
     * @author Pierre Planchon
     * @author Arthur Keller
     * @author Gaspard Souliez
     * @author Mathis Decoster
     */
    public class InnerIAMonstreCoordinate implements ICoordinate{
        private int row;
        private int col;

        public InnerIAMonstreCoordinate(int col, int row){
            this.col = col;
            this.row = row;
        }

        @Override
        public int getCol() {
            return this.col;
        }

        @Override
        public int getRow() {
            return this.row;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + row;
            result = prime * result + col;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ICoordinate other = (ICoordinate) obj;
            if (row != other.getRow())
                return false;
            return col == other.getCol();
        }
    }
}