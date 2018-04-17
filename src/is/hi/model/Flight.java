package is.hi.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Flight {

    private String flightID;
    private String from;
    private String to;
    private String departureTime;
    private String arrivalTime;
    private int ecoCapacity;
    private int busCapacity;
    private int ecoPrice;
    private int busPrice;
    private String airline;
    private ArrayList<Passenger> passengers;
    private ArrayList<String> availableSeats;

    public Flight(){

        passengers = new ArrayList<Passenger>();

        availableSeats = new ArrayList<String>();
        for(char row = 'A'; row < 20 + 'A'; row++) {
            for(int nr = 1; nr <= 6; nr++) {
                String s = "";
                s = row + String.valueOf(nr);
                availableSeats.add(s);
            }
        }

    }

    public boolean isFullEco() {
        return ecoCapacity == 0;
    }

    public boolean isFullBus() {
        return busCapacity == 0;
    }

    public void addPassenger(Passenger p) {
        passengers.add(p);
    }


    /*******************************************************
     Setters
     *******************************************************/

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setDepartureTime(String departureTime){
        this.departureTime = departureTime;
    }

    public void setArrivalTime(String arrivalTime){
        this.arrivalTime = arrivalTime;
    }

    public void setEcoCapacity(int ecoCapacity) {
        this.ecoCapacity = ecoCapacity;
    }

    public void setBusCapacity(int busCapacity) {
        this.busCapacity = busCapacity;
    }

    public void setEcoPrice(int ecoPrice) {
        this.ecoPrice = ecoPrice;
    }

    public void setBusPrice(int busPrice) {
        this.busPrice = busPrice;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void setSeats(ArrayList<String> bookedSeats) {
        for(int i = 0; i < bookedSeats.size(); i++){
            if(availableSeats.contains(bookedSeats.get(i))){
                availableSeats.remove(bookedSeats.get(i));
            }
        }
    }

    /*******************************************************
     Getters
     *******************************************************/

    public String getFlightID(){
        return flightID;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDepartureTime() {
        String date = departureTime.substring(0,4) + '-' + departureTime.substring(4,6) + '-'+departureTime.substring(6,8);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
        LocalDate localDate = LocalDate.parse(date);
        return localDate.format(formatter);
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getEcoCapacity() {
        return ecoCapacity;
    }

    public int getBusCapacity() {
        return busCapacity;
    }

    public int getEcoPrice() {
        return ecoPrice;
    }

    public int getBusPrice() {
        return busPrice;
    }

    public String getAirline() {
        return airline;
    }

    public ArrayList<String> getAvailableSeats() {
        System.out.println(availableSeats.get(10));
        return availableSeats;
    }
}
