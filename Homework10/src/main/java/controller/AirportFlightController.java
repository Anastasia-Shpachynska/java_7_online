package controller;

import entity.Airport;
import entity.AirportFlight;
import entity.Flight;
import service.impl.AirportFlightServiceImpl;
import service.impl.AirportServiceImpl;
import service.impl.FlightServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class AirportFlightController {
    AirportServiceImpl airportServiceImpl = new AirportServiceImpl();
    FlightServiceImpl flightServiceImpl = new FlightServiceImpl();
    AirportFlightServiceImpl airportFlightServiceImpl = new AirportFlightServiceImpl();

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

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
            case "1" -> add(bufferedReader);
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

    private void add(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the ID of the first airport: ");
            String idFirst = bufferedReader.readLine();
            System.out.println("Enter the ID of the second airport: ");
            String idSecond = bufferedReader.readLine();
            System.out.println("Enter the ID of the flight: ");
            String idFlight = bufferedReader.readLine();
            if(idFirst.isEmpty() || idSecond.isEmpty() || idFlight.isEmpty()) {
                System.out.println("ID cannot be empty!");
            }else if(idFirst.equals(idSecond)) {
                System.out.println("ID cannot be the same!");
            }else {
                Airport firstId = airportServiceImpl.findOne(idFirst);
                Airport secondId = airportServiceImpl.findOne(idSecond);
                Flight flightId = flightServiceImpl.findOne(idFlight);

                if(firstId == null || secondId == null || flightId == null) {
                    System.out.println("Not found. Сheck that all ID are correct!");
                }else {
                    AirportFlight airportFlight = new AirportFlight();
                    airportFlight.setFirstAirportId(idFirst);
                    airportFlight.setSecondAirportId(idSecond);
                    airportFlight.setFlightId(idFlight);

                    boolean result = airportFlightServiceImpl.add(airportFlight);

                    if(!result) {
                        System.out.println("Id the flight cannot be repeated!");
                    }else {
                        System.out.println("The relationship is established.");
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void update(BufferedReader bufferedReader) {
        try{
            System.out.println("Enter the ID of the flight: ");
            String id = bufferedReader.readLine();
            if(id.isEmpty()) {
                System.out.println("ID cannot be empty!");
            }else {
                Flight flight = airportFlightServiceImpl.findFlight(id);
                if(flight == null){
                    System.out.println("The flight not found.");
                }else {
                    Airport airport = airportServiceImpl.findOne(flight.getDepartureLocation());
                    Airport airport1 = airportServiceImpl.findOne(flight.getDestinationLocation());

                    System.out.println(ANSI_BLUE + "Flight: " + ANSI_RESET + flight.getId() + "\t" + flight.getDepartureLocation() + "\t" + flight.getDestinationLocation() + "\t" + flight.getPrice());
                    System.out.println(ANSI_BLUE + "First airport: " + ANSI_RESET + airport.getId() + "\t" + airport.getName());
                    System.out.println(ANSI_BLUE + "Second airport: " + ANSI_RESET + airport1.getId() + "\t" + airport1.getName() + "\n");

                    System.out.println("Enter a new ID of the first airport: ");
                    String newFirstAirport = bufferedReader.readLine();
                    System.out.println("Enter a new ID of the second airport: ");
                    String newSecondAirport = bufferedReader.readLine();
                    if(newFirstAirport.isEmpty() || newSecondAirport.isEmpty() || newFirstAirport.equals(newSecondAirport)) {
                        System.out.println("ID are empty or the same! Try again.");
                    }else {
                        AirportFlight airportFlight = new AirportFlight();
                        airportFlight.setFlightId(flight.getId());
                        airportFlight.setFirstAirportId(newFirstAirport);
                        airportFlight.setSecondAirportId(newSecondAirport);
                        airportFlightServiceImpl.update(airportFlight);
                        System.out.println("Successfully updated");
                    }
                }
            }
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void deleteFlight(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the ID of the flight:");
            String id = bufferedReader.readLine();
            if(id.isEmpty()) {
                System.out.println("ID cannot be empty!");
            }else {
                boolean result = airportFlightServiceImpl.deleteFlight(id);
                if(!result) {
                    System.out.println("The flight not found!");
                }else {
                    System.out.println("Successfully deleted.");
                }
            }
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void deleteAirport(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the ID of the airport:");
            String id = bufferedReader.readLine();
            if(id.isEmpty()) {
                System.out.println("ID cannot be empty!");
            }else {
                boolean result = airportFlightServiceImpl.deleteAirport(id);
                if(!result) {
                    System.out.println("The airport not found!");
                }else {
                    System.out.println("Successfully deleted.");
                }
            }
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void findAirport(BufferedReader bufferedReader) {
        try{
            System.out.println("Enter the ID of the airport: ");
            String id = bufferedReader.readLine();
            if(id.isEmpty()) {
                System.out.println("ID cannot be empty!");
            }else {
                Airport airport = airportServiceImpl.findOne(id);
                if(airport == null) {
                    System.out.println("The airport not found.");
                }else{
                    List<String> airportFlight = airportFlightServiceImpl.findAirport(id);
                    System.out.println(ANSI_BLUE + "The " + ANSI_RESET + id + ANSI_BLUE + " has relationship with: " + ANSI_RESET);
                    for (String s : airportFlight) {
                        Flight flight = flightServiceImpl.findOne(s);
                        System.out.println(flight.getId() + "\t" + flight.getDepartureLocation() + "\t" + flight.getDestinationLocation() + "\t" + flight.getPrice());
                    }
                }
            }
        }catch(IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void findFlight(BufferedReader bufferedReader) {
        try{
            System.out.println("Enter the ID of the flight: ");
            String id = bufferedReader.readLine();
            if(id.isEmpty()) {
                System.out.println("ID cannot be empty!");
            }else {
                Flight flight = airportFlightServiceImpl.findFlight(id);
                if(flight == null) {
                    System.out.println("The flight not found.");
                }else{
                    Airport airportFirst = airportServiceImpl.findOne(flight.getDepartureLocation());
                    Airport airportSecond = airportServiceImpl.findOne(flight.getDestinationLocation());

                    System.out.println(ANSI_BLUE + "The " + ANSI_RESET + flight.getId() + ANSI_BLUE + " has relationship with: " + ANSI_RESET);
                    System.out.println(airportFirst.getId() + "\t" + airportFirst.getName());
                    System.out.println(airportSecond.getId() + "\t" + airportSecond.getName());
                }
            }
        }catch(IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void findAllAirports() {
        System.out.println("All unique airports: ");
        List<Airport> airports = airportFlightServiceImpl.findAllAirports();
        if(airports.size() == 0) {
            System.out.println("No airport added yet.");
        }
        for (Airport airport : airports) {
            System.out.println(ANSI_BLUE + "Id: " + ANSI_RESET + airport.getId() + "\t" + ANSI_BLUE + "Name: " + ANSI_RESET + airport.getName());
        }
    }

    private void findAllFlights() {
        System.out.println("All unique flights: ");
        List<Flight> flights = airportFlightServiceImpl.findAllFlights();
        if(flights.size() == 0) {
            System.out.println("No airport added yet.");
        }
        for (Flight flight : flights) {
            System.out.println( ANSI_BLUE + "Id: " +  ANSI_RESET + flight.getId() + "\t" + ANSI_BLUE + "Departure Location: " + ANSI_RESET + flight.getDepartureLocation() + "\t"
                    + ANSI_BLUE + " Destination Location: " + ANSI_RESET + flight.getDestinationLocation() + "\t" + ANSI_BLUE + "Price: " + ANSI_RESET + flight.getPrice());
        }
    }
}
