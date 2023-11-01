package db;

import entity.AirportFlight;
import entity.Flight;
import util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class FlightDB {
    private static FlightDB instance;
    private final List<Flight> flights = new ArrayList<>();

    private FlightDB() {}

    public static FlightDB getInstance() {
        if(instance == null) {
            instance = new FlightDB();
        }
        return instance;
    }

    public void create(Flight flight) {
        String id = AppUtil.getUUID();
        flight.setId(id);
        flights.add(flight);
    }

    public void update(Flight flight) {
        for (Flight flightOne : flights) {
            if(flightOne.getId().equals(flight.getId())) {
                flightOne = flight;
            }
        }
    }

    public void delete(String id) {
        flights.removeIf(flight -> flight.getId().equals(id));
        AirportFlightDB airportFlightDB = AirportFlightDB.getInstance();
        airportFlightDB.deleteFlight(id);
    }

    public Flight findOne(String id) {
        return flights
                .stream()
                .filter(flight -> flight.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Flight> findAll () {
        return flights;
    }

}
