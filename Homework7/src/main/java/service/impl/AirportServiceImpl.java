package service.impl;

import dao.AirportDao;
import entity.Airport;
import service.AirportService;

import java.util.List;

public class AirportServiceImpl implements AirportService {
    AirportDao airportDao = new AirportDao();

    @Override
    public void create(Airport airport) {
        airportDao.create(airport);
    }

    @Override
    public void update(Airport airport) {
        airportDao.update(airport);
    }

    @Override
    public void delete(String id) {
        airportDao.delete(id);
    }

    @Override
    public Airport findOne(String id) {
        return airportDao.findOne(id);
    }

    @Override
    public List<Airport> findAll() {
        return airportDao.findAll();
    }
}
