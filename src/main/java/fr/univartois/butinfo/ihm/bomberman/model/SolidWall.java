package fr.univartois.butinfo.ihm.bomberman.model;

/**
 * La classe SolidWall représente un mur de pierres solide sur la map.
 * Il s'agit d'un élément qui ne peut pas être traversé, et qui résiste aux explosions.
 *
 * @author Yoann JOLY
 * @version 0.1.0
 */
public class SolidWall implements ITileContent {

    /**
     * Constructeur de la classe SolidWall.
     */
    public SolidWall() {
    }

    @Override
    public String getName() {
        return "wall";
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isDestroyableByExplosion() {
        return false;
    }

}
