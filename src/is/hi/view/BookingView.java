package is.hi.view;

import com.jfoenix.controls.*;
import is.hi.model.Flight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

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

    private Flight flight;
    @FXML
    public void closeWindow(ActionEvent e){
        ((Node)(e.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void bookFlight(ActionEvent e){
        System.out.println(flight.getTo());
    }

    public void setFlight(Flight f){
        this.flight = f;
    }
}
