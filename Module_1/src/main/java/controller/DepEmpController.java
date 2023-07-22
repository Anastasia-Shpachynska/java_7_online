package controller;

import entity.DepEmp;
import entity.Departments;
import entity.Employees;
import service.DepEmpServiceImpl;
import service.DepartmentsServiceImpl;
import service.EmployeesServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DepEmpController {

    private DepartmentsServiceImpl departmentsServiceImp = new DepartmentsServiceImpl();
    private EmployeesServiceImpl employeesServiceImpl = new EmployeesServiceImpl();
    private DepEmpServiceImpl depEmpServiceImpl = new DepEmpServiceImpl();

    public void startDepEmp() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        menuDepEmp();
        String select;
        for (select = bufferedReader.readLine(); select != null; select = bufferedReader.readLine()) {
            crudDepEmp(bufferedReader, select);
        }
    }

    private void menuDepEmp() {
        System.out.println();
        System.out.println("If you want to add an employee to a department, please enter 1");
        System.out.println("If you want delete an employee from a department, please enter 2");
        System.out.println("If you want to find an employee and him departments, please enter 3");
        System.out.println("If you want to find a department and its employees, please enter 4");
        System.out.println("If you want to view all employees (unique) who are relationship to departments, please enter 5");
        System.out.println("If you want to view all departments (unique) in which there are employees, please enter 6");
        System.out.println("If you want return to the main menu, please enter 7");
    }

    private void crudDepEmp(BufferedReader bufferedReader, String select) throws IOException {
        Controller controller = new Controller();
        switch (select) {
            case "1" -> addEmployee(bufferedReader);
            case "2" -> deleteEmployee(bufferedReader);
            case "3" -> findEmployee(bufferedReader);
            case "4" -> findDepartment(bufferedReader);
            case "5" -> findAllEmployees();
            case "6" -> findAllDepartments();
            case "7" -> controller.start();
        }
        startDepEmp();
    }

    private void addEmployee(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please, enter id department.");
        String id = bufferedReader.readLine();
        Departments department = departmentsServiceImp.findOneDepartment(id);
        if (department != null) {
            System.out.println("Name: " + department.getName() + '\n');
        }else {
            System.out.println("Department not found." + '\n');
            startDepEmp();
        }
        System.out.println("Please, enter id employee.");
        String id1 = bufferedReader.readLine();
        Employees employee = employeesServiceImpl.findOneEmployee(id1);
        if (employee == null) {
            System.out.println("Employee not found." + '\n');
            startDepEmp();
        }
        DepEmp depEmp = new DepEmp();
        depEmp.setDepartmentId(id);
        depEmp.setEmployeeId(id1);
        depEmpServiceImpl.addEmployee(id, id1);
        System.out.println("Employee has been successfully add.");
    }

    private void deleteEmployee(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter id employee.");
        String id = bufferedReader.readLine();
        Employees employee = employeesServiceImpl.findOneEmployee(id);
        if (employee != null) {
            System.out.println("First name: " + employee.getFirstName() + '\t' + "Last name: " + employee.getLastName() + '\n');
        }else {
            System.out.println("Employee not found." + '\n');
            startDepEmp();
        }
        depEmpServiceImpl.deleteEmployee(id);
        System.out.println("Employee has been successfully delete from the relationship.");
    }

    private void findEmployee(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter id employee.");
        String id = bufferedReader.readLine();
        Employees employee = employeesServiceImpl.findOneEmployee(id);
        if (employee == null) {
            System.out.println("Employee not found." + '\n');
            startDepEmp();
        }
        System.out.println("The " + employee.getId() + " has relationship with:");
        String[] findEmployee = depEmpServiceImpl.findEmployee(id);
        for (int i = 0; i < findEmployee.length; i++) {
            if (findEmployee[i] != null) {
                Departments departments = departmentsServiceImp.findOneDepartment(findEmployee[i]);
                System.out.println("Id: " + departments.getId() + '\t' + "Name: " + departments.getName());
            }else {
                System.out.println("No more connections found.");
                startDepEmp();
            }
        }
    }

    private void findDepartment(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter id department.");
        String id = bufferedReader.readLine();
        Departments department = departmentsServiceImp.findOneDepartment(id);
        if (department == null) {
            System.out.println("Department not found." + '\n');
            startDepEmp();
        }
        System.out.println("The " + department.getId() + " has relationship with:");
        String[] findDepartment = depEmpServiceImpl.findDepartment(id);
        for (int i = 0; i < findDepartment.length; i++) {
            if (findDepartment[i] != null) {
                Employees employees = employeesServiceImpl.findOneEmployee(findDepartment[i]);
                System.out.println("Id: " + employees.getId() + '\t' + "First name: " + employees.getFirstName() + '\t' +
                        "Last name: " + employees.getLastName() + '\t' + "Age: " + employees.getAge());
            }else {
                System.out.println("No more connections found.");
                startDepEmp();
            }
        }
    }

    private void findAllEmployees() throws IOException {
        String[] employees = depEmpServiceImpl.allEmployees();
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                Employees employee = employeesServiceImpl.findOneEmployee(employees[i]);
                System.out.println("Id: " + employee.getId() + '\t' + "First name: " + employee.getFirstName() + '\t' +
                        "Last name: " + employee.getLastName() + '\t' + "Age: " + employee.getAge());
            }else {
                System.out.println("No more connections found.");
                startDepEmp();
            }
        }
    }

    private void findAllDepartments() throws IOException {
        String[] departments = depEmpServiceImpl.allDepartments();
        for (int i = 0; i < departments.length; i++) {
            if (departments[i] != null) {
                Departments department = departmentsServiceImp.findOneDepartment(departments[i]);
                System.out.println("Id: " + department.getId() + '\t' + "Name: " + department.getName());
            }else {
                System.out.println("No more connections found.");
                startDepEmp();
            }
        }
    }
}
