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

    private ArrayList<Flight> flights = new ArrayList<>();
    private ArrayList<Query> searches = new ArrayList<>();

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
        ArrayList<String> a = new ArrayList<String>();
        while (r.next()) {
            a.add(r.getString(1));
        }
        r.close();
        System.out.println();
        return a;
    }
    // TODO
    public ArrayList<Flight> searchFlights(Query query) throws SQLException {
        flights.clear();
        PreparedStatement p = conn.prepareStatement(query.toString() + " order by EcoPrice");
        ResultSet r = p.executeQuery();

        while (r.next()) {
            Flight flight = new Flight();
            flight.setFlightID(r.getString("flightID"));
            flight.setFrom(r.getString("origin"));
            flight.setTo(r.getString("destination"));
            flight.setDepartureTime(r.getString("departureDate"));
            flight.setArrivalTime(r.getString("returnDate"));
            flight.setEcoCapacity(r.getInt("ecoCapacity"));
            flight.setBusCapacity(r.getInt("busCapacity"));
            flight.setEcoPrice(r.getInt("ecoPrice"));
            flight.setBusPrice(r.getInt("busPrice"));
            flight.setAirline(r.getString("airline"));
            flights.add(flight);
        }
        System.out.println("Fjöldi úr leit " + flights.size());

        String q ="INSERT INTO queries(origin,destination, departureTime, " +
                "departureDate, seatingClass, maxPrice) " +
                "VALUES(?,?,?,?,?,?)";
        p = conn.prepareStatement(q);
        p.setString(1, query.getOrigin());
        p.setString(2,query.getDestination());
        p.setInt(3,query.getDepartureTime());
        p.setInt(4,query.getDepartureDate());
        p.setString(5,query.getSeatingClass());
        p.setInt(6,query.getMaxPrice());
        p.executeUpdate();
        r.close();

        return flights;
    }

    public ArrayList<Flight> getFlights() { return flights; }


    /*
    ADMIN FÖll
     */

    public int getNrOffSearches(String startDate, String endDate) throws SQLException {
        String query = "SELECT count(*) FROM queries WHERE searchDate > " + startDate + " AND searchDate < " + endDate;

        PreparedStatement p = conn.prepareStatement(query);
        ResultSet r = p.executeQuery();
        return r.getInt(1);
    }

    public int getNrOffBookings(String startDate, String endDate) throws SQLException {
        String query = "SELECT count(*) FROM queries WHERE bookingDate > " + startDate + " AND bookingDate < " + endDate;

        PreparedStatement p = conn.prepareStatement(query);
        ResultSet r = p.executeQuery();
        return r.getInt(1);
    }




}
