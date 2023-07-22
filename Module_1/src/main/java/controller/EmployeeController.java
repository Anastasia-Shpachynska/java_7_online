package controller;

import entity.Employees;
import service.EmployeesServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EmployeeController {

    private EmployeesServiceImpl employeesServiceImpl = new EmployeesServiceImpl();

    public void startEmployee() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        menuEmployees();
        String select;
        for (select = bufferedReader.readLine(); select != null; select = bufferedReader.readLine()) {
            crudEmployees(bufferedReader, select);
        }
    }

    private void menuEmployees() {
        System.out.println();
        System.out.println("If you want create employee, please enter 1");
        System.out.println("If you want update employee, please enter 2");
        System.out.println("If you want delete employee, please enter 3");
        System.out.println("If you want find employee, please enter 4");
        System.out.println("If you want to view all employees, please enter 5");
        System.out.println("If you want return to the main menu, please enter 6");
    }

    private void crudEmployees(BufferedReader bufferedReader, String select2) throws IOException {
        Controller controller = new Controller();
        switch (select2) {
            case "1" -> createEmployee(bufferedReader);
            case "2" -> updateEmployee(bufferedReader);
            case "3" -> deleteEmployee(bufferedReader);
            case "4" -> findOneEmployee(bufferedReader);
            case "5" -> findAllEmployees();
            case "6" -> controller.start();
            default -> System.out.println("There is no such option.");
        }
        startEmployee();
    }

    private void createEmployee(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please, enter first name.");
        String firstName = bufferedReader.readLine();
        System.out.println("Please, enter last name.");
        String lastName = bufferedReader.readLine();
        System.out.println("Please, enter age.");
        String ageString = bufferedReader.readLine();
        int age = Integer.parseInt(ageString);
        Employees employee = new Employees();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setAge(age);
        employeesServiceImpl.createEmployee(employee);
        System.out.println("Employee successfully created.");
    }

    private void updateEmployee(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please, enter id employee");
        String id = bufferedReader.readLine();
        Employees employee = employeesServiceImpl.findOneEmployee(id);
        if (employee != null) {
            System.out.println("First name: " + employee.getFirstName());
            System.out.println("Last name: " + employee.getLastName());
            System.out.println("Age: " + employee.getAge() + '\n');
        }else {
            System.out.println("Employee not found");
            startEmployee();
        }
        System.out.println("If you want update firs name, please enter 1");
        System.out.println("If you want update last name, please enter 2");
        System.out.println("If you want update age, please enter 3");
        System.out.println("If you want update all information about the employee, please enter 4");
        String update = bufferedReader.readLine();
        switch (update) {
            case "1" -> {
                System.out.println("Please enter a new first name");
                String newFirstName = bufferedReader.readLine();
                employee.setFirstName(newFirstName);
                System.out.println("First name has been successfully updated");
            }
            case "2" -> {
                System.out.println("Please enter a new last name");
                String newLastName = bufferedReader.readLine();
                employee.setLastName(newLastName);
                System.out.println("Last name has been successfully updated");
            }
            case "3" -> {
                System.out.println("Please enter a new age");
                String newAge = bufferedReader.readLine();
                employee.setAge(Integer.parseInt(newAge));
                System.out.println("Age has been successfully updated");
            }
            case "4" -> {
                System.out.println("Please enter a new first name");
                String newFirstName = bufferedReader.readLine();
                employee.setFirstName(newFirstName);
                System.out.println("Please enter a new last name");
                String newLastName = bufferedReader.readLine();
                employee.setLastName(newLastName);
                System.out.println("Please enter a new age");
                String newAge = bufferedReader.readLine();
                employee.setAge(Integer.parseInt(newAge));
                System.out.println("Information has been successfully updated");
            }
            default -> System.out.println("An error occurred. Please try again.");
        }
    }

    private void deleteEmployee(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter id employee");
        String id = bufferedReader.readLine();
        Employees employee = employeesServiceImpl.findOneEmployee(id);
        if (employee != null) {
            System.out.println("First name: " + employee.getFirstName());
            System.out.println("Last name: " + employee.getLastName());
            System.out.println("Age: " + employee.getAge() + '\n');
        } else {
            System.out.println("Employee not found");
            startEmployee();
        }
        System.out.println("Really delete the employee?");
        System.out.println("Yes. - Press 1");
        System.out.println("No. - Press 2");
        String delete = bufferedReader.readLine();
        switch (delete) {
            case "1" -> {
                employeesServiceImpl.deleteEmployee(id);
                System.out.println("Employee successfully deleted.");
            }
            case "2" -> System.out.println("All employees remained in place.");
            default -> System.out.println("Something went wrong. Please try again.");
        }
    }

    private void findOneEmployee(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter id employee");
        String id = bufferedReader.readLine();
        Employees employee = employeesServiceImpl.findOneEmployee(id);
        if (employee != null) {
            System.out.println("First name: " + employee.getFirstName());
            System.out.println("Last name: " + employee.getLastName());
            System.out.println("Age: " + employee.getAge() + '\n');
        } else {
            System.out.println("Id incorrect. Please, try again.");
            startEmployee();
        }
    }

    private void findAllEmployees() {
        Employees[] employees = employeesServiceImpl.findAllEmployees();
        for (int i = 0; i < employees.length; i++) {
            Employees employee = employees[i];
            if (employee != null) {
                System.out.println("Id: " + employee.getId());
                System.out.println("First name: " + employee.getFirstName());
                System.out.println("Last name: " + employee.getLastName());
                System.out.println("Age: " + employee.getAge() + '\n');
            }
        }
    }
}
