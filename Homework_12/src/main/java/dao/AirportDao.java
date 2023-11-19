package dao;

import entity.Airport;

public interface AirportDao extends CRUDDao<Airport>{
    void addFlightToAirportById(Long airportId, Long flightId);
    void deleteFlightToAirportById(Long airportId, Long flightId);
}
