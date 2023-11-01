package entity;

public class AirportFlight extends BaseEntity {
    private String firstAirportId;
    private String secondAirportId;
    private String flightId;

    public String getFirstAirportId() {
        return firstAirportId;
    }

    public void setFirstAirportId(String firstAirportId) {
        this.firstAirportId = firstAirportId;
    }

    public String getSecondAirportId() {
        return secondAirportId;
    }

    public void setSecondAirportId(String secondAirportId) {this.secondAirportId = secondAirportId;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }
}
