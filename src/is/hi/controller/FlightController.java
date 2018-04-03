package is.hi.controller;

import com.jfoenix.controls.*;
import is.hi.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.ArrayList;

public class FlightController {
    @FXML private JFXComboBox cbOrigin;
    @FXML private JFXComboBox cbDestination;
    @FXML private JFXComboBox cbAirline;
    @FXML private JFXComboBox cbClass;
    @FXML private JFXComboBox cbTiming;
    @FXML private JFXSlider maxPrice;
    @FXML private JFXCheckBox oneWay;
    @FXML private JFXDatePicker departureFlight;
    @FXML private JFXDatePicker returnFlight;
    @FXML private JFXButton btnSearch;
    @FXML private Label maxPriceLabel;
    @FXML private TableView<Flight> originTable;
    @FXML private TableView<Flight> destinationTable;
    //@FXML private TableColumn<Flight, String> originCol;
    //@FXML private TableColumn<Flight, String> destinationCol;
    //@FXML private TableColumn<Flight, String> departureCol;
    //FXML private TableColumn<Flight, String> departureTimeCol;
    //@FXML private TableColumn<Flight, String> priceCol;
    //@FXML private TableColumn<Flight, String> availableSeatsCol;

    private Query query;
    private DBManager db;

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
        if(cbAirline.getValue() != null) q.setAirline(String.valueOf(cbAirline.getValue()));
        if(cbDestination.getValue() != null) q.setDestination(String.valueOf(cbDestination.getValue()));
        if(cbOrigin.getValue() != null) q.setOrigin(String.valueOf(cbOrigin.getValue()));
        if(cbClass.getValue() != null) q.setSeatingClass(String.valueOf(cbClass.getValue()));
        if(cbTiming.getValue() != null){
            String timing = String.valueOf(cbTiming.getValue());
            switch(timing){
                case "Næturflug":
                    q.setDepartureTime(600);
                    break;
                case "Morgunflug":
                    q.setDepartureTime(1200);
                    break;
                case "Dagsflug":
                    q.setDepartureTime(1800);
                    break;
                case "Kvöldflug":
                    q.setDepartureTime(2400);
            }
        }
        if(departureFlight.getValue() != null){
            int year = departureFlight.getValue().getYear();
            int month = departureFlight.getValue().getMonthValue();
            int day = departureFlight.getValue().getDayOfMonth();

            String m = "";
            String d = "";
            if(month < 10) m += "0";
            if(day < 10) d += "0";
            q.setDepartureDate(Integer.valueOf(year +m+month+d+day));
        }
        q.setMaxPrice((int)maxPrice.getValue() * 1000);
        ArrayList<Flight> flights = new ArrayList<>();
        try {
            flights = db.searchFlights(q);
        } catch (SQLException error){
            System.out.println("Villa við að sækja flug " + error);
        }
        populateTable(flights, originTable);
        populateTable(flights, destinationTable);
    }

    private void populateTable(ArrayList<Flight> flights, TableView<Flight> table){
        ObservableList<Flight> items =  FXCollections.observableArrayList(flights);
        table.setItems(items);
    }

    @FXML
    private void flightSelected(MouseEvent e){
        /**
        System.out.println("Table clicked");
        Flight f = originTable.getSelectionModel().getSelectedItem();
        System.out.println(f.getFrom() + " " + f.getTo());
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(f.getFrom() + " - " + f.getTo()));
        JFXButton click = new JFXButton("OK");
        content.setActions(click);
        JFXDialog flightInfo = new JFXDialog(dialogWindow, content,JFXDialog.DialogTransition.TOP);
        click.setOnAction(event -> {
            flightInfo.close();
        });

        flightInfo.show();
*/
    }

    @FXML
    @SuppressWarnings("unchecked")
    public void initialize() {
        ArrayList<String> a;

        db = new DBManager();
        int max = 100;
        int min = 0;
        try{
            a = db.runQuery("Select origin from flights GROUP BY origin ORDER BY origin");
            cbOrigin.getItems().addAll(a);
            a = db.runQuery("Select destination from flights GROUP BY destination ORDER BY destination");
            cbDestination.getItems().addAll(a);
            a = db.runQuery("Select airline from flights GROUP BY airline");
            cbAirline.getItems().addAll(a);
        } catch (SQLException error){
            System.out.println(error);
        }

        ObservableList<String> timings = FXCollections.observableArrayList("Morgunflug", "Dagsflug", "Kvöldflug", "Næturflug");
        cbTiming.getItems().addAll(timings);

        ObservableList<String> classes = FXCollections.observableArrayList("Economy", "Business");
        cbClass.getItems().addAll(classes);

        maxPrice.setValue(maxPrice.getMax());

        maxPriceLabel.setText((int)maxPrice.getValue() + " þús");
        maxPrice.valueProperty().addListener((ov, old_val, new_val) ->
                maxPriceLabel.setText((int)Math.ceil(new_val.doubleValue()) + " þús"));
        TableColumn originCol = new TableColumn("Origin");
        originCol.setCellValueFactory(
                new PropertyValueFactory<Flight, String>("from"));
        TableColumn destinationCol = new TableColumn("Destination");
        destinationCol.setCellValueFactory(
                new PropertyValueFactory<Flight, String>("to"));
        TableColumn priceCol = new TableColumn("ecoPrice");
        priceCol.setCellValueFactory(
                new PropertyValueFactory<Flight, Integer>("price" +
                        ""));
        TableColumn airlineCol = new TableColumn("Airline");
        airlineCol.setCellValueFactory(
                new PropertyValueFactory<Flight, String>("airline"));
        originTable.getColumns().addAll(originCol, destinationCol, priceCol, airlineCol);
        destinationTable.getColumns().addAll(originCol, destinationCol, priceCol, airlineCol);


    }
}
