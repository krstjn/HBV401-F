package is.hi.model;

import java.util.Date;

public class Query {
    private String origin = null;
    private String destination = null;
    private String departureTime = null;
    private String arrivalTime = null;
    private Date departureDate = null;
    private Date returnDate = null;
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

    public String getDepartureTime() {
        return departureTime;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getReturnDate() {
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

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime;}

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void setSeatingClass(String seatingClass) {
        this.seatingClass = seatingClass;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public void setReturnDate(Date arrivalDate) {
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
        return "origin = " + origin;
    }
    private String getDestinationString() {
        if(destination == null) return null;
        return "destination = " + destination;
    }
    private String getDepartureTimeString() {
        if(departureTime == null) return "";
        return "departureTime = " + departureTime;
    }
    private String getArrivalTimeTimeString() {
        if(departureTime == null) return "";
        return "arrivalTime = " + arrivalTime;
    }
    private String getAirlineString() {
        if(airline == null) return null;
        return "airline = " + airline;
    }
    private String getSeatingClassString() {
        if(seatingClass == null) return null;
        return "seatingClass = " + seatingClass;
    }
    private String getDepartureDateString() {
        if(departureDate == null) return null;
        return "departureDate = " + departureDate;
    }
    private String getMaxPriceString() {
        if(maxPrice < 0) return null;
        return "price <= " + maxPrice;
    }
    private String getReturnDateString() {
        if(returnDate == null) return null;
        return "departureDate= " + returnDate;
    }

    /**
     * Skilar streng sem verður verður settur inn í queries töfluna.
     * @return String
     */
    public String getQueries(){
        return "";
    }

    public String[] getFilters(){
        String[] queries = {
                getOriginString(),
                getDestinationString(),
                //getDepartureTimeString(),
                //getDepartureDateString(),
                //getReturnDateString(),
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
