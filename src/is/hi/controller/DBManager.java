package is.hi.controller;

import is.hi.model.Query;

import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;

/************************
 * Höfundur: Kristján P.*
 ************************/
public class DBManager {
    private Connection conn;
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
}
