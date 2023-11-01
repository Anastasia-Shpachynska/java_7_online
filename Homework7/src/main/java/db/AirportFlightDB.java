package db;

import entity.Airport;
import entity.AirportFlight;
import entity.Flight;

import java.util.*;

public class AirportFlightDB {
    AirportDB airportDB = AirportDB.getInstance();
    FlightDB flightDB = FlightDB.getInstance();

    private static AirportFlightDB instance;
    private final List<AirportFlight> airportsFlights = new ArrayList<>();

    private AirportFlightDB() {}

    public static AirportFlightDB getInstance() {
        if(instance == null) {
            instance = new AirportFlightDB();
        }
        return instance;
    }

    public boolean add(AirportFlight airportFlight) {
        for (AirportFlight airportsFlight : airportsFlights) {
            if(airportsFlight.getFlightId().equals(airportFlight.getFlightId())) {
                return false;
            }
        }
        airportsFlights.add(airportFlight);
        return true;
    }

    public void update(AirportFlight airportFlight) {
        for (AirportFlight airportsFlight : airportsFlights) {
            if(airportsFlight.getFlightId().equals(airportFlight.getFlightId())) {
                airportsFlight = airportFlight;
            }
        }
    }

    public boolean deleteFlight(String id) {
        Iterator<AirportFlight> iterator = airportsFlights.iterator();
        while (iterator.hasNext()) {
            AirportFlight airportFlight = iterator.next();
            if(airportFlight.getFlightId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean deleteAirport(String id) {
        boolean deleted = false;
        Iterator<AirportFlight> iterator = airportsFlights.iterator();
        while (iterator.hasNext()) {
            AirportFlight airportFlight = iterator.next();
            if (airportFlight.getFirstAirportId().equals(id) || airportFlight.getSecondAirportId().equals(id)) {
                iterator.remove();
                deleted = true;
            }
        }
        return deleted;
    }

    public  List<String> findAirport(String id) {
        List<String> list = new ArrayList<>();
        for (AirportFlight airportsFlight : airportsFlights) {
            if(airportsFlight.getFirstAirportId().equals(id) || airportsFlight.getSecondAirportId().equals(id)) {
                list.add(airportsFlight.getFlightId());
            }
        }
        return list;
    }

    public Flight findFlight(String id) {
        for (AirportFlight airportsFlight : airportsFlights) {
            if(airportsFlight.getFlightId().equals(id)) {
                Flight flight = new Flight();
                flight.setId(id);
                flight.setDepartureLocation(airportsFlight.getFirstAirportId());
                flight.setDestinationLocation(airportsFlight.getSecondAirportId());
                return flight;
            }
        }
        return null;
    }

    public List<Airport> findAllAirports() {
        Set<Airport> airports = new HashSet<>();
        for (AirportFlight airportsFlight : airportsFlights) {
            for (Airport airport : airportDB.findAll()) {
                if(airportsFlight.getFirstAirportId().equals(airport.getId()) || airportsFlight.getSecondAirportId().equals(airport.getId())) {
                    airports.add(airport);
                }
            }
        }
        return new ArrayList<>(airports);
    }

    public List<Flight> findAllFlights() {
        Set<Flight> flights = new HashSet<>();
        for (AirportFlight airportsFlight : airportsFlights) {
            for (Flight flight : flightDB.findAll()) {
                if(airportsFlight.getFlightId().equals(flight.getId())) {
                    flights.add(flight);
                }
            }
        }
        return new ArrayList<>(flights);
    }
}
