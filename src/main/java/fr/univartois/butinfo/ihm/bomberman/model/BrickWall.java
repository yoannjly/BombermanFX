package fr.univartois.butinfo.ihm.bomberman.model;

/**
 * La classe BrickWall représente un mur de briques sur la map.
 * Il s'agit d'un élément qui ne peut pas être traversé, mais qui peut être détruit par
 * une explosion.
 *
 * @author Yoann JOLY
 * @version 0.1.0
 */
public class BrickWall implements ITileContent {

    /**
     * Constructeur de la classe BrickWall.
     */
    public BrickWall() {
    }

    @Override
    public String getName() {
        return "bricks";
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isDestroyableByExplosion() {
        return true;
    }

}
