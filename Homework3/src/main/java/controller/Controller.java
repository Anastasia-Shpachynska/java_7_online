package controller;

import dao.PeoplesDao;
import service.PeoplesService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    private PeoplesService peoplesService = new PeoplesService();
    private PeoplesDao peoplesDao = new PeoplesDao();
    public String firstPerson;
    public String secondPerson;
    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello!" + '\n' + "This is how the theory of six handshakes works." + '\n');
        peoplesService.getArray();
        System.out.println("Please, select the person you want to start with.");
        firstPerson = bufferedReader.readLine();
        System.out.println("Please, select the person you want to reach.");
        secondPerson = bufferedReader.readLine();
        correctValue();
        System.out.println("Who knows who.");
        peoplesService.relationship();
        path();
        menu();
        String select;
        for (select = bufferedReader.readLine(); select != null; select = bufferedReader.readLine()) {
            option(bufferedReader, select);
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want to try again please enter 1");
        System.out.println("If you want exit please enter 2");
    }

    private void option(BufferedReader bufferedReader, String select) throws IOException {
        switch (select) {
            case "1" -> start();
            case "2" -> System.exit(0);
        }
    }

    public void correctValue() throws IOException {
        boolean correctFirstName = false;
        boolean correctSecondName = false;

        for (int i = 0; i < peoplesDao.peoples.length; i++) {
            if (peoplesDao.peoples[i].equals(firstPerson)) {
                    correctFirstName = true;
            }
        }

        for (int i = 0; i < peoplesDao.peoples.length; i++) {
            if (peoplesDao.peoples[i].equals(secondPerson)) {
                correctSecondName = true;
            }
        }

        if (correctFirstName && correctSecondName) {
            if(firstPerson.equals(secondPerson)) {
                System.out.println("Invalid value. Please select different people.");
                start();
            }
        }else {
            System.out.println("You entered an incorrect value. Please select a name from the proposed list.");
            start();
        }
    }

    public void path() {
        peoplesService.path(firstPerson, secondPerson);
    }
}
