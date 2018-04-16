package is.hi.controller;

import is.hi.model.Flight;
import is.hi.model.Passenger;
import is.hi.model.Query;

import java.sql.SQLException;
import java.util.ArrayList;

public class FlightController {
    private ArrayList<Flight> flights;
    private Query query;
    private DBManager db;

    public FlightController(){
        db = new DBManager();
    }

    public ArrayList<Flight> searchFlights(Query q) throws SQLException {
        query = q;
        flights = db.searchFlights(q);
        return flights;
    }

    public void bookFlight(Passenger p) {

    }

    public ArrayList<String> runQuery(String s) throws SQLException{
        return db.runQuery(s);
    }

    public int getNrOffSearches(String s1, String s2) throws SQLException {
        return db.getNrOffSearches(s1, s2);
    }

    public int getNrOffBookings(String s1, String s2) throws SQLException {
        return db.getNrOffBookings(s1, s2);
    }


}
