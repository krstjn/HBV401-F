package is.hi.controller;

import com.jfoenix.controls.*;
import is.hi.model.*;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;

import java.sql.SQLException;
import java.util.ArrayList;

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

    private Query query;
    private DBManager db;


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
        ArrayList<String> a = null;

        db = new DBManager();
        try{
            a = db.runQuery("Select * from flights");
        } catch (SQLException e){
            System.out.println(e);
        }
        cbOrigin.getItems().addAll(a);

        cbDestination.getItems().addAll(a);
    }
}
