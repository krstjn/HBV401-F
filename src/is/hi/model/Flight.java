package is.hi.model;

import java.util.ArrayList;

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

    public Flight(){
        passengers = new ArrayList<Passenger>();
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
        return departureTime;
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

}
