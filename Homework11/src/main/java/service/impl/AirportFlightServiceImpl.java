package service.impl;

import dao.AirportFlightDao;
import dao.Impl.AirportFlightDaoImpl;
import entity.Airport;
import entity.AirportFlight;
import entity.Flight;
import service.AirportFlightService;

import java.util.List;

public class AirportFlightServiceImpl implements AirportFlightService {

    AirportFlightDao airportFlightDao = new AirportFlightDaoImpl();

    @Override
    public void create(AirportFlight entity) {
        List<AirportFlight> AirportFlight = airportFlightDao.findAllFlight();
        for (AirportFlight airportFlight : AirportFlight) {
            if(airportFlight.getId().equals(entity.getFlightId())) {
                System.out.println("A flight with such an ID already exists!");
                throw new RuntimeException();
            }
        }
        isEmpty(entity);
        airportFlightDao.create(entity);
    }

    @Override
    public void update(AirportFlight entity) {
        isEmpty(entity);
        airportFlightDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        idIsEmpty(id);
        airportFlightDao.delete(id);
    }

    @Override
    public void deleteAirport(Long id) {
        idIsEmpty(id);
        airportFlightDao.deleteAirport(id);
    }

    @Override
    public List<AirportFlight> findAirportFlights(Long id) {
        idIsEmpty(id);
        checkExistByIdAirport(id);
        return airportFlightDao.findAirportFlights(id);
    }

    @Override
    public List<AirportFlight> findFlightAirports(Long id) {
        idIsEmpty(id);
        checkExistByIdFlight(id);
        return airportFlightDao.findFlightAirports(id);
    }

    @Override
    public List<String> findAllAirports() {
        return airportFlightDao.findAllAirports();
    }

    @Override
    public List<AirportFlight> findAllFlight() {
        return airportFlightDao.findAllFlight();
    }

    @Override
    public void isEmpty(AirportFlight entity) {
        if(entity.getFlightId() == null || entity.getFirstAirportId()== null || entity.getSecondAirportId() == null) {
            System.out.println("The field cannot be empty.");
            throw new RuntimeException();
        }
        if(entity.getFirstAirportId().equals(entity.getSecondAirportId())) {
            System.out.println("ID cannot be the same!");
            throw new RuntimeException();
        }
    }

    private void idIsEmpty(Long id) {
        if(id.equals(null)) {
            System.out.println("The field cannot be empty.");
            throw new RuntimeException();
        }
    }

    private void checkExistByIdAirport(Long id) {
        if (!airportFlightDao.existsByIdAirport(id)) {
            System.out.println(id + " not found.");
            throw new RuntimeException();
        }
    }

    private void checkExistByIdFlight(Long id) {
        if (!airportFlightDao.existsByIdFlight(id)) {
            System.out.println(id + " not found.");
            throw new RuntimeException();
        }
    }
}
