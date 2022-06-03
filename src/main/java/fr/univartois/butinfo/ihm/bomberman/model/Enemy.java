package fr.univartois.butinfo.ihm.bomberman.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * La classe Enemy représente un adversaire du joueur dans le jeu du Bomberman.
 *
 * @author Yoann JOLY
 * @version 0.1.0
 */
public class Enemy extends AbstractCharacter {

    /**
     * Le nom de ce personnage.
     */
    private final String name;

    /**
     * La Timeline permettant à ce personnage de se déplacer seul.
     */
    private Timeline timeline;

    /**
     * Construit un nouvel Enemy.
     *
     * @param name      Le nom du personnage.
     * @param bomberman Le jeu du Bomberman, dans lequel le personnage se déplace.
     */
    public Enemy(String name, Bomberman bomberman) {
        super(1, bomberman);
        this.name = name;
    }

    private void moveRandomly() {
        int move = Bomberman.RANDOM.nextInt(4);
        switch (move) {
            case 0 -> bomberman.moveUp(this);
            case 1 -> bomberman.moveLeft(this);
            case 2 -> bomberman.moveDown(this);
            case 3 -> bomberman.moveRight(this);
        }
    }

    /**
     * Méthode permettant de faire bouger les enemies toutes les secondes.
     */
    public void animate() {
        this.timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> moveRandomly()));
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void decHealth() {
        super.decHealth();
        if (getHealth() == 0) {
            timeline.stop();
        }
    }

}
