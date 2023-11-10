package entity;

public class AirportFlight extends BaseEntity {
    private Long firstAirportId;
    private Long secondAirportId;
    private Long flightId;

    public Long getFirstAirportId() {
        return firstAirportId;
    }

    public void setFirstAirportId(Long firstAirportId) {
        this.firstAirportId = firstAirportId;
    }

    public Long getSecondAirportId() {
        return secondAirportId;
    }

    public void setSecondAirportId(Long secondAirportId) {this.secondAirportId = secondAirportId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    @Override
    public String toString() {
        return "AirportFlight{" +
                "firstAirportId='" + firstAirportId + '\'' +
                ", flightId='" + flightId + '\'' +
                ", secondAirportId='" + secondAirportId + '\'' +
                '}';
    }
}
