package fr.univartois.butinfo.ihm.bomberman.model;

/**
 * L'interface ITileContent définit le contrat qui doit être respecté par les différents
 * éléments qui constituent la map du jeu du Bomberman.
 *
 * @author Yoann JOLY
 * @version 0.1.0
 */
public interface ITileContent {

    /**
     * Donne le nom de cet élément de la map.
     *
     * @return Le nom de cet élément.
     */
    String getName();

    /**
     * Vérifie si cet élement est considéré comme vide.
     * Un élément vide est un élément sur lequel un personnage peut se déplacer.
     *
     * @return Si cet élement est vide.
     */
    boolean isEmpty();

    /**
     * Détermine si une explosion peut détruire cet élément.
     *
     * @return Si une explosion a un effet sur cet élément.
     */
    boolean isDestroyableByExplosion();

}
