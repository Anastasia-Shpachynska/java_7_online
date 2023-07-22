package service;

import dao.DepEmpDao;

public class DepEmpServiceImpl implements DepEmpService {

    private DepEmpDao depEmpDao = new DepEmpDao();

    @Override
    public void addEmployee(String departmentId, String employeeId) {
        depEmpDao.addEmployee(departmentId, employeeId);
    }

    @Override
    public void deleteEmployee(String employeeId) {
        depEmpDao.deleteEmployee(employeeId);
    }

    @Override
    public String[] findEmployee(String employeeId) {
       return depEmpDao.findEmployee(employeeId);
    }

    @Override
    public String[] findDepartment(String departmentId) {
        return depEmpDao.findDepartment(departmentId);
    }

    @Override
    public String[] allEmployees() {
        return depEmpDao.allEmployees();
    }

    @Override
    public String[] allDepartments() {
        return depEmpDao.allDepartments();
    }

}
