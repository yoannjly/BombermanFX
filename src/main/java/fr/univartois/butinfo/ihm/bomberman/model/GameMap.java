package fr.univartois.butinfo.ihm.bomberman.model;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe GameMap représente la map du jeu du Bomberman, sur laquelle les personnages
 * se déplacent et peuvent poser des bombes.
 *
 * @author Yoann JOLY
 * @version 0.1.0
 */
public class GameMap {

    /**
     * Le nombre de lignes de tuiles dans cette map.
     */
    private int height;

    /**
     * Le nombre de colonnes de tuiles dans cette map.
     */
    private int width;

    /**
     * Les tuiles qui constituent cette map.
     */
    private Tile[][] tiles;

    /**
     * Construit une nouvelle instance de GameMap.
     *
     * @param width  Le nombre de lignes de tuiles dans la map.
     * @param height Le nombre de colonnes de tuiles dans la map.
     */
    public GameMap(int height, int width) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[height][width];
        init();
    }

    /**
     * Crée les tuiles qui constituent cette map.
     */
    private void init() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tiles[i][j] = new Tile(i, j);
            }
        }
    }

    /**
     * Donne le nombre de lignes de tuiles dans cette map.
     *
     * @return Le nombre de lignes de tuiles dans cette map.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Donne le nombre de colonnes de tuiles dans cette map.
     *
     * @return Le nombre de colonnes de tuiles dans cette map.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Vérifie si une position se trouve sur cette map.
     *
     * @param row    L'indice de ligne à vérifier.
     * @param column L'indice de colonne à vérifier.
     * @return Si la position se trouve bien sur la map.
     */
    public boolean isOnMap(int row, int column) {
        return ((0 <= row) && (row < height))
                && ((0 <= column) && (column < width));
    }

    /**
     * Donne une tuile à une position donnée.
     *
     * @param row    La ligne de la tuile à récupérer.
     * @param column La colonne de la tuile à récupérer.
     * @return La tuile à la position donnée.
     */
    public Tile get(int row, int column) {
        return tiles[row][column];
    }

    /**
     * Donne la liste des tuiles qui sont vides sur cette map.
     *
     * @return La liste des tuiles vides.
     * @see Tile#isEmpty()
     */
    public List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (tiles[i][j].isEmpty()) {
                    emptyTiles.add(tiles[i][j]);
                }
            }
        }

        return emptyTiles;
    }

}
