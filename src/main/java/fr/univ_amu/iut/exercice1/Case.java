package fr.univ_amu.iut.exercice1;


import javafx.scene.control.Button;

class Case extends Button {
    private int ligne;
    private int colonne;
    private Joueur possesseur;

    public Case(int ligne, int colonne) {
        setPrefSize(50, 50);
        this.colonne = colonne;
        this.ligne = ligne;
        this.possesseur = Joueur.PERSONNE;
    }

    public void setPossesseur(Joueur possesseur) {
        this.possesseur = possesseur;
        setGraphic(possesseur.getImage());
    }

    public Joueur getPossesseur() {
        return possesseur;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }
}
