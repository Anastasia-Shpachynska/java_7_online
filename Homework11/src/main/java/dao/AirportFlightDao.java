package dao;

import entity.Airport;
import entity.AirportFlight;
import entity.Flight;

import java.util.List;

public interface AirportFlightDao extends CRUDDao<AirportFlight>{
    void deleteAirport(Long id);
    List<AirportFlight> findAirportFlights(Long id);
    List<AirportFlight> findFlightAirports(Long id);
    List<String> findAllAirports();
    List<AirportFlight> findAllFlight();
    boolean existsByIdFlight(Long id);
    boolean existsByIdAirport(Long id);
}

