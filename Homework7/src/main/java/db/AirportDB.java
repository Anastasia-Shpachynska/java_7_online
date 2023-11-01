package db;

import entity.Airport;
import util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class AirportDB {
    private static AirportDB instance;
    private final List<Airport> airports = new ArrayList<>();
    private AirportDB () {}

    public static AirportDB getInstance() {
        if(instance == null) {
            instance = new AirportDB();
        }
        return instance;
    }

    public void create(Airport airport) {
        String id = AppUtil.getUUID();
        airport.setId(id);
        airports.add(airport);
    }

    public void update(Airport airport) {
        for (Airport airportOne : airports) {
            if(airportOne.getId().equals(airport.getId())) {
                airportOne = airport;
            }
        }
    }

    public void delete(String id) {
       airports.removeIf(airport -> airport.getId().equals(id));
       AirportFlightDB airportFlightDB = AirportFlightDB.getInstance();
       airportFlightDB.deleteAirport(id);
    }

    public Airport findOne(String id) {
        return airports
                .stream()
                .filter(airport -> airport.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Airport> findAll () {
            return airports;
    }
}
