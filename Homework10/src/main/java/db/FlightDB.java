package db;

import com.google.gson.Gson;
import entity.Airport;
import entity.Flight;
import util.AppUtil;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightDB {
    private static FlightDB instance;
    private final List<Flight> flights = new ArrayList<>();
    private final File flightFile = new File("src/main/java/db/json/flights.json");

    public static FlightDB getInstance() {
        if(instance == null) {
            instance = new FlightDB();
        }
        return instance;
    }

    private void readJson() {
        Gson gson = new Gson();
        try (FileReader fileReader = new FileReader(flightFile)) {
            Flight[] flights1 = gson.fromJson(fileReader, Flight[].class);
            if (flights1 != null) {
                flights.clear();
                flights.addAll(Arrays.asList(flights1));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void writeJson() {
        Gson gson = new Gson();
        try (FileWriter fileWriter = new FileWriter(flightFile)) {
            String json = gson.toJson(flights);
            fileWriter.write(json);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void create(Flight flight) {
        readJson();
        String id = AppUtil.getUUID();
        flight.setId(id);
        flights.add(flight);
        writeJson();
    }

    public void update(Flight flight) {
        readJson();
        for (Flight flightOne : flights) {
            if(flightOne.getId().equals(flight.getId())) {
                flightOne = flight;
            }
        }
        writeJson();
    }

    public void delete(String id) {
        readJson();
        flights.removeIf(flight -> flight.getId().equals(id));
        AirportFlightDB airportFlightDB = AirportFlightDB.getInstance();
        airportFlightDB.deleteFlight(id);
        writeJson();
    }

    public Flight findOne(String id) {
        readJson();
        return flights
                .stream()
                .filter(flight -> flight.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Flight> findAll () {
        readJson();
        return flights;
    }

}
