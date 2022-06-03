package fr.univartois.butinfo.ihm.bomberman.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.Duration;

/**
 * La classe Tile représente une tuile composant la map du jeu du Bomberman.
 * Une fois créée, une telle tuile devient fixe sur la map : c'est son contenu qui change
 * au cours du jeu.
 *
 * @author Yoann JOLY
 * @version 0.1.0
 */
public class Tile {

    /**
     * La ligne où cette tuile est positionnée sur la map.
     */
    private final int row;

    /**
     * La colonne où cette tuile est positionnée sur la map.
     */
    private final int column;

    /**
     * Le contenu de cette tuile.
     */
    private final ObjectProperty<ITileContent> content;

    private final ObjectProperty<AbstractCharacter> character;

    private final ObjectProperty<Bomb> bomb;

    private final BooleanProperty exploding;

    /**
     * Construit une nouvelle instance de Tile.
     *
     * @param row    La ligne où la tuile est positionnée sur la map.
     * @param column La colonne où la tuile est positionnée sur la map.
     */
    public Tile(int row, int column) {
        this.row = row;
        this.column = column;
        content = new SimpleObjectProperty<>();
        character = new SimpleObjectProperty<>();
        bomb = new SimpleObjectProperty<>();
        exploding = new SimpleBooleanProperty();
    }

    /**
     * Donne la ligne où cette tuile est positionnée sur la map.
     *
     * @return La ligne où cette tuile est positionnée sur la map.
     */
    public int getRow() {
        return row;
    }

    /**
     * Donne la colonne où cette tuile est positionnée sur la map.
     *
     * @return La colonne où cette tuile est positionnée sur la map.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Donne le contenu de cette tuile.
     *
     * @return Le contenu de cette tuile.
     */
    public ITileContent getContent() {
        return content.getValue();
    }

    /**
     * Donne la propriété représentant le contenu de cette tuile.
     *
     * @return La propriété représentant le contenu de cette tuile.
     */
    public ObjectProperty<ITileContent> getPropertyContent() {
        return content;
    }

    /**
     * Change le contenu de cette tuile.
     *
     * @param content Le nouveau contenu de cette tuile.
     */
    public void setContent(ITileContent content) {
        this.content.set(content);
    }

    /**
     * Donne l'éventuel personnage qui peut se trouver sur cette tuile.
     *
     * @return L'éventuel personnage qui peut se trouver sur cette tuile.
     */
    public AbstractCharacter getCharacter() {
        return character.getValue();
    }

    /**
     * Donne la propriété représentant l'éventuel personnage qui peut se trouver
     * sur cette tuile.
     *
     * @return La propriété représentant l'éventuel personnage sur cette tuile.
     */
    public ObjectProperty<AbstractCharacter> getCharacterProperty() {
        return character;
    }

    /**
     * Modifie le personnage se trouvant sur cette tuile.
     *
     * @param character Le nouveau personnage sur cette tuile.
     */
    public void setCharacter(AbstractCharacter character) {
        this.character.set(character);
    }

    /**
     * Retire le personnage actuellement présent sur cette tuile.
     */
    public void removeCharacter() {
        character.set(null);
    }

    /**
     * Donne la propriété représentant l'éventuelle bombe qui peut se trouver
     * sur cette tuile.
     *
     * @return La propriété représentant l'éventuelle bombe sur cette tuile.
     */
    public ObjectProperty<Bomb> getBombProperty() {
        return bomb;
    }

    /**
     * Modifie la bombe se trouvant sur cette tuile.
     *
     * @param bomb La nouvelle bombe sur cette tuile.
     */
    public void setBomb(Bomb bomb) {
        this.bomb.set(bomb);
    }

    /**
     * Retire la bombe actuellement présente sur cette tuile.
     */
    public void removeBomb() {
        bomb.set(null);
    }

    /**
     * Donne la propriété représentant l'éventuelle explosion qui peut se trouver
     * sur cette tuile.
     *
     * @return La propriété représentant l'éventuelle explosion sur cette tuile.
     */
    public BooleanProperty getExploding() {
        return exploding;
    }

    /**
     * Modifie l'état d'explosion se trouvant sur cette tuile.
     *
     * @param exploding L'état d'explosion se trouvant sur cette tuile.
     */
    public void setExploding(boolean exploding) {
        this.exploding.set(exploding);
    }

    /**
     * Vérifie si cette tuile est vide, c'est-à-dire que son contenu est vide et qu'aucun
     * personnage n'est présent sur cette tuile.
     *
     * @return Si cette tuile est vide.
     * @see ITileContent#isEmpty()
     */
    public boolean isEmpty() {
        return content.get().isEmpty() && character.getValue() == null;
    }

    /**
     * Fait exploser cette tuile.
     */
    public void explode() {
        if (getContent().isDestroyableByExplosion()) {

            AbstractCharacter character = getCharacter();
            if (character != null) character.decHealth();

            setExploding(true);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
                setExploding(false);
                setContent(new Lawn());
            }));
            timeline.play();
        }
    }

}
