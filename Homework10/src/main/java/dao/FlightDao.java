package dao;

import db.FlightDB;
import entity.Flight;

import java.util.List;

public class FlightDao {
    FlightDB flightDB = FlightDB.getInstance();
    public void create(Flight flight) {
        flightDB.create(flight);
    }

    public void update(Flight flight) {
        flightDB.update(flight);
    }

    public void delete(String id) {
        flightDB.delete(id);
    }

    public Flight findOne(String id) {
        return flightDB.findOne(id);
    }

    public List<Flight> findAll() {
        return flightDB.findAll();
    }
}
