package is.hi.view;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import is.hi.controller.FlightController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.print.DocFlavor;
import java.sql.SQLException;
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
    private void searchQueries(ActionEvent e) throws SQLException{
        Date one = new Date();
        one.setHours(0);
        String dateOne = (one.toString());
        if(startDate.getValue() != null)
            dateOne = startDate.getValue().toString();
        Date two = new Date();
        two.setHours(24);
        String dateTwo = (two.toString());
        if(endDate.getValue() != null)
            dateTwo = endDate.getValue().toString();




        int nrSearches = fc.getNrOffSearches("'"+dateOne+"'", "'"+dateTwo+"'");
        int nrBookings = fc.getNrOffBookings("'"+dateOne+"'","'"+dateTwo+"'");
        bookings.setText(String.valueOf(nrBookings));
        searches.setText(String.valueOf(nrSearches));
        originList.getItems().addAll(fc.getMostSearched("'"+dateOne+"'", "'"+dateTwo+"'", "origin"));
        destinationList.getItems().addAll(fc.getMostSearched("'"+dateOne+"'", "'"+dateTwo+"'", "destination"));
        System.out.println("Leitartilraun: " + dateOne + " : " + dateTwo);
    }

    public void setController(FlightController fc){
        this.fc = fc;
    }
    @FXML
    public void initialize(){

    }
}
