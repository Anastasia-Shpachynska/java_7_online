package service;

import entity.Airport;

import java.util.List;

public interface AirportService {
    void create(Airport airport);
    void update(Airport airport);
    void delete(String id);
    Airport findOne(String id);
    List<Airport> findAll();
}
