package controller;

import entity.Departments;
import service.DepartmentsServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DepartmentController {

    private DepartmentsServiceImpl departmentsServiceImpl = new DepartmentsServiceImpl();

    public void startDepartment() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        menuDepartments();
        String select;
        for (select = bufferedReader.readLine(); select != null; select = bufferedReader.readLine()) {
            crudDepartments(bufferedReader, select);
        }
    }

    private void menuDepartments() {
        System.out.println();
        System.out.println("If you want create department, please enter 1");
        System.out.println("If you want update department, please enter 2");
        System.out.println("If you want delete department, please enter 3");
        System.out.println("If you want find department, please enter 4");
        System.out.println("If you want to view all departments, please enter 5");
        System.out.println("If you want return to the main menu, please enter 6");
    }

    private void crudDepartments(BufferedReader bufferedReader, String select) throws IOException {
        Controller controller = new Controller();
        switch (select) {
            case "1" -> createDepartment(bufferedReader);
            case "2" -> updateDepartment(bufferedReader);
            case "3" -> deleteDepartment(bufferedReader);
            case "4" -> findOneDepartment(bufferedReader);
            case "5" -> findAllDepartments();
            case "6" -> controller.start();
            default -> System.out.println("There is no such option.");
        }
        startDepartment();
    }

    private void createDepartment(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please, enter name");
        String name = bufferedReader.readLine();
        Departments department = new Departments();
        department.setName(name);
        departmentsServiceImpl.createDepartment(department);
        System.out.println("Department successfully created.");
    }

    private void updateDepartment(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please, enter id department");
        String id = bufferedReader.readLine();
        Departments department = departmentsServiceImpl.findOneDepartment(id);
        if (department != null) {
            System.out.println("Name: " + department.getName() + '\n');
        }else {
            System.out.println("Department not found.");
            startDepartment();
        }
        System.out.println("Please, enter new name");
        String newName = bufferedReader.readLine();
        department.setName(newName);
        System.out.println("Information has been successfully updated");
    }

    private void deleteDepartment(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter id department");
        String id = bufferedReader.readLine();
        Departments department = departmentsServiceImpl.findOneDepartment(id);
        if (department != null) {
            System.out.println("Name: " + department.getName() + '\n');
        } else {
            System.out.println("Department not found.");
            startDepartment();
        }
        System.out.println("Really delete the department?");
        System.out.println("Yes. - Press 1");
        System.out.println("No. - Press 2");
        String delete = bufferedReader.readLine();
        switch (delete) {
            case "1" -> {
                departmentsServiceImpl.deleteDepartment(id);
                System.out.println("Department successfully deleted.");
            }
            case "2" -> System.out.println("All departments remained in place.");
            default -> System.out.println("Something went wrong. Please try again.");
        }
    }

    private void findOneDepartment(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter id department");
        String id = bufferedReader.readLine();
        Departments department = departmentsServiceImpl.findOneDepartment(id);
        if (department != null) {
            System.out.println("Name: " + department.getName() + '\n');
        } else {
            System.out.println("Id incorrect. Please, try again.");
            startDepartment();
        }
    }

    private void findAllDepartments() {
        Departments[] departments = departmentsServiceImpl.findAllDepartments();
        for (int i = 0; i < departments.length; i++) {
            Departments department = departments[i];
            if (department != null) {
                System.out.println("Id: " + department.getId());
                System.out.println("Name: " + department.getName() + '\n');
            }
        }
    }
}
