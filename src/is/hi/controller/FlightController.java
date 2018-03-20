package is.hi.controller;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;

import java.util.Collection;

public class FlightController {
    @FXML
    private JFXComboBox cbOrigin;
    @FXML
    private JFXComboBox cbDestination;
    @FXML
    private JFXComboBox cbAirline;
    @FXML
    private JFXComboBox cbClass;
    @FXML
    private JFXComboBox cbTiming;
    @FXML
    private JFXSlider maxPrice;
    @FXML
    private JFXCheckBox oneWay;
    @FXML
    private JFXDatePicker departureFlight;
    @FXML
    private JFXDatePicker returnFlight;
    @FXML
    private JFXButton btnSearch;


    @FXML
    private void buttonPressed(ActionEvent e){
        System.out.println("takki");
    }

    @FXML
    private void oneWayFlight(ActionEvent e){
        if(oneWay.isSelected())
            returnFlight.setDisable(true);
        else
            returnFlight.setDisable(false);
    }

    @FXML
    public void initialize() {
        String[] a = {"Option 4",
                "Option 5",
                "Option 6"};
        cbOrigin.getItems().addAll(a);

        cbDestination.getItems().addAll(a);
    }
}
