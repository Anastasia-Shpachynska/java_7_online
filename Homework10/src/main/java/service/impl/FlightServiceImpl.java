package service.impl;

import dao.FlightDao;
import entity.Flight;
import service.FlightService;

import java.util.List;

public class FlightServiceImpl implements FlightService {
    FlightDao flightDao = new FlightDao();
    @Override
    public void create(Flight flight) {
        flightDao.create(flight);
    }

    @Override
    public void update(Flight flight) {
        flightDao.update(flight);
    }

    @Override
    public void delete(String id) {
        flightDao.delete(id);
    }

    @Override
    public Flight findOne(String id) {
        return flightDao.findOne(id);
    }

    @Override
    public List<Flight> findAll() {
        return flightDao.findAll();
    }
}
