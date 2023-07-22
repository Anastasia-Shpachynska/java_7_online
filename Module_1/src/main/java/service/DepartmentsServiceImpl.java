package service;

import dao.DepartmentsDao;
import entity.Departments;

public class DepartmentsServiceImpl implements DepartmentsService {

    private DepartmentsDao departmentsDao = new DepartmentsDao();

    @Override
    public void createDepartment(Departments department) {
        departmentsDao.createDepartment(department);
    }

    @Override
    public void updateDepartment(Departments department) {
        departmentsDao.updateDepartment(department);
    }

    @Override
    public void deleteDepartment(String id) {
        departmentsDao.deleteDepartment(id);
    }

    @Override
    public Departments findOneDepartment(String id) {
       return departmentsDao.findOneDepartment(id);
    }

    @Override
    public Departments[] findAllDepartments() {
        return departmentsDao.findAllDepartments();
    }
}
