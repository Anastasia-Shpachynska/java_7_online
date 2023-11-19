package controller;

import entity.Airport;
import entity.AirportFlight;
import entity.Flight;
import service.AirportFlightService;
import service.AirportService;
import service.FlightService;
import service.impl.AirportFlightServiceImpl;
import service.impl.AirportServiceImpl;
import service.impl.FlightServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class AirportFlightController {
    AirportService airportService = new AirportServiceImpl();
    FlightService flightService = new FlightServiceImpl();
    AirportFlightService airportFlightService = new AirportFlightServiceImpl();

    public void start() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            menu();
            String select;
            for (select = bufferedReader.readLine(); select != null; select = bufferedReader.readLine()) {
                select(select, bufferedReader);
            }
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void menu() {
        System.out.println("Add a flight to the airport  - 1");
        System.out.println("Update the flight at the airport - 2");
        System.out.println("Delete the flight from the airport - 3");
        System.out.println("Delete the airport (and flight) - 4");
        System.out.println("Find the airport and its flights - 5");
        System.out.println("Find the flight and its airports - 6");
        System.out.println("Find all unique airports in the 'relationship' table - 7");
        System.out.println("Find all unique flights in the 'relationship' table - 8");
        System.out.println("Return to the main menu - 9");
    }

    private void select(String select, BufferedReader bufferedReader) {
        Controller controller = new Controller();
        switch (select) {
            case "1" -> create(bufferedReader);
            case "2" -> update(bufferedReader);
            case "3" -> deleteFlight(bufferedReader);
            case "4" -> deleteAirport(bufferedReader);
            case "5" -> findAirport(bufferedReader);
            case "6" -> findFlight(bufferedReader);
            case "7" -> findAllAirports();
            case "8" -> findAllFlights();
            case "9" -> controller.start();
            default -> System.out.println("Incorrect option. Please, select from the proposed menu!");
        }
    }

    private void create(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the ID of the first airport: ");
            Long idFirst = Long.valueOf(bufferedReader.readLine());
            System.out.println("Enter the ID of the second airport: ");
            Long idSecond = Long.valueOf(bufferedReader.readLine());
            System.out.println("Enter the ID of the flight: ");
            Long idFlight = Long.valueOf(bufferedReader.readLine());

            airportService.findOne(idFirst);
            airportService.findOne(idSecond);
            flightService.findOne(idFlight);

            AirportFlight airportFlight = new AirportFlight();
            airportFlight.setFirstAirportId(idFirst);
            airportFlight.setSecondAirportId(idSecond);
            airportFlight.setFlightId(idFlight);
            airportFlightService.create(airportFlight);
            System.out.println("The relationship is established.");
        } catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void update(BufferedReader bufferedReader) {
        try{
            System.out.println("Enter the ID of the flight: ");
            Long id = Long.valueOf(bufferedReader.readLine());
            airportFlightService.findFlightAirports(id);
            System.out.println("Enter a new ID of the first airport: ");
            Long newFirstAirport = Long.valueOf(bufferedReader.readLine());
            System.out.println("Enter a new ID of the second airport: ");
            Long newSecondAirport = Long.valueOf(bufferedReader.readLine());

            AirportFlight airportFlight = new AirportFlight();
            airportFlight.setFlightId(id);
            airportFlight.setFirstAirportId(newFirstAirport);
            airportFlight.setSecondAirportId(newSecondAirport);
            airportFlightService.update(airportFlight);
            System.out.println("Successfully updated");
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void deleteFlight(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the ID of the flight:");
            Long id = Long.valueOf(bufferedReader.readLine());
            airportFlightService.delete(id);
            System.out.println("Successfully deleted.");
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void deleteAirport(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the ID of the airport:");
            Long id = Long.valueOf(bufferedReader.readLine());
            airportFlightService.deleteAirport(id);
            System.out.println("Successfully deleted.");
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void findAirport(BufferedReader bufferedReader) {
        try{
            System.out.println("Enter the ID of the airport: ");
            Long id = Long.valueOf(bufferedReader.readLine());
            List<AirportFlight> airportFlights = airportFlightService.findAirportFlights(id);
            System.out.println("The " + id + " has relationship with: ");
            Flight flight = new Flight();
            for (AirportFlight airportFlight : airportFlights) {
                System.out.println(flight = flightService.findOne(airportFlight.getFlightId()));
            }
        }catch(IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void findFlight(BufferedReader bufferedReader) {
        try{
            System.out.println("Enter the ID of the flight: ");
            Long id = Long.valueOf(bufferedReader.readLine());
            List<AirportFlight> airportFlights = airportFlightService.findFlightAirports(id);
            System.out.println("The " + id + " has relationship with: ");
            Airport airport = new Airport();
            for (AirportFlight airportFlight : airportFlights) {
                System.out.println(airport = airportService.findOne(airportFlight.getFirstAirportId()));
                System.out.println(airport = airportService.findOne(airportFlight.getSecondAirportId()));
            }
        }catch(IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void findAllAirports() {
        System.out.println("All unique airports: ");
        List<String> airports = airportFlightService.findAllAirports();
        Airport airportFull = new Airport();
        for (String airport : airports) {
            System.out.println(airportFull = airportService.findOne(Long.valueOf(airport)));
        }
    }

    private void findAllFlights() {
        System.out.println("All unique flights: ");
        List<AirportFlight> airportFlights = airportFlightService.findAllFlight();
        Flight flightFull = new Flight();
        for (AirportFlight airportFlight : airportFlights) {
            System.out.println(flightFull = flightService.findOne(airportFlight.getFlightId()));
        }
    }
}
