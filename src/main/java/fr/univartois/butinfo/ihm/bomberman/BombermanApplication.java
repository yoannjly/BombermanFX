package fr.univartois.butinfo.ihm.bomberman;

import fr.univartois.butinfo.ihm.bomberman.controller.BombermanController;
import fr.univartois.butinfo.ihm.bomberman.model.Bomberman;
import fr.univartois.butinfo.ihm.bomberman.model.GameMap;
import fr.univartois.butinfo.ihm.bomberman.model.GameMapFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * La classe BombermanApplication est la classe principale du jeu du Bomberman
 * fonctionnant avec JavaFX.
 *
 * @author Yoann JOLY
 * @version 0.1.0
 */
public class BombermanApplication extends Application {

    /**
     * Créé un objet de la classe BombermanApplication.
     */
    public BombermanApplication() {
    }

    /**
     * Cette méthode exécute l'application JavaFX.
     *
     * @param args Les arguments de la ligne de commande (dont on ne tient pas compte).
     * @see #launch(String...)
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Cette méthode permet d'initialiser l'affichage de la fenêtre de l'application.
     *
     * @param stage La fenêtre (initialement vide) de l'application.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Il faut d'abord récupérer la description de la vue (au format FXML).
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/bomberman.fxml"));
        Parent viewContent = fxmlLoader.load();

        // Ensuite, on la place dans une scène.
        Scene scene = new Scene(viewContent);
        // Que l'on place elle-même dans la fenêtre.
        stage.setScene(scene);

        // On peut ensuite donner un titre à la fenêtre.
        stage.setTitle("BombermanFX");

        // On crée ensuite le modèle du jeu.
        GameMap map = GameMapFactory.createMapWithRandomBrickWalls(13, 19, 50);
        Bomberman bomberman = new Bomberman(map, 3);

        // On récupère ensuite le contrôleur.
        BombermanController controller = fxmlLoader.getController();

        // On associe le modèle et le contrôleur.
        bomberman.setController(controller);
        controller.setModel(bomberman);

        // On initialise les events.
        controller.initEvents(scene);

        // Enfin, on lance le jeu et on affiche la fenêtre.
        bomberman.startGame();
        stage.show();
    }

}
