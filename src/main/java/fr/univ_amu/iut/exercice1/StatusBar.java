package fr.univ_amu.iut.exercice1;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;



class StatusBar extends BorderPane {
    private static final String MESSAGE_TOUR_NOIR = "Noir joue !";
    private static final String MESSAGE_TOUR_BLANC = "Blanc joue !";

    private static final String SCORE_NOIR = "Noir : ";
    private static final String SCORE_BLANC = "Blanc : ";
    private static final String MESSAGE_TOUR_FIN_PARTIE = "Partie Terminée";

    private Joueur joueurCourant = Joueur.NOIR;

    private final Label messageScoreNoir = new Label("");
    private final Label messageScoreBlanc = new Label("");
    private final Label messageTourDeJeu = new Label("");

    public StatusBar() {
        super();
        setLeft(messageScoreNoir);
        setCenter(messageTourDeJeu);
        setRight(messageScoreBlanc);
        updateStatus();
    }

    public void setJoueurCourant(Joueur joueurCourant) {
        this.joueurCourant = joueurCourant;
    }

    public void updateStatus() {
        messageScoreNoir.setText(SCORE_NOIR + Joueur.NOIR.getScore());
        messageScoreBlanc.setText(SCORE_BLANC + Joueur.BLANC.getScore());
        if (joueurCourant == Joueur.NOIR)
            messageTourDeJeu.setText(MESSAGE_TOUR_NOIR);
        else if (joueurCourant == Joueur.BLANC)
            messageTourDeJeu.setText(MESSAGE_TOUR_BLANC);
        else
            messageTourDeJeu.setText(MESSAGE_TOUR_FIN_PARTIE);
    }
}
