package dao;

import db.AirportDB;
import entity.Airport;

import java.util.List;

public class AirportDao {
    AirportDB airportDB = AirportDB.getInstance();

    public void create(Airport airport) {
        airportDB.create(airport);
    }

    public void update(Airport airport) {
        airportDB.update(airport);
    }

    public void delete(String id) {
        airportDB.delete(id);
    }

    public Airport findOne(String id) {
       return airportDB.findOne(id);
    }

    public List<Airport> findAll() {
        return airportDB.findAll();
    }
}
