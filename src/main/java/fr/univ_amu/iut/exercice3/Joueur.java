package fr.univ_amu.iut.exercice3;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

public enum Joueur {
    NOIR("assets/noirgrand.png"),
    BLANC("assets/blancgrand.png"),
    PERSONNE("assets/vide.png");

    private final Image image;
    private IntegerProperty score;

    Joueur(String fileName) {
        image = new Image(getClass().getClassLoader().getResource(fileName).toExternalForm());
        score = new SimpleIntegerProperty();
    }

    public static void initialiserScores() {
        BLANC.initialiserScore();
        NOIR.initialiserScore();
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    private void initialiserScore() {
        score.set(0);
    }

    public int getScore() {
        return score.get();
    }

    public void decrementerScore() {
        score.set(score.get() - 1);
    }

    public void incrementerScore() {
        score.set(score.get() - 1);
    }

    public Image getImage() {
        return image;
    }

    public Joueur suivant() {
        if (this == BLANC)
            return NOIR;
        if (this == NOIR)
            return BLANC;
        return PERSONNE;
    }
}
