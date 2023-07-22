package service;

public interface DepEmpService {

    void addEmployee(String departmentId, String employeeId);
    void deleteEmployee(String employeeId);
    String[] findEmployee(String employeeId);
    String[] findDepartment(String departmentId);
    String[] allEmployees();
    String[] allDepartments();

}
