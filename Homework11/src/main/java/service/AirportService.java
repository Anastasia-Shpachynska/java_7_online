package service;

import entity.Airport;

import java.util.List;

public interface AirportService extends CRUDService<Airport> {
    Airport findOne(Long id);
    List<Airport> findAll();
    void isEmpty(Airport entity);
}
