package fr.univartois.butinfo.ihm.bomberman.model;

/**
 * La classe Bomb représente une bombe qui peut être déposée sur une tuile de la map afin
 * de faire exploser le contenu des tuiles environnantes.
 *
 * @author Yoann JOLY
 * @version 0.1.0
 */
public class Bomb {

    /**
     * La map du jeu où cette bombe a été déposée.
     */
    protected GameMap gameMap;

    /**
     * La ligne de la map où cette bombe a été déposée.
     */
    protected int row;

    /**
     * La colonne de la map où cette bombe a été déposée.
     */
    protected int column;

    /**
     * Constructeur de la classe Bomb.
     */
    public Bomb() {
    }

    /**
     * Modifie la map du jeu où cette bombe a été déposée.
     *
     * @param gameMap La map du jeu où cette bombe a été déposée.
     */
    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    /**
     * Modifie la position où cette bombe a été déposée.
     *
     * @param row    La ligne de la map où cette bombe a été déposée.
     * @param column La colonne de la map où cette bombe a été déposée.
     */
    public void setPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Donne le nom de cette bombe.
     *
     * @return Le nom de cette bombe.
     */
    public String getName() {
        return "bomb";
    }

    /**
     * Donne le délai entre le moment où cette bombe est posée et le moment où elle
     * explose (en secondes).
     *
     * @return Le délai avant l'explosion de cette bombe.
     */
    public int getDelay() {
        return 3;
    }

    /**
     * Fait exploser cette bombe, ce qui provoque une explosion sur les tuiles voisines.
     */
    public void explode() {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (gameMap.isOnMap(row + i, column + j)) {
                    gameMap.get(row + i, column + j).explode();
                }
            }
        }
    }

    @Override
    public String toString() {
        return getName();
    }

}
