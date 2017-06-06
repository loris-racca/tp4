package fr.univ_amu.iut.exercice3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Optional;

import static javafx.scene.control.Alert.AlertType;


public class OthelloIHM extends Application {
    private static final int TAILLE = 8;

    private StatusBar statusBar;
    private Othellier othellier;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Othello");
        statusBar = new StatusBar();
        othellier = new Othellier(this, TAILLE);
        BorderPane root = new BorderPane();

        root.setCenter(othellier);
        root.setBottom(statusBar);
        root.setTop(barreDeMenus());
        Scene scene = new Scene(root);

        primaryStage.setOnCloseRequest(event -> {
            this.actionMenuJeuQuitter();
            event.consume();
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void updateStatus() {
        if (othellier.getJoueurCourant() == Joueur.PERSONNE) {
            afficheDialogFinDePartie();
        }

        statusBar.setJoueurCourant(othellier.getJoueurCourant());
        statusBar.updateStatus();
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

    private MenuBar barreDeMenus() {
        MenuBar barre = new MenuBar();
        barre.getMenus().add(creerMenuJeu());
        return barre;
    }

    private Menu creerMenuJeu() {
        Menu menuJeu = new Menu("Jeu");
        menuJeu.getItems().add(creerMenuJeuNouveau());
        menuJeu.getItems().add(creerMenuJeuQuitter());
        return menuJeu;
    }

    private MenuItem creerMenuJeuNouveau() {
        MenuItem menu = new MenuItem("Nouveau");
        menu.setOnAction(event -> actionMenuJeuNouveau());
        return menu;
    }

    private MenuItem creerMenuJeuQuitter() {
        MenuItem menu = new MenuItem("Quitter");
        menu.setOnAction(event -> actionMenuJeuQuitter());
        return menu;
    }

    private void actionMenuJeuQuitter() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Êtes vous certain de vouloir quitter l'application ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    private void actionMenuJeuNouveau() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Êtes vous certain de vouloir créer une nouvelle partie ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            othellier.nouvellePartie();
        }
    }
}
