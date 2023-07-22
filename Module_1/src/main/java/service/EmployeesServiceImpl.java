package service;

import dao.EmployeesDao;
import entity.Employees;

public class EmployeesServiceImpl implements EmployeesService {

    private EmployeesDao employeesDao = new EmployeesDao();

    @Override
    public void createEmployee(Employees employee) {
        if (employee.getAge() < 18 || employee.getAge() > 70) {
            System.out.println("\u001B[31m" + "Incorrect age" + "\u001B[0m");
        }
        employeesDao.createEmployee(employee);
    }

    @Override
    public void updateEmployee(Employees employee) {
        if (employee.getAge() < 18 || employee.getAge() > 70) {
            System.out.println("\u001B[31m" + "Incorrect age" + "\u001B[0m");
        }
        employeesDao.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(String id) {
        employeesDao.deleteEmployee(id);
    }

    @Override
    public Employees findOneEmployee(String id) {
       return employeesDao.findOneEmployee(id);
    }

    @Override
    public Employees[] findAllEmployees() {
        return employeesDao.findAllEmployees();
    }
}
