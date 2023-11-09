package controller;

import entity.Flight;
import service.impl.FlightServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FlightController {
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    FlightServiceImpl flightServiceImpl = new FlightServiceImpl();

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
        System.out.println("Find the flight - 4");
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

    public boolean containsOnlyLettersAndSpaces(String input) {
        return input.matches("[a-zA-Z ]+");
    }

    private void create(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the departure location: ");
            String departureLocation = bufferedReader.readLine();
            System.out.println("Enter the destination location: ");
            String destinationLocation = bufferedReader.readLine();
            System.out.println("Enter the flight price: ");
            float price = Float.parseFloat(bufferedReader.readLine());
            if(departureLocation.isEmpty() || destinationLocation.isEmpty()) {
                System.out.println("Fields must be filled!");
            }else if(!containsOnlyLettersAndSpaces(departureLocation) || !containsOnlyLettersAndSpaces(destinationLocation)) {
                System.out.println("The departure location and destination location of the flight can only contain letters!");
            }else {
                Flight flight = new Flight();
                flight.setDepartureLocation(departureLocation);
                flight.setDestinationLocation(destinationLocation);
                flight.setPrice(price);
                flightServiceImpl.create(flight);
                System.out.println("Flight successfully added.");
            }
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }catch (NumberFormatException ex) {
            System.out.println("Price must be a number!");
        }
    }

    private void update(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the flight ID: ");
            String id = bufferedReader.readLine();
            if(id.isEmpty()) {
                System.out.println("ID cannot be empty!");
            }else {
                Flight flight = flightServiceImpl.findOne(id);
                if(flight == null) {
                    System.out.println("Airport not found!");
                }else {
                    System.out.println(ANSI_BLUE + "Flight: " + "Departure Location: " + ANSI_RESET + flight.getDepartureLocation() + "\t"
                            + ANSI_BLUE + " Destination Location: " + ANSI_RESET + flight.getDestinationLocation() + "\t" + ANSI_BLUE + "Price: " + ANSI_RESET + flight.getPrice());

                    System.out.println("Enter a new departure location: ");
                    String newDepartureLocation = bufferedReader.readLine();
                    System.out.println("Enter a new destination location: ");
                    String newDestinationLocation = bufferedReader.readLine();
                    System.out.println("Enter a new price: ");
                    float newPrice = Float.parseFloat(bufferedReader.readLine());

                    if(newDepartureLocation.isEmpty() || newDestinationLocation.isEmpty()) {
                        System.out.println("Fields must be filled!");
                    }else if(!containsOnlyLettersAndSpaces(newDepartureLocation) || !containsOnlyLettersAndSpaces(newDestinationLocation)) {
                        System.out.println("The departure location and destination location of the flight can only contain letters!");
                    }else {
                        flight.setDepartureLocation(newDepartureLocation);
                        flight.setDestinationLocation(newDestinationLocation);
                        flight.setPrice(newPrice);
                        flightServiceImpl.update(flight);
                        System.out.println("The flight update!");
                    }
                }
            }
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }catch (NumberFormatException ex) {
            System.out.println("Price must be a number!");
        }
    }

    private void delete(BufferedReader bufferedReader) {
        try{
            System.out.println("Enter the flight ID: ");
            String id = bufferedReader.readLine();
            if(id.isEmpty()) {
                System.out.println("ID cannot be empty!");
            }else {
                Flight flight = flightServiceImpl.findOne(id);
                if (flight == null) {
                    System.out.println("Airport not found!");
                } else {
                    flightServiceImpl.delete(id);
                    System.out.println("Flight successfully deleted.");
                }
            }
        }catch(IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void findOne(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the flight ID: ");
            String id = bufferedReader.readLine();
            if(id.isEmpty()) {
                System.out.println("ID cannot be empty!");
            }else {
                Flight flight = flightServiceImpl.findOne(id);
                if(flight == null) {
                    System.out.println("Flight not found!");
                }else {
                    System.out.println(ANSI_BLUE + "Id: " +  ANSI_RESET + flight.getId() + "\t" + ANSI_BLUE + "Departure Location: " + ANSI_RESET + flight.getDepartureLocation() + "\t"
                            + ANSI_BLUE + " Destination Location: " + ANSI_RESET + flight.getDestinationLocation() + "\t" + ANSI_BLUE + "Price: " + ANSI_RESET + flight.getPrice());
                }
            }
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void findAll() {
        System.out.println("All flights: ");
        List<Flight> flights = flightServiceImpl.findAll();
        if(flights.size() == 0) {
            System.out.println("No flight added yet.");
        }
        for (Flight flight : flights) {
            System.out.println( ANSI_BLUE + "Id: " +  ANSI_RESET + flight.getId() + "\t" + ANSI_BLUE + "Departure Location: " + ANSI_RESET + flight.getDepartureLocation() + "\t"
                    + ANSI_BLUE + " Destination Location: " + ANSI_RESET + flight.getDestinationLocation() + "\t" + ANSI_BLUE + "Price: " + ANSI_RESET + flight.getPrice());
        }
    }
}
