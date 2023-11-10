package service;

import entity.Airport;
import entity.Flight;

import java.util.List;

public interface FlightService extends CRUDService<Flight> {
    Flight findOne(Long id);
    List<Flight> findAll();
    void isEmpty(Flight entity);
}
