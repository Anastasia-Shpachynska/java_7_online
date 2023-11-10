package dao;

import entity.Airport;

import java.util.List;

public interface AirportDao extends CRUDDao<Airport>{
    Airport findOne(Long id);
    List<Airport> findAll();
    boolean existsById(String id);
}
