package is.hi.view;

import com.jfoenix.controls.*;
import is.hi.controller.FlightController;
import is.hi.model.Flight;
import is.hi.model.Passenger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.sql.SQLException;

/************************
 * Höfundur: Kristján P.*
 ************************/
public class BookingView {

    @FXML private JFXTextField firstName;
    @FXML private JFXTextField lastName;
    @FXML private JFXComboBox gender;
    @FXML private JFXDatePicker birthday;
    @FXML private JFXComboBox seatingClass;
    @FXML private JFXComboBox seat;
    @FXML private JFXCheckBox luggage;
    @FXML private Label flightInfo;
    @FXML private Label error;

    private Flight flight;
    private FlightController fc;

    @FXML
    public void closeWindow(ActionEvent e){
        ((Node)(e.getSource())).getScene().getWindow().hide();
    }

    private boolean validateBooking(){
        if(firstName.getText().length() == 0) return false;
        if(lastName.getText().length() == 0) return false;
        if(birthday.getValue() == null) return false;
        if(gender.getValue() == null) return false;
        if(seat.getItems() == null) return false;
        if(seatingClass.getValue() == null) return false;

        return true;
    }

    @FXML
    private void bookFlight(ActionEvent e) throws SQLException{
        if(validateBooking()) {
            System.out.println(flight.getTo());
            Passenger passenger = new Passenger();
            passenger.setFirst(firstName.getText());
            passenger.setLast(lastName.getText());
            passenger.setSeatingNumber(seat.getValue().toString());
            passenger.setBirthDate(birthday.getValue().toString());
            passenger.setFlightID(flight.getFlightID());
            if (gender.getValue().toString().equals("Karlkyns"))
                passenger.setGender('M');
            else
                passenger.setGender('F');

            passenger.setSeatingClass(seatingClass.getValue().toString());

            fc.bookFlight(passenger);
        } else {
            error.setText("Fylla þarf alla stjörnumerkta reiti");
            error.setTextFill(Color.web("#f00"));
        }
    }

    public void setFlight(Flight f, FlightController fc){
        this.flight = f;
        this.fc = fc;
        flightInfo.setText(
                "Flug frá " + flight.getFrom() +
                " til " + flight.getTo() +
                ".     Verð(economy): " + flight.getEcoPrice()+
                ".     Verð(business): " + flight.getBusPrice());

    }

    @FXML
    @SuppressWarnings("unchecked")
    public void initialize(){
        ObservableList<String> genders = FXCollections.observableArrayList("Karlkyn", "Kvenkyn");
        gender.getItems().addAll(genders);

        ObservableList<String> seating = FXCollections.observableArrayList("Economy", "Business");
        seatingClass.getItems().addAll(seating);

        ObservableList<String> seats = FXCollections.observableArrayList("4A", "5B");
        seat.getItems().addAll(seats);
    }
}
