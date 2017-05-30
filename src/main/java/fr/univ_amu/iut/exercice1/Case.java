package fr.univ_amu.iut.exercice1;


import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

class Case extends Button {
    private int ligne;
    private int colonne;
    private Joueur possesseur;
    private ImageView imageView;
    public Case(int ligne, int colonne) {
        this.colonne = colonne;
        this.ligne = ligne;
        this.possesseur = Joueur.PERSONNE;
        this.imageView = new ImageView();

        setPrefSize(50, 50);
        setGraphic(imageView);
    }

    public Joueur getPossesseur() {
        return possesseur;
    }

    public void setPossesseur(Joueur possesseur) {
        this.possesseur = possesseur;
        imageView.setImage(possesseur.getImage());
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }
}
