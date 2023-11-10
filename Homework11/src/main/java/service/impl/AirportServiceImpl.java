package service.impl;

import dao.AirportDao;
import dao.Impl.AirportDaoImpl;
import entity.Airport;
import service.AirportService;

import java.util.List;

public class AirportServiceImpl implements AirportService {

    AirportDao airportDao = new AirportDaoImpl();
    @Override
    public void create(Airport entity) {
        isEmpty(entity);
        containsOnlyLettersAndSpaces(entity.getName());
        airportDao.create(entity);
    }

    @Override
    public void update(Airport entity) {
        isEmpty(entity);
        containsOnlyLettersAndSpaces(entity.getName());
        airportDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        airportDao.delete(id);
    }

    @Override
    public Airport findOne(Long id) {
        if(id == null) {
            System.out.println("The field cannot be empty.");
            throw new RuntimeException();
        }
        checkExistById(id);
        return airportDao.findOne(id);
    }

    @Override
    public List<Airport> findAll() {
        return airportDao.findAll();
    }

    @Override
    public void isEmpty(Airport entity) {
        if(entity.getName().isEmpty()) {
            System.out.println("The field cannot be empty.");
            throw new RuntimeException();
        }
    }

    private void containsOnlyLettersAndSpaces(String entity) {
        if(!entity.matches("[a-zA-Z ]+")) {
            System.out.println("The name of the airport must consist only of letters.");
            throw new RuntimeException();
        }
    }


    private void checkExistById(Long id) {
        if (!airportDao.existsById(String.valueOf(id))) {
            System.out.println("Airport not found.");
            throw new RuntimeException();
        }
    }
}
