package db;

import com.google.gson.Gson;
import entity.Airport;
import entity.AirportFlight;
import entity.Flight;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AirportFlightDB {
    AirportDB airportDB = AirportDB.getInstance();
    FlightDB flightDB = FlightDB.getInstance();

    private static AirportFlightDB instance;
    private final List<AirportFlight> airportsFlights = new ArrayList<>();
    private final File airportFlightFile = new File("src/main/java/db/json/airportsFlights.json");

    private void readJson() {
        Gson gson = new Gson();
        try (FileReader fileReader = new FileReader(airportFlightFile)) {
            AirportFlight[] airportsFlights1 = gson.fromJson(fileReader, AirportFlight[].class);
            if (airportsFlights1 != null) {
                airportsFlights.clear();
                airportsFlights.addAll(Arrays.asList(airportsFlights1));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void writeJson() {
        Gson gson = new Gson();
        try (FileWriter fileWriter = new FileWriter(airportFlightFile)) {
            String json = gson.toJson(airportsFlights);
            fileWriter.write(json);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static AirportFlightDB getInstance() {
        if(instance == null) {
            instance = new AirportFlightDB();
        }
        return instance;
    }

    public boolean add(AirportFlight airportFlight) {
        readJson();
        for (AirportFlight airportsFlight : airportsFlights) {
            if(airportsFlight.getFlightId().equals(airportFlight.getFlightId())) {
                return false;
            }
        }
        airportsFlights.add(airportFlight);
        writeJson();
        return true;
    }

    public void update(AirportFlight airportFlight) {
        readJson();
        for (AirportFlight airportsFlight : airportsFlights) {
            if(airportsFlight.getFlightId().equals(airportFlight.getFlightId())) {
                airportsFlight = airportFlight;
            }
        }
        writeJson();
    }

    public boolean deleteFlight(String id) {
        readJson();
        Iterator<AirportFlight> iterator = airportsFlights.iterator();
        while (iterator.hasNext()) {
            AirportFlight airportFlight = iterator.next();
            if(airportFlight.getFlightId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        writeJson();
        return false;
    }

    public boolean deleteAirport(String id) {
        readJson();
        boolean deleted = false;
        Iterator<AirportFlight> iterator = airportsFlights.iterator();
        while (iterator.hasNext()) {
            AirportFlight airportFlight = iterator.next();
            if (airportFlight.getFirstAirportId().equals(id) || airportFlight.getSecondAirportId().equals(id)) {
                iterator.remove();
                deleted = true;
            }
        }
        writeJson();
        return deleted;
    }

    public  List<String> findAirport(String id) {
        readJson();
        List<String> list = new ArrayList<>();
        for (AirportFlight airportsFlight : airportsFlights) {
            if(airportsFlight.getFirstAirportId().equals(id) || airportsFlight.getSecondAirportId().equals(id)) {
                list.add(airportsFlight.getFlightId());
            }
        }
        return list;
    }

    public Flight findFlight(String id) {
        readJson();
        for (AirportFlight airportsFlight : airportsFlights) {
            if(airportsFlight.getFlightId().equals(id)) {
                Flight flight = new Flight();
                flight.setId(id);
                flight.setDepartureLocation(airportsFlight.getFirstAirportId());
                flight.setDestinationLocation(airportsFlight.getSecondAirportId());
                return flight;
            }
        }
        return null;
    }

    public List<Airport> findAllAirports() {
        readJson();
        Set<Airport> airports = new HashSet<>();
        for (AirportFlight airportsFlight : airportsFlights) {
            for (Airport airport : airportDB.findAll()) {
                if(airportsFlight.getFirstAirportId().equals(airport.getId()) || airportsFlight.getSecondAirportId().equals(airport.getId())) {
                    airports.add(airport);
                }
            }
        }
        return new ArrayList<>(airports);
    }

    public List<Flight> findAllFlights() {
        readJson();
        Set<Flight> flights = new HashSet<>();
        for (AirportFlight airportsFlight : airportsFlights) {
            for (Flight flight : flightDB.findAll()) {
                if(airportsFlight.getFlightId().equals(flight.getId())) {
                    flights.add(flight);
                }
            }
        }
        return new ArrayList<>(flights);
    }
}
