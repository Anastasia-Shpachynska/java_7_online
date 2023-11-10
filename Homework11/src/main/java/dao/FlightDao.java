package dao;

import entity.Flight;

import java.util.List;

public interface FlightDao extends CRUDDao<Flight>{
    Flight findOne(Long id);
    List<Flight> findAll();
    boolean existsById(String id);
}
