package service;

import entity.Employees;

public interface EmployeesService {
    void createEmployee(Employees employee);
    void updateEmployee(Employees employee);
    void deleteEmployee(String id);
    Employees findOneEmployee(String id);
    Employees[] findAllEmployees();
}
