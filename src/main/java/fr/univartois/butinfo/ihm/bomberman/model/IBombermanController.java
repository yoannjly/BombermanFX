package fr.univartois.butinfo.ihm.bomberman.model;

/**
 * L'interface IBombermanController définit le contrat qui doit être respecté par
 * n'importe quel contrôleur du jeu du Bomberman.
 *
 * @author Yoann JOLY
 * @version 0.1.0
 */
public interface IBombermanController {

    /**
     * Modifie le modèle du jeu qui doit être affiché par la vue.
     *
     * @param bomberman Le modèle du jeu, qui gère la partie en cours.
     */
    void setModel(Bomberman bomberman);

    /**
     * Initialise la map du jeu du Bomberman.
     *
     * @param map La map du jeu.
     */
    void initMap(GameMap map);

    /**
     * Initialise le joueur du Bomberman.
     *
     * @param player Le personnage du joueur.
     */
    void initPlayer(Player player);

}
