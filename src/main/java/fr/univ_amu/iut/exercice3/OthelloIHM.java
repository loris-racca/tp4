package fr.univ_amu.iut.exercice3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Optional;

import static javafx.scene.control.Alert.AlertType;


public class OthelloIHM extends Application {
    @FXML
    private StatusBar statusBar;

    @FXML
    private Othellier othellier;

    @FXML
    private MenuBar menuBar;

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    void initialize() {
        othellier.joueurCourantProperty().addListener((observable, oldValue, newValue) -> {
            if (othellier.getJoueurCourant() == Joueur.PERSONNE) {
                afficheDialogFinDePartie();
            }
        });

        statusBar.joueurCourantProperty().bind(othellier.joueurCourantProperty());

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Othello");

        try {
            BorderPane root = FXMLLoader.load(getClass().getResource("/fr/univ_amu/iut/exercice3/OthelloIHM.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

            primaryStage.setOnCloseRequest(event -> {
                this.actionMenuJeuQuitter();
                event.consume();
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void afficheDialogFinDePartie() {
        String messageFinDePartie;

        if (Joueur.BLANC.getScore() > Joueur.NOIR.getScore())
            messageFinDePartie = "Blanc a gagné !!!";
        else if (Joueur.BLANC.getScore() < Joueur.NOIR.getScore())
            messageFinDePartie = "Noir a gagné !!!";
        else
            messageFinDePartie = "Égalité !!!";

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Fin de partie");
        alert.setContentText(messageFinDePartie);
        alert.showAndWait();
    }

    @FXML
    public void actionMenuJeuQuitter() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Êtes vous certain de vouloir quitter l'application ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    @FXML
    public void actionMenuJeuNouveau() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Êtes vous certain de vouloir créer une nouvelle partie ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            othellier.nouvellePartie();
        }
    }
}
