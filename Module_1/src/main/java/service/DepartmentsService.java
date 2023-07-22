package service;

import entity.Departments;

public interface DepartmentsService {
    void createDepartment(Departments department);
    void updateDepartment(Departments department);
    void deleteDepartment(String id);
    Departments findOneDepartment(String id);
    Departments[] findAllDepartments();
}
