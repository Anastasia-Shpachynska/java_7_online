package service;

import entity.Flight;

import java.util.List;

public interface FlightService {
    void create(Flight flight);
    void update(Flight flight);
    void delete(String id);
    Flight findOne(String id);
    List<Flight> findAll();
}
