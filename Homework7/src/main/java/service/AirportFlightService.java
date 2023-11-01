package service;

import entity.Airport;
import entity.AirportFlight;
import entity.Flight;

import java.util.List;

public interface AirportFlightService {
    boolean add(AirportFlight airportFlight);
    void update(AirportFlight airportFlight);
    boolean deleteFlight(String id);
    boolean deleteAirport(String id);
    List<String> findAirport(String id);
    Flight findFlight(String id);
    List<Airport> findAllAirports();
    List<Flight> findAllFlights();
}
