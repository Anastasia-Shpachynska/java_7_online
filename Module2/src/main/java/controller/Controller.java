package controller;

import service.Impl.GraphServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    GraphServiceImpl graphServiceImpl = new GraphServiceImpl();
    public static String fileName = "src/main/java/filesDB/input.txt";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

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
        System.out.println();
        System.out.println("Edit/view file contents - 1");
        System.out.println("Find the shortest paths - 2");
        System.out.println("Close the application - 3");
    }

    private void select(String select) {
        switch (select) {
            case "1" -> editView();
            case "2" -> findPath();
            case "3" -> System.exit(0);
            default -> System.out.println("There is no such option in the menu. Please select something from the proposed menu.");
        }
    }

    private void editView() {
        System.out.println( ANSI_GREEN + "For the correct operation of the program, be sure to save all changes after editing the file." + ANSI_RESET);
        graphServiceImpl.editView(fileName);
    }

    private void findPath() {
        graphServiceImpl.findPath(fileName);
    }
}
