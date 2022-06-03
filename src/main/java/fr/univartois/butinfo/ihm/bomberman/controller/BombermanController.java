package fr.univartois.butinfo.ihm.bomberman.controller;

import fr.univartois.butinfo.ihm.bomberman.model.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import java.net.URL;

/**
 * La classe BombermanController propose un contrôleur permettant de gérer une partie du
 * Bomberman présentée à l'utilisateur sous la forme d'une interface graphique JavaFX.
 *
 * @author Yoann JOLY
 * @version 0.1.0
 */
public class BombermanController implements IBombermanController {

    /**
     * Le conteneur permettant d'afficher la map du jeu.
     */
    @FXML
    private GridPane gridPane;

    /**
     * Le modèle gérant la partie en cours.
     */
    private Bomberman bomberman;

    /**
     * Les boutons représentant les tuiles du jeu du Taquin.
     */
    private Label[][] labels;

    /**
     * Le label affichant la vie du joueur.
     */
    @FXML
    private Label playerHealth;

    /**
     * Le label affichant le nombre de bombes restantes au joueur.
     */
    @FXML
    private Label playerBombs;

    /**
     * Constructeur de la classe BombermanController.
     */
    public BombermanController() {
    }

    /**
     * Configure la {@link Scene} sur laquelle la partie de Bomberman est affichée.
     *
     * @param scene La Scene sur laquelle le jeu est affiché.
     */
    public void initEvents(Scene scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            switch (e.getCode()) {
                case UP, Z -> bomberman.moveUp();
                case LEFT, Q -> bomberman.moveLeft();
                case DOWN, S -> bomberman.moveDown();
                case RIGHT, D -> bomberman.moveRight();
                case SPACE -> bomberman.placeBomb();
            }
            e.consume();
        });
    }

    @Override
    public void setModel(Bomberman bomberman) {
        this.bomberman = bomberman;
    }

    @Override
    public void initMap(GameMap map) {
        int height = map.getHeight();
        int width = map.getWidth();
        labels = new Label[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                labels[i][j] = initLabel(i, j, map);
            }
        }
    }

    /**
     * Initialise un label à un emplacement défini de la map.
     *
     * @param i   Numéro de ligne.
     * @param j   Numéro de colonne.
     * @param map La map du jeu.
     */
    private Label initLabel(int i, int j, GameMap map) {
        Label label = new Label();
        gridPane.add(label, j, i);
        label.setPrefHeight(50);
        label.setPrefWidth(50);
        label.setBackground(createBackground(map.get(i, j).getContent()));
        map.get(i, j).getCharacterProperty().addListener(
                (p, o, n) -> {
                    ImageView view = new ImageView(createImageFor(n));
                    view.setFitHeight(50);
                    view.setFitWidth(50);
                    label.setGraphic(view);
                });
        map.get(i, j).getBombProperty().addListener(
                (p, o, n) -> {
                    ImageView view = new ImageView(loadImage(n.getName()));
                    view.setFitHeight(50);
                    view.setFitWidth(50);
                    label.setGraphic(view);
                });
        map.get(i, j).getExploding().addListener(
                (p, o, n) -> {
                    if (Boolean.TRUE.equals(n)) {
                        ImageView view = new ImageView(loadImage("explosion"));
                        view.setFitHeight(50);
                        view.setFitWidth(50);
                        label.setGraphic(view);
                    } else {
                        label.setGraphic(null);
                    }
                });
        map.get(i, j).getPropertyContent().addListener(
                (p, o, n) -> label.setBackground(createBackground(map.get(i, j).getContent()))
        );
        return label;
    }

    @Override
    public void initPlayer(Player player) {
        playerHealth.textProperty().bind(player.getHealthProperty().asString());
        playerBombs.textProperty().bind(player.getBombsAmountProperty().asString());
    }

    /**
     * Détermine l'arrière-plan de la tuile donnée, afin d'afficher son contenu.
     *
     * @param content La tuile pour laquelle l'arrière-plan doit être déterminé.
     * @return L'arrière-plan associé à la tuile.
     */
    private Background createBackground(ITileContent content) {
        BackgroundImage backgroundImage = new BackgroundImage(
                loadImage(content.getName()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        return new Background(backgroundImage);
    }

    /**
     * Crée l'image permenttant d'afficher le personnage donné sur la map.
     *
     * @param character Le personnage à afficher.
     * @return L'image du personnage.
     */
    private Image createImageFor(AbstractCharacter character) {
        if (character == null) {
            return null;
        }

        return loadImage(character.getName());
    }

    /**
     * Charge une image depuis les ressources de l'application.
     *
     * @param name Le nom de l'image à charger.
     * @return L'image qui a été chargée.
     */
    private Image loadImage(String name) {
        URL urlImage = getClass().getResource("../view/images/" + name + ".png");
        assert urlImage != null;
        return new Image(urlImage.toExternalForm(), 50, 50, true, false);
    }

}
