package service;

import entity.Airport;

public interface AirportService extends CRUDService<Airport> {
    void addFlightToAirportById(Long airportId, Long flightId);
    void deleteFlightToAirportById(Long airportId, Long flightId);
}
