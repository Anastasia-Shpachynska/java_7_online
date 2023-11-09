package service.impl;

import dao.AirportFlightDao;
import entity.Airport;
import entity.AirportFlight;
import entity.Flight;
import service.AirportFlightService;

import java.util.List;

public class AirportFlightServiceImpl implements AirportFlightService {
    AirportFlightDao airportFlightDao = new AirportFlightDao();

    @Override
    public boolean add(AirportFlight airportFlight) {
        return airportFlightDao.add(airportFlight);
    }

    @Override
    public void update(AirportFlight airportFlight) {

    }

    @Override
    public boolean deleteFlight(String id) {
        return airportFlightDao.deleteFlight(id);
    }

    @Override
    public boolean deleteAirport(String id) {
        return airportFlightDao.deleteAirport(id);
    }

    @Override
    public  List<String> findAirport(String id) {
        return airportFlightDao.findAirport(id);
    }

    @Override
    public Flight findFlight(String id) {
       return airportFlightDao.findFlight(id);
    }

    @Override
    public List<Airport> findAllAirports() {
        return airportFlightDao.findAllAirports();
    }

    @Override
    public List<Flight> findAllFlights() {
        return airportFlightDao.findAllFlights();
    }
}
