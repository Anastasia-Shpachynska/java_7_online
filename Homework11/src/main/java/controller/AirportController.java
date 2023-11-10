package controller;

import entity.Airport;
import service.AirportService;
import service.impl.AirportServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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
        System.out.println("Find the airport - 4");
        System.out.println("View all airports - 5");
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
}
