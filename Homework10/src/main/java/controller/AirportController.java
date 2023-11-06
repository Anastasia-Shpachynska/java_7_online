package controller;

import entity.Airport;
import service.impl.AirportServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class AirportController {
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    AirportServiceImpl airportServiceImpl = new AirportServiceImpl();

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

    public boolean containsOnlyLettersAndSpaces(String input) {
        return input.matches("[a-zA-Z ]+");
    }

    private void create(BufferedReader bufferedReader) {
        try {
            System.out.println("Please, enter the name of the airport: ");
            String airportName = bufferedReader.readLine();
            if (airportName.isEmpty()) {
                System.out.println("Field 'name' cannot be empty!");
            }else if(!containsOnlyLettersAndSpaces(airportName)) {
                System.out.println("The name of the airport can only contain letters!");
            }else {
                Airport airport = new Airport();
                airport.setName(airportName);
                airportServiceImpl.create(airport);
                System.out.println("Airport successfully added.");
            }
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void update(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the airport ID: ");
            String id = bufferedReader.readLine();
            if(id.isEmpty()) {
                System.out.println("ID cannot be empty!");
            }else {
                Airport airport = airportServiceImpl.findOne(id);
                if(airport == null) {
                    System.out.println("Airport not found!");
                }else {
                    System.out.println(ANSI_BLUE + "Airport: " + ANSI_RESET + airport.getName());

                    System.out.println("Enter a new name: ");
                    String newName = bufferedReader.readLine();
                    if(newName.isEmpty()) {
                        System.out.println("Field 'name' cannot be empty!");
                    }else if(!containsOnlyLettersAndSpaces(newName)) {
                        System.out.println("The name of the airport can only contain letters!");
                    }else {
                        airport.setName(newName);
                        airportServiceImpl.update(airport);
                        System.out.println("The airport update!");
                    }
                }
            }
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void delete(BufferedReader bufferedReader) {
        try{
            System.out.println("Enter the airport ID: ");
            String id = bufferedReader.readLine();
            if(id.isEmpty()) {
                System.out.println("ID cannot be empty!");
            }else {
                Airport airport = airportServiceImpl.findOne(id);
                if (airport == null) {
                    System.out.println("Airport not found!");
                } else {
                    airportServiceImpl.delete(id);
                    System.out.println("Airport successfully deleted.");
                }
            }
        }catch(IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void findOne(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the airport ID: ");
            String id = bufferedReader.readLine();
            if(id.isEmpty()) {
                System.out.println("ID cannot be empty!");
            }else {
                Airport airport = airportServiceImpl.findOne(id);
                if(airport == null) {
                    System.out.println("Airport not found!");
                }else {
                    System.out.println(ANSI_BLUE + "Id: " + ANSI_RESET + id + "\t" + ANSI_BLUE + "Name: " + ANSI_RESET + airport.getName());
                }
            }
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void findAll() {
        System.out.println("All airports: ");
        List<Airport> airports = airportServiceImpl.findAll();
        if(airports.size() == 0) {
            System.out.println("No airport added yet.");
        }
        for (Airport airport : airports) {
            System.out.println(ANSI_BLUE + "Id: " + ANSI_RESET + airport.getId() + "\t" + ANSI_BLUE + "Name: " + ANSI_RESET + airport.getName());
        }
    }
}
