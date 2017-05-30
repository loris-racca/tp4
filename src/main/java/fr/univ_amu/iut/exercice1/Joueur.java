package fr.univ_amu.iut.exercice1;

import javafx.scene.image.ImageView;

public enum Joueur {
    NOIR("assets/noir.png"),
    BLANC("assets/blanc.png"),
    PERSONNE("assets/vide.png");

    private final ImageView image;
    private int score;

    Joueur(String fileName) {
        image = new ImageView(getClass().getClassLoader().getResource(fileName).toExternalForm());
        score = 0;
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

    public ImageView getImage() {
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

    public static void initialiserScores() {
        BLANC.initialiserScore();
        NOIR.initialiserScore();
    }
}
