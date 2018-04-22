package is.hi.model;

import java.time.LocalDate;
import java.util.Date;

public class Query {
    private String origin = null;
    private String destination = null;
    private int departureTime = -1;
    private int arrivalTime = -1;
    private int departureDate = -1;
    private int returnDate = -1;
    private String airline = null;
    private String seatingClass = null;
    private int maxPrice = -1;
    /**
     * Smiður fyrir Query, ekki gert ráð fyrir neinni viðfangbreytu
     * búist við því að notaðir séu setters.
     */
    public Query() { }

    /**
     * Getters fyrir tilviksbreytur
     */
    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getDepartureDate() {
        return departureDate;
    }

    public int getReturnDate() {
        return returnDate;
    }

    public String getAirline() {
        return airline;
    }

    public String getSeatingClass() {
        return seatingClass;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    /**
     * Setters fyrir tilviksbreytur
     */

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(int arrivalTime) { this.arrivalTime = arrivalTime;}

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void setSeatingClass(String seatingClass) {
        this.seatingClass = seatingClass;
    }

    public void setDepartureDate(int departureDate) {
        this.departureDate = departureDate;
    }

    public void setReturnDate(int arrivalDate) {
        this.returnDate = arrivalDate;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * Getters sem notaðir eru til að búa til leitarstreng.
     */
    private String getOriginString() {
        if(origin == null) return null;
        return "origin = '" + origin + "'";
    }
    private String getDestinationString() {
        if(destination == null) return null;
        return "destination = '" + destination + "'";
    }
    private String getDepartureTimeString() {
        if(departureTime < 0) return null;
        return "departureTime < " + departureTime + " AND departureTime >= " + (departureTime-600);
    }
    private String getArrivalTimeTimeString() {
        if(departureTime < 0) return null;
        return "arrivalTime = " + arrivalTime;
    }
    private String getAirlineString() {
        if(airline == null) return null;
        return "airline = '" + airline + "'";
    }
    private String getSeatingClassString() {
        if(seatingClass == null) return null;
        if(seatingClass.equals("Economy")) return "ecoCapacity <> 0";
        else return "busCapacity <> 0" ;
    }
    private String getDepartureDateString() {
        if(departureDate < 0) {
            LocalDate today = LocalDate.now();
            String year = String.valueOf(today.getYear());
            int m = today.getMonthValue();
            System.out.println("month " + m);
            int d = today.getDayOfMonth();
            System.out.println("day "+d);
            String month = String.valueOf(m);
            String day = String.valueOf(d);
            if(m < 10)
                month = "0"+month;
            if(d < 10 )
                day = "0"+day;

            return "departureDate >= " +year+month+day;
        }
        return "departureDate = " + departureDate;
    }
    private String getMaxPriceString() {
        if(maxPrice < 0) return null;
        return "(busPrice <= " + maxPrice + " OR ecoPrice <= " + maxPrice + ")";
    }
    private String getReturnDateString() {
        if(returnDate < 0) return null;
        return "returnDate= " + returnDate;
    }

    /**
     * Skilar streng sem verður verður settur inn í queries töfluna.
     * @return String
     */
    public String getQueries(){
        String q = "INSERT INTO queries(id, origin,destination, departureTime, " +
                "departureDate, duration, returnDate, availableSeats, seatingClass, maxPrice) " +
                "VALUES(";
        if(getOrigin() != null) q += getOrigin()+",";
        return "";
    }

    public String[] getFilters(){

        String[] queries = {
                getOriginString(),
                getDestinationString(),
                getDepartureTimeString(),
                getDepartureDateString(),
                getReturnDateString(),
                getAirlineString(),
                getSeatingClassString(),
                getMaxPriceString()
        };

        return queries;
    }

    /**
     * Skilar streng sem leitar að flugum
     * @return String
     */
    public String toString(){
        String q = "SELECT * FROM flights WHERE ";
        String[] filter = getFilters();
        boolean first = true;
        for(int i = 0; i <filter.length; i++){
            if(filter[i] != null){
                if(first) q += filter[i];
                else q +=" AND " + filter[i];
                first = false;
            }
        }
        System.out.println(q);
        return q;
    }
}
