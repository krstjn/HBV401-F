package is.hi.model;

import java.util.Date;

public class Query {
    private String origin = null;
    private String destination = null;
    private String departureTime = null;
    private Date departureDate = null;
    private Date returnDate;
    private String airline;
    private String seatingClass;
    private int maxPrice;

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
    public void setReturnDate(Date arrivalDate) {
        this.returnDate = arrivalDate;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void setSeatingClass(String seatingClass) {
        this.seatingClass = seatingClass;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Getters sem notaðir eru til að búa til leitarstreng.
     */
    private String getReturnDateString() {
        return "arrivalDate = " + returnDate;
    }
    private String getOriginString() {
        return "origin = " + origin;
    }
    private String getDestinationString() {
        return "destination = " + destination;
    }
    private String getDepartureTimeString() {
        return "departureTime = " + departureTime;
    }
    private String getAirlineString() {
        return "airline = " + airline;
    }
    private String getSeatingClassString() {
        return "seatingClass = " + seatingClass;
    }
    private String getDepartureDateString() {
        return "departureDate = " + departureDate;
    }
    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }
    private String getMaxPriceString() {
        return "maxPrice = " + maxPrice;
    }

    /**
     * Skilar streng sem verður verður settur inn í queries töfluna.
     * @return String
     */
    public String getQuery(){
        return "";
    }

    /**
     * Skilar streng sem leitar að flugum
     * @return String
     */
    public String toString(){
        return "";
    }
}
