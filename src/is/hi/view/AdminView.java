package is.hi.view;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import is.hi.controller.FlightController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.print.DocFlavor;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AdminView {
    @FXML private Label bookings;
    @FXML private Label searches;
    @FXML private JFXDatePicker startDate;
    @FXML private JFXDatePicker endDate;
    @FXML private JFXListView originList;
    @FXML private JFXListView destinationList;

    private FlightController fc;


    public static String fromCalendar(final Calendar calendar) {
        Date date = calendar.getTime();
        String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .format(date);
        return formatted.substring(0, 22) + ":" + formatted.substring(22);
    }

    public static String getDate() {
        return fromCalendar(GregorianCalendar.getInstance());
    }
    @FXML
    private void searchQueries(ActionEvent e) throws SQLException{
        String dateOne = getDate();
        dateOne = dateOne.substring(0,11)+"00:00:00";
        if(startDate.getValue() != null)
            dateOne = startDate.getValue().toString();

        System.out.println("dateOne: " + dateOne);

        String dateTwo = getDate();
        dateTwo = dateTwo.substring(0,11)+"23:59:59";
        if(endDate.getValue() != null)
            dateTwo = endDate.getValue().toString();
        System.out.println("dateTwo: " + dateTwo);




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
