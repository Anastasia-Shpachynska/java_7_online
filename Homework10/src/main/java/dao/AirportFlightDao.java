package dao;

import db.AirportFlightDB;
import entity.Airport;
import entity.AirportFlight;
import entity.Flight;

import java.util.List;

public class AirportFlightDao {
    AirportFlightDB airportFlightDB = AirportFlightDB.getInstance();


    public boolean add(AirportFlight airportFlight) {
        return airportFlightDB.add(airportFlight);
    }

    public void update(AirportFlight airportFlight) {
        airportFlightDB.update(airportFlight);
    }

    public boolean deleteFlight(String id) {
        return airportFlightDB.deleteFlight(id);
    }

    public boolean deleteAirport(String id) {
        return airportFlightDB.deleteAirport(id);
    }

    public  List<String> findAirport(String id) {
        return airportFlightDB.findAirport(id);
    }

    public Flight findFlight(String id) {
        return airportFlightDB.findFlight(id);
    }

    public List<Airport> findAllAirports() {
        return airportFlightDB.findAllAirports();
    }

    public List<Flight> findAllFlights() {
        return airportFlightDB.findAllFlights();
    }
}
