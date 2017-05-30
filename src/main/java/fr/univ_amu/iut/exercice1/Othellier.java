package fr.univ_amu.iut.exercice1;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.List;


class Othellier extends GridPane {

    private static final Point2D[] directions = {
            new Point2D(1, 0),
            new Point2D(1, 1),
            new Point2D(0, 1),
            new Point2D(-1, 1),
            new Point2D(-1, 0),
            new Point2D(-1, -1),
            new Point2D(0, -1),
            new Point2D(1, -1)
    };
    private int taille;
    private Case[][] cases;
    private Joueur joueurCourant = Joueur.NOIR;
    private OthelloIHM pere;

    private final  EventHandler<ActionEvent> caseListener = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Case caseSelectionnee = (Case) event.getSource();
            if (estPositionJouable(caseSelectionnee)) {
                capturer(caseSelectionnee);
                tourSuivant();
                pere.updateStatus();
            }
        }
    };

    public Othellier(OthelloIHM pere, int taille) {
        this.pere = pere;
        this.taille = taille;
        remplirOthelier(taille);
        adapterLesLignesEtColonnes();
        positionnerPionsDebutPartie();
    }

    private void remplirOthelier(int taille) {
        cases = new Case[taille][taille];
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                cases[i][j] = new Case(i, j);
                cases[i][j].setOnAction(caseListener);
                add(cases[i][j],i,j);
            }
        }
    }

    private void adapterLesLignesEtColonnes() {
        for (int i = 0; i < taille; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(Priority.ALWAYS);
            getColumnConstraints().add(column);
        }

        for (int i = 0; i < taille; i++) {
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            getRowConstraints().add(row);
        }
    }

    private void positionnerPionsDebutPartie() {
        cases[taille / 2 - 1][taille / 2 - 1].setPossesseur(Joueur.BLANC);
        cases[taille / 2][taille / 2].setPossesseur(Joueur.BLANC);
        Joueur.BLANC.incrementerScore();
        Joueur.BLANC.incrementerScore();

        cases[taille / 2 - 1][taille / 2].setPossesseur(Joueur.NOIR);
        cases[taille / 2][taille / 2 - 1].setPossesseur(Joueur.NOIR);
        Joueur.NOIR.incrementerScore();
        Joueur.NOIR.incrementerScore();
    }


    public void nouvellePartie() {
        joueurCourant = Joueur.NOIR;
        Joueur.initialiserScores();
        vider();
        positionnerPionsDebutPartie();
        pere.updateStatus();
    }

    private boolean peutJouer() {
        return !casesJouables().isEmpty();
    }

    private List<Case> casesJouables() {
        List<Case> casesJouables = new ArrayList<>();
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (cases[i][j].getPossesseur() == Joueur.PERSONNE && !casesCapturable(cases[i][j]).isEmpty()) {
                    casesJouables.add(cases[i][j]);
                }
            }
        }
        return casesJouables;
    }

    private List<Case> casesCapturable(Case caseSelectionnee) {
        List<Case> casesCapturable = new ArrayList<>();

        for (Point2D direction : directions) {
            casesCapturable.addAll(casesCapturable(caseSelectionnee, direction));
        }

        return casesCapturable;
    }

    private List<Case> casesCapturable(Case caseSelectionnee, Point2D direction) {
        List<Case> casesCapturable = new ArrayList<>();

        int indiceLigne = caseSelectionnee.getLigne() + (int) direction.getY();
        int indiceColonne = caseSelectionnee.getColonne() + (int) direction.getX();

        while (estIndicesValides(indiceLigne, indiceColonne)) {
            if (cases[indiceLigne][indiceColonne].getPossesseur() != joueurCourant.suivant())
                break;

            casesCapturable.add(cases[indiceLigne][indiceColonne]);

            indiceLigne += direction.getY();
            indiceColonne += direction.getX();
        }

        if (estIndicesValides(indiceLigne, indiceColonne) &&
                cases[indiceLigne][indiceColonne].getPossesseur() == joueurCourant)
            return casesCapturable;
        return new ArrayList<>();
    }

    private boolean estIndicesValides(int indiceLigne, int indiceColonne) {
        return estIndiceValide(indiceColonne) && estIndiceValide(indiceLigne);
    }

    private boolean estIndiceValide(int indiceColonne) {
        return indiceColonne < taille && indiceColonne >= 0;
    }

    private boolean estPositionJouable(Case caseSelectionnee) {
        return caseSelectionnee.getPossesseur() == Joueur.PERSONNE && !casesCapturable(caseSelectionnee).isEmpty();
    }

    private void capturer(Case caseCapturee) {
        caseCapturee.setPossesseur(joueurCourant);
        joueurCourant.incrementerScore();

        List<Case> casesCapturees = casesCapturable(caseCapturee);
        for (Case caseCourante : casesCapturees) {
            caseCourante.setPossesseur(joueurCourant);
            joueurCourant.incrementerScore();
            joueurCourant.suivant().decrementerScore();
        }
    }

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    private void tourSuivant() {
        joueurCourant = joueurCourant.suivant();
        if (!peutJouer()) {
            joueurCourant = joueurCourant.suivant();
            if (!peutJouer())
                joueurCourant = Joueur.PERSONNE;
        }
    }

    private void vider() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                cases[i][j].setPossesseur(Joueur.PERSONNE);
            }
        }
    }
}
