package service;

import entity.Airport;
import entity.AirportFlight;
import entity.Flight;

import java.util.List;

public interface AirportFlightService extends CRUDService<AirportFlight>{
    void deleteAirport(Long id);
    List<AirportFlight> findAirportFlights(Long id);
    List<AirportFlight> findFlightAirports(Long id);
    List<String> findAllAirports();
    List<AirportFlight> findAllFlight();

    void isEmpty(AirportFlight entity);
}
