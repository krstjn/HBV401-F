package is.hi.model;


public class Passenger {

    private String  firstName;
    private String  lastName;
    private char    gender;
    private boolean luggage;
    private String  seatingclass;
    private String  seatNumber;
    private String  birthDate;
    private String  flightID;

    /********* Smiður *********/
    public Passenger() { }

    /********* Getters *********/
    public String getFirst(){
        return firstName;
    }
    public String getLast(){
        return lastName;
    }
    public char getGender(){
        return gender;
    }
    public boolean getLuggage(){
        return luggage;
    }
    public String getSeatingClass(){
        return seatingclass;
    }
    public String getSeatNumber(){
        return seatNumber;
    }
    public String getBirthDate(){
        return birthDate;
    }

    public String getFlightID() {
        return flightID;
    }

    /********* Setters *********/
    public void setFirst(String fn){
        this.firstName = fn;
    }
    public void setLast(String ln){
        this.lastName = ln;
    }
    public void setGender(char gn){
        this.gender = gn;
    }
    public void setLuggage(boolean lg){
        this.luggage = lg;
    }
    public void setSeatingClass(String sc) {
        this.seatingclass = sc;
    }
    public void setSeatingNumber(String sn){
        this.seatNumber = sn;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }
}
