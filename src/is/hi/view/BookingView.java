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
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

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
        boolean success = true;
        if(firstName.getText().length() == 0){
            success = false;
            firstName.setUnFocusColor(Color.web("#f00"));
        } else firstName.setUnFocusColor(Color.web("#4d4d4d"));

        if(lastName.getText().length() == 0) {
            success = false;
            lastName.setUnFocusColor(Color.web("#f00"));
        } else lastName.setUnFocusColor(Color.web("#4d4d4d"));
        if(birthday.getValue() == null) {
            success = false;
            birthday.setStyle("-fx-border-color: red; -fx-border-width: 0 0 2 0");
        } else birthday.setStyle("-fx-border-width: 0");
        if(gender.getValue() == null) {
            success = false;
            gender.setUnFocusColor(Color.web("#f00"));
        } else gender.setUnFocusColor(Color.web("#4d4d4d"));
        if(seat.getValue() == null) {
            success = false;
            seat.setUnFocusColor(Color.web("#f00"));
        } else seat.setUnFocusColor(Color.web("#4d4d4d"));
        if(seatingClass.getValue() == null) {
            success = false;
            seatingClass.setUnFocusColor(Color.web("#f00"));
        } else seatingClass.setUnFocusColor(Color.web("#4d4d4d"));

        return success;
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

            ButtonType afram = new ButtonType("Já", ButtonBar.ButtonData.OK_DONE);
            ButtonType stop = new ButtonType("Nei", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Viltu klára þessa bókun?", afram, stop);
            alert.setTitle("Staðfesta bókun");
            alert.setHeaderText("Þú ert að bóka flug frá " + flight.getFrom() + " til " + flight.getTo());

            Optional<ButtonType> result = alert.showAndWait();
            System.out.println(result.get());
            if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE){
                fc.bookFlight(passenger);
                closeWindow(e);
            } else {

            }

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
        ArrayList<String> seats = flight.getAvailableSeats();
        seat.getItems().addAll(seats);
    }

    @FXML
    @SuppressWarnings("unchecked")
    public void initialize(){
        ObservableList<String> genders = FXCollections.observableArrayList("Karlkyn", "Kvenkyn");
        gender.getItems().addAll(genders);

        ObservableList<String> seating = FXCollections.observableArrayList("Economy", "Business");
        seatingClass.getItems().addAll(seating);

    }
}
