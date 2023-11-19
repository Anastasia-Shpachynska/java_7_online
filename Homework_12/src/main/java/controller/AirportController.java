package controller;

import entity.Airport;
import entity.Flight;
import service.AirportService;
import service.impl.AirportServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

public class AirportController {

    AirportService airportService = new AirportServiceImpl();

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
        System.out.println("Create new airport - 1");
        System.out.println("Update the airport - 2");
        System.out.println("Delete the airport - 3");
        System.out.println("Find the airport and its associated flights - 4");
        System.out.println("View all airports - 5");
        System.out.println("Add relationship - 6");
        System.out.println("Delete relationship - 7");
        System.out.println("Return to the main menu - 8");
    }

    private void select(String select, BufferedReader bufferedReader) throws IOException {
        Controller controller = new Controller();
        switch (select) {
            case "1" -> create(bufferedReader);
            case "2" -> update(bufferedReader);
            case "3" -> delete(bufferedReader);
            case "4" -> findOne(bufferedReader);
            case "5" -> findAll();
            case "6" -> addFlightToAirportById(bufferedReader);
            case "7" -> deleteFlightToAirportById(bufferedReader);
            case "8" -> controller.start();
            default -> System.out.println("Incorrect option. Please, select from the proposed menu!");
        }
    }

    private void create(BufferedReader bufferedReader) {
        try {
            System.out.println("Please, enter the name of the airport: ");
            String airportName = bufferedReader.readLine();
            Airport airport = new Airport();
            airport.setName(airportName);
            airportService.create(airport);
            System.out.println("Airport successfully added.");
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void update(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the airport ID: ");
            Long id = Long.valueOf(bufferedReader.readLine());
            Airport airport = airportService.findOne(id);
            System.out.println(airport);
            System.out.println("Enter a new name: ");
            String newName = bufferedReader.readLine();
            airport.setName(newName);
            airportService.update(airport);
            System.out.println("The airport update!");
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void delete(BufferedReader bufferedReader) {
        try{
            System.out.println("Enter the airport ID: ");
            Long id = Long.valueOf(bufferedReader.readLine());
            Airport airport = airportService.findOne(id);
            System.out.println(airport);
            airportService.delete(id);
            System.out.println("Airport successfully deleted.");
        }catch(IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void findOne(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the airport ID: ");
            Long id = Long.valueOf(bufferedReader.readLine());
            Airport airport = airportService.findOne(id);
            System.out.println("Airport: " + airport);
            Set<Flight> flights = airport.getFlights();
            if(flights != null) {
                System.out.println("Flight: " + flights);
            }
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void findAll() {
        System.out.println("All airports: ");
        List<Airport> airports = airportService.findAll();
        for (Airport airport : airports) {
            System.out.println(airport);
        }
    }

    private void addFlightToAirportById(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter airport id: ");
            Long airportId = Long.parseLong(bufferedReader.readLine());

            System.out.println("Enter flight id: ");
            Long flightId = Long.parseLong(bufferedReader.readLine());
            if (airportId != null && flightId != null) {
                airportService.addFlightToAirportById(airportId, flightId);
            } else {
                System.out.println("Field cannot be empty!");
                addFlightToAirportById(bufferedReader);
            }
        }catch (IOException ex) {
            System.out.println("Id does not fit the format.");
        }
    }

    private void deleteFlightToAirportById(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter airport id: ");
            Long airportId = Long.parseLong(bufferedReader.readLine());

            System.out.println("Enter flight id: ");
            Long flightId = Long.parseLong(bufferedReader.readLine());
            if (airportId != null && flightId != null) {
                airportService.deleteFlightToAirportById(airportId, flightId);
            } else {
                System.out.println("Field cannot be empty!");
                addFlightToAirportById(bufferedReader);
            }
        }catch (IOException ex) {
            System.out.println("Id does not fit the format.");
        }
    }
}
