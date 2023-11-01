package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    AirportController airportController = new AirportController();
    FlightController flightController = new FlightController();
    AirportFlightController airportFlightController = new AirportFlightController();

    public void start() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            menu();
            String select;
            for (select = bufferedReader.readLine(); select != null; select = bufferedReader.readLine()) {
                select(select);
            }
        }catch (IOException ex) {
            System.out.println("Oops... something went wrong, try again." + ex.getMessage());
        }
    }

    private void menu() {
        System.out.println("\n" + "Please, select an option: " + "\n");
        System.out.println("Go to the 'Airport' menu - 1");
        System.out.println("Go to the 'Flight' menu - 2");
        System.out.println("Go to the 'Relationship between airport and flight' menu - 3");
        System.out.println("Close application - 4");
    }

    private void select(String select) {
        switch (select) {
            case "1" -> airportController.start();
            case "2" -> flightController.start();
            case "3" -> airportFlightController.start();
            case "4" -> System.exit(0);
            default -> System.out.println("There is no such option in the menu. Please select something from the proposed menu.");
        }
    }
}
