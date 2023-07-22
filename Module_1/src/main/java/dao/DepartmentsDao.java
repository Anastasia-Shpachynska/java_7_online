package dao;

import db.DepartmentsDB;
import entity.Departments;

public class DepartmentsDao {
    DepartmentsDB depDB = DepartmentsDB.getInstance();


    public void createDepartment(Departments department) {
        depDB.create(department);
    }

    public void updateDepartment(Departments department) {
        depDB.update(department);
    }

    public void deleteDepartment(String id) {
        depDB.delete(id);
    }

    public Departments findOneDepartment(String id) {
        return depDB.findOne(id);
    }

    public Departments[] findAllDepartments() {
        return depDB.findAll();
    }
}
