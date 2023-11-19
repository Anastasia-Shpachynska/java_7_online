package controller;

import entity.Airport;
import entity.Flight;
import service.FlightService;
import service.impl.FlightServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

public class FlightController {

    FlightService flightService = new FlightServiceImpl();

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
        System.out.println("Create new flight - 1");
        System.out.println("Update the flight - 2");
        System.out.println("Delete the flight - 3");
        System.out.println("Find a flight and its associated airports - 4");
        System.out.println("View all flights - 5");
        System.out.println("Return to the main menu - 6");
    }

    private void select(String select, BufferedReader bufferedReader) {
        Controller controller = new Controller();
        switch (select) {
            case "1" -> create(bufferedReader);
            case "2" -> update(bufferedReader);
            case "3" -> delete(bufferedReader);
            case "4" -> findOne(bufferedReader);
            case "5" -> findAll();
            case "6" -> controller.start();
            default -> System.out.println("Incorrect option. Please, select from the proposed menu!");
        }
    }

    private void create(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the departure location: ");
            String departureLocation = bufferedReader.readLine();
            System.out.println("Enter the destination location: ");
            String destinationLocation = bufferedReader.readLine();
            System.out.println("Enter the flight price: ");
            float price = Float.parseFloat(bufferedReader.readLine());
            Flight flight = new Flight();
            flight.setDepartureLocation(departureLocation);
            flight.setDestinationLocation(destinationLocation);
            flight.setPrice(price);
            flightService.create(flight);
            System.out.println("Flight successfully added.");
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }catch (NumberFormatException ex) {
            System.out.println("Price must be a number!");
        }
    }

    private void update(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the flight ID: ");
            Long id = Long.valueOf(bufferedReader.readLine());
            Flight flight = flightService.findOne(id);
            System.out.println(flight);
            System.out.println("Enter a new departure location: ");
            String newDepartureLocation = bufferedReader.readLine();
            System.out.println("Enter a new destination location: ");
            String newDestinationLocation = bufferedReader.readLine();
            System.out.println("Enter a new price: ");
            float newPrice = Float.parseFloat(bufferedReader.readLine());
            flight.setDepartureLocation(newDepartureLocation);
            flight.setDestinationLocation(newDestinationLocation);
            flight.setPrice(newPrice);
            flightService.update(flight);
            System.out.println("The flight update!");
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }catch (NumberFormatException ex) {
            System.out.println("Price must be a number!");
        }
    }

    private void delete(BufferedReader bufferedReader) {
        try{
            System.out.println("Enter the flight ID: ");
            Long id = Long.valueOf(bufferedReader.readLine());
            Flight flight = flightService.findOne(id);
            System.out.println(flight);
            flightService.delete(id);
            System.out.println("Flight successfully deleted.");
        }catch(IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void findOne(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the flight ID: ");
            Long id = Long.valueOf(bufferedReader.readLine());
            Flight flight = flightService.findOne(id);
            System.out.println(flight);
            Set<Airport> airports = flight.getAirports();
            if(airports != null) {
                System.out.println("Airport: " + airports);
            }
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void findAll() {
        System.out.println("All flights: ");
        List<Flight> flights = flightService.findAll();
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }
}
