package is.hi.controller;

import is.hi.model.Flight;
import is.hi.model.Query;

import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;

/************************
 * Höfundur: Kristján P.*
 ************************/
public class DBManager {

    private Connection conn;

    private ArrayList<Flight> flights;
    private ArrayList<Query> searches;

    public DBManager(){
        try{
            System.out.println("Trying sqlite...");
            Class.forName("org.sqlite.JDBC"); // fyrir SQLite
            conn = DriverManager.getConnection("jdbc:sqlite:flights.db");
        } catch (Exception e){
            System.out.println("sqlite failed...");
        }
    }
    public ArrayList<String> runQuery(String query) throws SQLException{
        PreparedStatement p = conn.prepareStatement(query);
        ResultSet r = p.executeQuery();
        String x = "";
        ArrayList<String> a = new ArrayList<String>();
        while (r.next()) {
            a.add(r.getString(3));
        }
        r.close();
        conn.close();
        System.out.println();
        return a;
    }

    public void searchFlights(String query) throws SQLException {
        PreparedStatement p = conn.prepareStatement(query);
        ResultSet r = p.executeQuery();

        Flight flight = new Flight();
        while (r.next()) {
            flight.setFlightID(r.getString("flightID"));
            flight.setFrom(r.getString("origin"));
            flight.setTo(r.getString("destination"));
            flight.setDepartureTime(r.getString("departure"));
            flight.setArrivalTime(r.getString("arrival"));
            flight.setEcoCapacity(r.getInt("ecoCapacity"));
            flight.setBusCapacity(r.getInt("busCapacity"));
            flight.setEcoPrice(r.getInt("ecoPrice"));
            flight.setBusPrice(r.getInt("busPrice"));
            flight.setAirline(r.getString("airline"));

            flights.add(flight);
        }
        r.close();
        conn.close();
        System.out.println();
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }


}
