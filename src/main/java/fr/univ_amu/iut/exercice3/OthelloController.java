package fr.univ_amu.iut.exercice3;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

import static javafx.scene.control.Alert.AlertType;


public class OthelloController {
    @FXML
    private StatusBar statusBar;

    @FXML
    private Othellier othellier;

    @FXML
    void initialize() {
        othellier.joueurCourantProperty().addListener((observable, oldValue, newValue) -> {
            if (othellier.getJoueurCourant() == Joueur.PERSONNE) {
                afficheDialogFinDePartie();
            }
        });

        statusBar.joueurCourantProperty().bind(othellier.joueurCourantProperty());
    }

    void setStageAndSetupListeners(Stage stage) {
        stage.setOnCloseRequest(event -> {
            this.actionMenuJeuQuitter();
            event.consume();
        });
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
