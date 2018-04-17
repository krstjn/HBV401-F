package is.hi.controller;

import is.hi.model.Flight;
import is.hi.model.Passenger;
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

    public void bookFlight(Passenger passenger) throws SQLException {
        String u = "INSERT INTO passengers(firstName, lastName, gender, seat, luggage, class, birthdate, flightID)" +
                "VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement p = conn.prepareStatement(u);
        p.setString(1, passenger.getFirst());
        p.setString(2, passenger.getLast());
        p.setString(3, String.valueOf(passenger.getGender()));
        p.setString(4, passenger.getSeatNumber());
        p.setBoolean(5, passenger.getLuggage());
        p.setString(6, passenger.getSeatingClass());
        p.setString(7, passenger.getBirthDate());
        p.setString(8, passenger.getFlightID());

        p.executeUpdate();

        if(passenger.getSeatingClass() == "eco"){
            u = "UPDATE flights SET ecoCapacity = ecoCapacity - 1 WHERE flightID == " + passenger.getFlightID();
        } else {
            u = "UPDATE flights SET busCapacity = busCapacity - 1 WHERE flightID == " + passenger.getFlightID();
        }
        p = conn.prepareStatement(u);
        p.executeUpdate();
    }

    /*
    ADMIN FÖll
     */

    public boolean checkAdmin(String username, String password) throws SQLException {
        String q = "SELECT * FROM users WHERE username = " + username + " AND password = " + password;
        PreparedStatement p = conn.prepareStatement(q);
        ResultSet r = p.executeQuery();
        return r.getFetchSize() == 1;
    }

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

    public ArrayList<String> getMostSearched(String startDate, String endDate) throws SQLException {
        String query = "SELECT origin, COUNT(origin) AS cnt FROM queries WHERE bookingDate >= " + startDate +
                " AND bookingDate <= " + endDate +  "GROUP BY origin ORDER BY cnt DESC";
        PreparedStatement p = conn.prepareStatement(query);
        ResultSet r = p.executeQuery();

        ArrayList<String> list = new ArrayList<>();
        while(r.next()) {
            list.add(r.getString("origin"));
        }
        r.close();
        return list;
    }
}
