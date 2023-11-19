package service.impl;

import dao.FlightDao;
import dao.Impl.FlightDaoImpl;
import entity.Flight;
import service.FlightService;

import java.util.List;

public class FlightServiceImpl implements FlightService {

    FlightDao flightDao = new FlightDaoImpl();

    @Override
    public void create(Flight entity) {
        isEmpty(entity);
        containsOnlyLettersAndSpaces(entity);
        flightDao.create(entity);
    }

    @Override
    public void update(Flight entity) {
        isEmpty(entity);
        containsOnlyLettersAndSpaces(entity);
        flightDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        flightDao.delete(id);
    }

    @Override
    public Flight findOne(Long id) {
        if(id == null) {
            System.out.println("The field cannot be empty.");
            throw new RuntimeException();
        }
        checkExistById(id);
        return flightDao.findOne(id);
    }

    @Override
    public List<Flight> findAll() {
        return flightDao.findAll();
    }

    @Override
    public void isEmpty(Flight entity) {
        if(entity.getDepartureLocation().isEmpty() || entity.getDestinationLocation().isEmpty()) {
            System.out.println("The field cannot be empty.");
            throw new RuntimeException();
        }
    }

    private void containsOnlyLettersAndSpaces(Flight entity) {
        if(!entity.getDestinationLocation().matches("[a-zA-Z ]+") || !entity.getDepartureLocation().matches("[a-zA-Z ]+")) {
            System.out.println("The departure location and destination location of the flight can only contain letters!");
            throw new RuntimeException();
        }
    }


    private void checkExistById(Long id) {
        if (!flightDao.existsById(id)) {
            System.out.println("Flight not found.");
            throw new RuntimeException();
        }
    }
}
