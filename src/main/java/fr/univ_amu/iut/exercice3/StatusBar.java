package fr.univ_amu.iut.exercice3;

import javafx.beans.binding.StringExpression;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.beans.binding.Bindings.format;
import static javafx.beans.binding.Bindings.when;


public class StatusBar extends BorderPane implements Initializable {
    private static final String MESSAGE_TOUR_NOIR = "Noir joue !";
    private static final String MESSAGE_TOUR_BLANC = "Blanc joue !";
    private static final String SCORE_NOIR = "Noir : ";
    private static final String SCORE_BLANC = "Blanc : ";
    private static final String MESSAGE_TOUR_FIN_PARTIE = "Partie Terminée";

    @FXML
    private Label messageScoreNoir;

    @FXML
    private Label messageScoreBlanc;

    @FXML
    private Label messageTourDeJeu;

    private ObjectProperty<Joueur> joueurCourant = new SimpleObjectProperty<>(Joueur.NOIR);

    public StatusBar() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/univ_amu/iut/exercice3/StatusBarView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createBinding();
    }

    private void createBinding() {
        StringExpression stringScoreNoir = format("%s%d", SCORE_NOIR, Joueur.NOIR.scoreProperty());
        StringExpression stringScoreBlanc = format("%s%d", SCORE_BLANC, Joueur.BLANC.scoreProperty());

        messageScoreNoir.textProperty().bind(stringScoreNoir);
        messageScoreBlanc.textProperty().bind(stringScoreBlanc);

        StringExpression stringTourDeJeu = when(joueurCourant.isEqualTo(Joueur.NOIR))
                .then(MESSAGE_TOUR_NOIR)
                .otherwise(when(joueurCourant.isEqualTo(Joueur.BLANC))
                        .then(MESSAGE_TOUR_BLANC)
                        .otherwise(MESSAGE_TOUR_FIN_PARTIE));

        messageTourDeJeu.textProperty().bind(stringTourDeJeu);

    }

    public Joueur getJoueurCourant() {
        return joueurCourant.get();
    }

    public void setJoueurCourant(Joueur joueurCourant) {
        this.joueurCourant.set(joueurCourant);
    }

    public ObjectProperty<Joueur> joueurCourantProperty() {
        return joueurCourant;
    }


}
