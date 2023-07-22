package db;

import entity.Employees;
import utill.AppUtill;

public class EmployeesDB {

    private static EmployeesDB instance;

    private EmployeesDB() {}

    public static EmployeesDB getInstance() {
        if (instance == null) {
            instance = new EmployeesDB();
        }
        return instance;
    }

    public Employees[] employees = new Employees[10];
    private int size = 0;

    public void expandArray() {
        Employees[] newEmployee = new Employees[employees.length * 2];
        System.arraycopy(employees, 0, newEmployee, 0, employees.length);
        employees = newEmployee;
    }

    public void create(Employees employee) {
        if (size == employees.length) {
            expandArray();
        }
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                String id = AppUtill.getUUID();
                employee.setId(id);
                employees[i] = employee;
                size++;
                break;
            }
        }
    }

    public  void update(Employees employee) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getId().equals(employee.getId())) {
                employees[i] = employee;
            }
        }
    }

    public void delete(String id) {
        boolean left = false;
        for (int i = 0; i < employees.length; i++) {
            try {
                if (employees[i].getId().equals(id)) {
                    employees[i] = null;
                    left = true;
                    break;
                }
                if (left) {
                    employees[i] = employees[i + 1];
                }
            }catch (Exception e) {
            }
        }
        size--;
    }

    public Employees findOne(String id) {
        for (Employees employee : employees) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        return null;
    }

    public Employees[] findAll() {
        return employees;
    }
}
