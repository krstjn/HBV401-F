package is.hi.view;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import is.hi.controller.FlightController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.util.Date;

public class AdminView {
    @FXML private Label bookings;
    @FXML private Label searches;
    @FXML private JFXDatePicker startDate;
    @FXML private JFXDatePicker endDate;
    @FXML private JFXListView originList;
    @FXML private JFXListView destinationList;

    private FlightController fc;

    @FXML
    private void searchQueries(ActionEvent e){
        String dateOne = (new Date()).toString();
        if(startDate.getValue() != null)
            dateOne = startDate.getValue().toString();
        String dateTwo = (new Date()).toString();
        if(endDate.getValue() != null)
            dateTwo = endDate.getValue().toString();
        System.out.println("Leitartilraun: " + dateOne + " : " + dateTwo);
    }

    @FXML
    public void initialize(){
        fc = new FlightController();
    }
}
