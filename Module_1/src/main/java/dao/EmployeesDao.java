package dao;

import db.EmployeesDB;
import entity.Employees;

public class EmployeesDao {

    EmployeesDB empDB = EmployeesDB.getInstance();

    public void createEmployee(Employees employee) {
        empDB.create(employee);
    }

    public void updateEmployee(Employees employee) {
        empDB.update(employee);
    }

    public void deleteEmployee(String id) {
        empDB.delete(id);
    }

    public Employees findOneEmployee(String id) {return empDB.findOne(id);}

    public Employees[] findAllEmployees() {
        return empDB.findAll();
    }
}
