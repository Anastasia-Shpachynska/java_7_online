package dao;

import db.DepEmpDB;

public class DepEmpDao {

    DepEmpDB depEmpDB = DepEmpDB.getInstance();

    public void addEmployee(String departmentId, String employeeId) {
        depEmpDB.addEmployee(departmentId, employeeId);
    }

    public void deleteEmployee(String employeeId) {
        depEmpDB.deleteEmployee(employeeId);
    }

    public String[] findEmployee(String employeeId) {
        return depEmpDB.findEmployee(employeeId);
    }

    public String[] findDepartment(String departmentId) {
        return depEmpDB.findDepartment(departmentId);
    }

    public String[] allEmployees() {
        return depEmpDB.allEmployees();
    }

    public String[] allDepartments() {
        return depEmpDB.allDepartments();
    }
}
