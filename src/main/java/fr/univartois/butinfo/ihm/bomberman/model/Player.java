package fr.univartois.butinfo.ihm.bomberman.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * la classe Player représente le personnage du joueur qui utilise l'application.
 *
 * @author Yoann JOLY
 * @version 0.1.0
 */
public class Player extends AbstractCharacter {

    /**
     * Nombre de bombes que le joueur dispose au début de la partie.
     */
    public static final int NB_BOMBS = 20;

    /**
     * Le nombre de bombes disponibles pour le joueur.
     */
    private IntegerProperty bombsAmount;

    /**
     * Construit un nouveau Player.
     *
     * @param bomberman Le jeu du Bomberman, dans lequel le personnage se déplace.
     */
    public Player(Bomberman bomberman) {
        super(3, bomberman);
        bombsAmount = new SimpleIntegerProperty(NB_BOMBS);
    }

    /**
     * Donne le nombre de bombes restantes au joueur.
     *
     * @return Le nombre de bombes restantes au joueur.
     */
    public int getBombsAmount() {
        return bombsAmount.get();
    }

    /**
     * Donne la propriété du nombre de bombes restantes au joueur.
     *
     * @return La propriété du nombre de bombes restantes au joueur.
     */
    public IntegerProperty getBombsAmountProperty() {
        return bombsAmount;
    }

    /**
     * Modifie le nombre de bombes restantes au joueur.
     *
     * @param bombsAmount Nombre de bombes restantes.
     */
    public void setBombsAmount(int bombsAmount) {
        this.bombsAmount.set(bombsAmount);
    }

    @Override
    public String getName() {
        return "guy";
    }

}
