package fr.univ_amu.iut.exercice3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class OthelloMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Othello");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/univ_amu/iut/exercice3/OthelloView.fxml"));
            BorderPane root = loader.load();
            OthelloController controller = loader.getController();
            controller.setStageAndSetupListeners(primaryStage);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
