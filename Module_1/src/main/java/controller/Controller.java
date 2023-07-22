package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    private DepartmentController departmentController = new DepartmentController();
    private EmployeeController employeeController = new EmployeeController();
    private DepEmpController depEmpController = new DepEmpController();

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello!");
        System.out.println("Please select your option:");
        menu();
        String select;
        for (select = bufferedReader.readLine(); select != null; select = bufferedReader.readLine()) {
            crud(bufferedReader, select);
        }
    }
    private void menu() {
        System.out.println();
        System.out.println("If you want work with departments, please enter 1");
        System.out.println("If you want work with employees, please enter 2");
        System.out.println("If you want work with relationship between employees and departments, please enter 3");
        System.out.println("If you want close application, please enter 4");
    }

    private void crud(BufferedReader bufferedReader, String select) throws IOException {
        switch (select) {
            case "1" -> departmentController.startDepartment();
            case "2" -> employeeController.startEmployee();
            case "3" -> depEmpController.startDepEmp();
            case "4" -> System.exit(0);
            default -> System.out.println("There is no such option.");
        }
        start();
    }
}
