package fr.univartois.butinfo.ihm.bomberman.model;

/**
 * La classe Lawn représente un espace de pelouse sur la map.
 * Il s'agit d'un élément qui peut être traversé, et à travers lequel les explosions se
 * propagent sans résistance.
 *
 * @author Yoann JOLY
 * @version 0.1.0
 */
public class Lawn implements ITileContent {

    /**
     * Constructeur de la classe Lawn.
     */
    public Lawn() {
    }

    @Override
    public String getName() {
        return "lawn";
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isDestroyableByExplosion() {
        return true;
    }

}
