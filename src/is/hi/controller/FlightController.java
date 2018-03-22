package is.hi.controller;

import com.jfoenix.controls.*;
import is.hi.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;

import javax.print.DocFlavor;
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
    private void searchFlights(ActionEvent e){
        Query q = new Query();
        System.out.println(cbOrigin.getValue());
        if(cbAirline.getValue() != null) q.setAirline(String.valueOf(cbAirline.getValue()));
        if(cbDestination.getValue() != null) q.setDestination(String.valueOf(cbDestination.getValue()));
        if(cbOrigin.getValue() != null) q.setOrigin(String.valueOf(cbOrigin.getValue()));
        if(cbClass.getValue() != null) q.setSeatingClass(String.valueOf(cbClass.getValue()));
        q.setMaxPrice((int)maxPrice.getValue() * 1000);
        System.out.println(q);
    }
    @FXML
    @SuppressWarnings("unchecked")
    public void initialize() {
        ArrayList<String> a = null;

        db = new DBManager();
        try{
            a = db.runQuery("Select origin from flights GROUP BY origin ORDER BY origin");
            cbOrigin.getItems().addAll(a);
            a = db.runQuery("Select destination from flights GROUP BY destination ORDER BY destination");
            cbDestination.getItems().addAll(a);
            a = db.runQuery("Select airline from flights GROUP BY airline");
            cbAirline.getItems().addAll(a);
        } catch (SQLException e){
            System.out.println(e);
        }

        ObservableList<String> timings = FXCollections.observableArrayList("Morgunflug", "Dagsflug", "Kvöldflug", "Næturflug");
        cbTiming.getItems().addAll(timings);
        ObservableList<String> classes = FXCollections.observableArrayList("Economy", "Business");
        cbClass.getItems().addAll(classes);
    }
}
