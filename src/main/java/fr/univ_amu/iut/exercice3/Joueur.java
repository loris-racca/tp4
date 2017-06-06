package fr.univ_amu.iut.exercice3;

import javafx.scene.image.Image;

public enum Joueur {
    NOIR("assets/noirgrand.png"),
    BLANC("assets/blancgrand.png"),
    PERSONNE("assets/vide.png");

    private final Image image;
    private int score;

    Joueur(String fileName) {
        image = new Image(getClass().getClassLoader().getResource(fileName).toExternalForm());
        score = 0;
    }

    public static void initialiserScores() {
        BLANC.initialiserScore();
        NOIR.initialiserScore();
    }

    public int getScore() {
        return score;
    }

    public void decrementerScore() {
        score--;
    }

    public void incrementerScore() {
        score++;
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

    private void initialiserScore() {
        score = 0;
    }
}
