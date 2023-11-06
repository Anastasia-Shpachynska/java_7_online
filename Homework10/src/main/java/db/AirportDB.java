package db;

import com.google.gson.Gson;
import entity.Airport;
import util.AppUtil;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AirportDB {
    private static AirportDB instance;
    private final List<Airport> airports = new ArrayList<>();
    private final File airportFile = new File("src/main/java/db/json/airports.json");

    public static AirportDB getInstance() {
        if (instance == null) {
            instance = new AirportDB();
        }
        return instance;
    }

    private void readJson() {
        Gson gson = new Gson();
        try (FileReader fileReader = new FileReader(airportFile)) {
            Airport[] airports1 = gson.fromJson(fileReader, Airport[].class);
            if (airports1 != null) {
                airports.clear();
                airports.addAll(Arrays.asList(airports1));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void writeJson() {
        Gson gson = new Gson();
        try (FileWriter fileWriter = new FileWriter(airportFile)) {
            String json = gson.toJson(airports);
            fileWriter.write(json);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void create(Airport airport) {
        readJson();
        String id = AppUtil.getUUID();
        airport.setId(id);
        airports.add(airport);
        writeJson();
    }

    public void update(Airport airport) {
        readJson();
        for (Airport airportOne : airports) {
            if(airportOne.getId().equals(airport.getId())) {
                airportOne = airport;
            }
        }
        writeJson();
    }

    public void delete(String id) {
        readJson();
       airports.removeIf(airport -> airport.getId().equals(id));
       AirportFlightDB airportFlightDB = AirportFlightDB.getInstance();
       airportFlightDB.deleteAirport(id);
        writeJson();
    }

    public Airport findOne(String id) {
        readJson();
        return airports
                .stream()
                .filter(airport -> airport.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Airport> findAll () {
        readJson();
        return airports;
    }
}
