package fr.univartois.butinfo.ihm.bomberman.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * La classe Bomberman gère une partie du jeu.
 *
 * @author Yoann JOLY
 * @version 0.1.0
 */
public class Bomberman {

    /**
     * Le génarateur de nombres aléatoires utilisé dans le jeu.
     */
    public static final Random RANDOM = new Random();

    /**
     * La map du jeu.
     */
    private final GameMap gameMap;

    /**
     * Le personnage du joueur.
     */
    private final Player player;

    /**
     * Les personnages des adversaires du joueur.
     */
    private final List<Enemy> enemies;

    /**
     * Le contrôleur de l'application.
     */
    private IBombermanController controller;

    /**
     * Construit une nouvelle instance de Bomberman.
     *
     * @param gameMap   La map du jeu.
     * @param nbEnemies Le nombre d'adversaires à affronter.
     */
    public Bomberman(GameMap gameMap, int nbEnemies) {
        this.gameMap = gameMap;
        this.player = new Player(this);
        enemies = new ArrayList<>(nbEnemies);
        String[] enemiesName = new String[]{
                "agent", "bomb", "bricks", "chest", "column-bomb", "explosion", "goblin",
                "guy", "heart", "large-bomb", "lawn", "minotaur", "punker", "rourke", "row-bomb", "wall"
        };
        for (String enemyName : enemiesName) {
            enemies.add(new Enemy(enemyName, this));
        }
    }

    /**
     * Modifie le contrôleur avec lequel interagir pour mettre à jour l'affichage.
     *
     * @param controller Le contrôleur avec lequel interagir.
     */
    public void setController(IBombermanController controller) {
        this.controller = controller;
    }

    /**
     * Démarre une partie.
     */
    public void startGame() {
        controller.initMap(gameMap);
        controller.initPlayer(player);
        addCharacter(player);
        for (Enemy enemy : enemies) {
            addCharacter(enemy);
            enemy.animate();
        }
    }

    /**
     * Ajoute un personnage dans le jeu.
     *
     * @param character Le personnage à ajouter.
     */
    public void addCharacter(AbstractCharacter character) {
        List<Tile> availableTiles = gameMap.getEmptyTiles();
        int randIndex = RANDOM.nextInt(availableTiles.size());
        Tile tile = availableTiles.get(randIndex);
        tile.setCharacter(character);
        character.setPosition(tile.getRow(), tile.getColumn());
    }

    /**
     * Retire un personnage de la map du jeu.
     *
     * @param character Le character à retirer.
     */
    public void removeCharacter(AbstractCharacter character) {
        gameMap.get(character.getRow(), character.getColumn()).removeCharacter();
    }

    /**
     * Place une bombe dans le jeu.
     * Utilisable par le joueur uniquement.
     */
    public void placeBomb() {
        if (player.getBombsAmount() > 0) {
            player.setBombsAmount(player.getBombsAmount() - 1);
            Bomb bomb = new Bomb();

            int row = player.getRow();
            int column = player.getColumn();

            bomb.setPosition(row, column);
            bomb.setGameMap(gameMap);
            gameMap.get(player.getRow(), player.getColumn()).setBomb(bomb);

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(bomb.getDelay()), e -> {
                bomb.explode();
                gameMap.get(row, column).removeBomb();
            }));
            timeline.play();
        }
    }

    /**
     * Déplace le personnage du joueur vers le haut.
     */
    public void moveUp() {
        moveUp(player);
    }

    /**
     * Déplace le personnage du joueur vers la droite.
     */
    public void moveRight() {
        moveRight(player);
    }

    /**
     * Déplace le personnage du joueur vers le bas.
     */
    public void moveDown() {
        moveDown(player);
    }

    /**
     * Déplace le personnage du joueur vers la gauche.
     */
    public void moveLeft() {
        moveLeft(player);
    }

    /**
     * Déplace un personnage vers le haut.
     *
     * @param character Le personnage à déplacer.
     */
    public void moveUp(AbstractCharacter character) {
        move(character, -1, 0);
    }

    /**
     * Déplace un personnage vers la droite.
     *
     * @param character Le personnage à déplacer.
     */
    public void moveRight(AbstractCharacter character) {
        move(character, 0, +1);
    }

    /**
     * Déplace un personnage vers le bas.
     *
     * @param character Le personnage à déplacer.
     */
    public void moveDown(AbstractCharacter character) {
        move(character, +1, 0);
    }

    /**
     * Déplace un personnage vers la gauche.
     *
     * @param character Le personnage à déplacer.
     */
    public void moveLeft(AbstractCharacter character) {
        move(character, 0, -1);
    }

    /**
     * Déplace un personnage dans une direction donnée.
     *
     * @param character   Le personnage à déplacer.
     * @param rowShift    Le déplacement à réaliser en ligne.
     * @param columnShift Le déplacement à réaliser en colonne.
     */
    private void move(AbstractCharacter character, int rowShift, int columnShift) {
        int row = character.getRow();
        int column = character.getColumn();

        if (gameMap.isOnMap(row + rowShift, column + columnShift)
                && gameMap.get(row + rowShift, column + columnShift).isEmpty()) {
            gameMap.get(row, column).removeCharacter();
            gameMap.get(row + rowShift, column + columnShift).setCharacter(character);
            character.setPosition(row + rowShift, column + columnShift);
        }
    }

}
