package fr.univ_amu.iut.exercice1;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CounterController {

    IntegerProperty counter = new SimpleIntegerProperty(0);

    @FXML
    Label counterLabel;

    @FXML
    Button incrementButton;

    @FXML
    public void increment(ActionEvent actionEvent) {
        counter.set(counter.get() + 1);
    }

    public int getCounter() {
        return counter.get();
    }

    public void setCounter(int counter) {
        this.counter.set(counter);
    }

    public IntegerProperty counterProperty() {
        return counter;
    }
}
