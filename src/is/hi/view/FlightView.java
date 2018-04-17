package is.hi.view;

import com.jfoenix.controls.*;
import is.hi.controller.DBManager;
import is.hi.controller.FlightController;
import is.hi.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class FlightView {
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
    @FXML private StackPane dialogWindow;
    @FXML private TableView<Flight> returnTable;

    //@FXML private TableColumn<Flight, String> originCol;
    //@FXML private TableColumn<Flight, String> destinationCol;
    //@FXML private TableColumn<Flight, String> departureCol;
    //FXML private TableColumn<Flight, String> departureTimeCol;
    //@FXML private TableColumn<Flight, String> priceCol;
    //@FXML private TableColumn<Flight, String> availableSeatsCol;

    private Query query;
    private FlightController fc;

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
        Query q1 = new Query();
        if(cbAirline.getValue() != null) {
            q.setAirline(String.valueOf(cbAirline.getValue()));
            q1.setAirline(String.valueOf(cbAirline.getValue()));
        }
        if(cbDestination.getValue() != null){
            q.setDestination(String.valueOf(cbDestination.getValue()));
            if(!oneWay.isSelected())
                q1.setOrigin(String.valueOf(cbDestination.getValue()));
        }
        if(cbOrigin.getValue() != null) {
            q.setOrigin(String.valueOf(cbOrigin.getValue()));
            if(!oneWay.isSelected())
                q1.setDestination(String.valueOf(cbOrigin.getValue()));
        }
        if(cbClass.getValue() != null) {
            q.setSeatingClass(String.valueOf(cbClass.getValue()));
            q1.setSeatingClass(String.valueOf(cbClass.getValue()));
        }

        if(cbTiming.getValue() != null){
            String timing = String.valueOf(cbTiming.getValue());
            switch(timing){
                case "Næturflug":
                    q.setDepartureTime(600);
                    q1.setDepartureTime(600);
                    break;
                case "Morgunflug":
                    q.setDepartureTime(1200);
                    q1.setDepartureTime(1200);
                    break;
                case "Dagsflug":
                    q.setDepartureTime(1800);
                    q1.setDepartureTime(1800);
                    break;
                case "Kvöldflug":
                    q.setDepartureTime(2400);
                    q1.setDepartureTime(2400);
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
        q1.setMaxPrice((int)maxPrice.getValue() * 1000);
        ArrayList<Flight> flightsOut;
        ArrayList<Flight> flightsBack;

        try {
            flightsOut = fc.searchFlights(q);
            System.out.println("Leitir: " + fc.getNrOffSearches("'2010-05-28T15:36:56+0200'", "'2020-05-28T15:36:56+0200'"));
        } catch (SQLException error){
            System.out.println("Villa við að sækja flug " + error);
            flightsOut = new ArrayList<>();
        }
        populateTable(flightsOut, originTable);


        System.out.println(oneWay.isSelected());
        flightsBack = new ArrayList<>();
        if(!oneWay.isSelected()){
            try {

                flightsBack = fc.searchFlights(q1);
                System.out.println("Leitir: " + fc.getNrOffSearches("'2010-05-28T15:36:56+0200'", "'2020-05-28T15:36:56+0200'"));

            } catch (SQLException error){
                flightsBack = new ArrayList<>();

            }



        }
        populateTable(flightsBack, returnTable);


    }

    private void populateTable(ArrayList<Flight> flights, TableView<Flight> table){
        ObservableList<Flight> items =  FXCollections.observableArrayList(flights);
        table.setItems(items);
    }

    @FXML
    private void login(ActionEvent e){
        dialogWindow.setLayoutX(50);
        dialogWindow.setLayoutY(50);
        double width = dialogWindow.getWidth();
        double height = dialogWindow.getHeight();

        dialogWindow.setPrefWidth(width/2);
        dialogWindow.setPrefHeight(height/3);
        dialogWindow.getChildren().clear();
        System.out.println("login clicked");
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Innskráning"));
        JFXButton login = new JFXButton("Skrá inn");
        JFXButton close = new JFXButton("Hætta við");
        JFXTextField username = new JFXTextField("");
        username.setPromptText("Notandanafn");
        content.setLayoutX(50);
        content.setLayoutY(50);


        JFXTextField password = new JFXTextField("");
        password.setPromptText("Lykilorð");
        close.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.RAISED);
        login.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.RAISED);
        content.setActions(username, password, close, login);
        JFXDialog flightInfo = new JFXDialog(dialogWindow, content,JFXDialog.DialogTransition.TOP);

        login.setOnAction(event -> {
            System.out.println("tilraun til að skrá inn");
            System.out.println(username.getText());
        });
        close.setOnAction(event -> {
            dialogWindow.setMouseTransparent(true);
            dialogWindow.setPrefWidth(width/2);
            dialogWindow.setPrefHeight(height/2);
            flightInfo.close();
        });

        flightInfo.show();

    }
    @FXML
    private void flightSelected(MouseEvent e){
        double x = e.getX();
        double y = e.getY();
        dialogWindow.setLayoutX(x);
        dialogWindow.setLayoutY(y);
        dialogWindow.setMouseTransparent(false);

        dialogWindow.getChildren().clear();
        System.out.println("Table clicked");
        TableView<Flight> table = (TableView<Flight>)e.getSource();
        Flight f = table.getSelectionModel().getSelectedItem();
        System.out.println(f.getFrom() + " " + f.getTo());
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(f.getFrom() + " - " + f.getTo()));

        JFXButton book = new JFXButton("Bóka");
        JFXButton close = new JFXButton("Loka");
        content.setActions(close, book);
        JFXDialog flightInfo = new JFXDialog(dialogWindow, content,JFXDialog.DialogTransition.TOP);
        book.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Parent root = null;
                try {
                    System.out.println(getClass().getResource("booking.fxml"));
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("booking.fxml"));
                    root = loader.load();
                    BookingView controller = loader.getController();
                    controller.setFlight(f);
                    Scene scene = new Scene(root);
                    Stage secStage = new Stage();
                    secStage.setTitle("Bóka flug frá " + f.getFrom() + " til " + f.getTo());
                    secStage.setScene(scene);
                    secStage.show();
                    //((Node) (event.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        close.setOnAction(event -> {
            dialogWindow.setMouseTransparent(true);
            flightInfo.close();
        });

        flightInfo.show();

    }

    @FXML
    @SuppressWarnings("unchecked")
    public void initialize() {
        ArrayList<String> a;

        fc = new FlightController();
        try{
            a = fc.runQuery("Select origin from flights GROUP BY origin ORDER BY origin");
            cbOrigin.getItems().addAll(a);
            a = fc.runQuery("Select destination from flights GROUP BY destination ORDER BY destination");
            cbDestination.getItems().addAll(a);
            a = fc.runQuery("Select airline from flights GROUP BY airline");
            cbAirline.getItems().addAll(a);
        } catch (SQLException error){
            System.out.println(error);
            a = new ArrayList<>();
        }

        ObservableList<String> timings = FXCollections.observableArrayList("Morgunflug", "Dagsflug", "Kvöldflug", "Næturflug");
        cbTiming.getItems().addAll(timings);

        ObservableList<String> classes = FXCollections.observableArrayList("Economy", "Business");
        cbClass.getItems().addAll(classes);

        maxPrice.setValue(maxPrice.getMax());

        maxPriceLabel.setText((int)maxPrice.getValue() + " þús");
        maxPrice.valueProperty().addListener((ov, old_val, new_val) ->
                maxPriceLabel.setText((int)Math.ceil(new_val.doubleValue()) + " þús"));
        TableColumn originCol = new TableColumn("Brottfarastaður");
        originCol.setCellValueFactory(
                new PropertyValueFactory<Flight, String>("from"));
        originCol.setMinWidth(20);
        TableColumn destinationCol = new TableColumn("Áfangastaður");
        destinationCol.setCellValueFactory(
                new PropertyValueFactory<Flight, String>("to"));
        TableColumn priceCol = new TableColumn("Price");
        priceCol.setCellValueFactory(
                new PropertyValueFactory<Flight, Integer>("ecoPrice"));
        TableColumn airlineCol = new TableColumn("Airline");
        airlineCol.setCellValueFactory(
                new PropertyValueFactory<Flight, String>("airline"));
        TableColumn departureCol = new TableColumn("Brottfaratími");
        departureCol.setCellValueFactory(
                new PropertyValueFactory<Flight, Date>("departureTime"));
        TableColumn capacityCol = new TableColumn("Laus sæti");
        capacityCol.setCellValueFactory(
                new PropertyValueFactory<Flight, Integer>("ecoCapacity"));
        originTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        originTable.getColumns().addAll(originCol, destinationCol, priceCol, airlineCol,departureCol, capacityCol);
        TableColumn originCol1 = new TableColumn("Brottfarastaður");
        originCol1.setCellValueFactory(
                new PropertyValueFactory<Flight, String>("from"));
        TableColumn destinationCol1 = new TableColumn("Áfangastaður");
        destinationCol1.setCellValueFactory(
                new PropertyValueFactory<Flight, String>("to"));
        TableColumn priceCol1 = new TableColumn("Verð");
        priceCol1.setCellValueFactory(
                new PropertyValueFactory<Flight, Integer>("ecoPrice"));
        TableColumn airlineCol1 = new TableColumn("Flugfélag");
        airlineCol1.setCellValueFactory(
                new PropertyValueFactory<Flight, String>("airline"));
        TableColumn departureCol1 = new TableColumn("Brottfaratími");
        departureCol1.setCellValueFactory(
                new PropertyValueFactory<Flight, Date>("departureTime"));
        TableColumn capacityCol1 = new TableColumn("Laus sæti");
        capacityCol1.setCellValueFactory(
                new PropertyValueFactory<Flight, Integer>("ecoCapacity"));
        returnTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        returnTable.getColumns().addAll(originCol1, destinationCol1, priceCol1, airlineCol1,departureCol1, capacityCol1);
    }
}
