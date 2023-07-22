package db;

import entity.Departments;
import utill.AppUtill;

public class DepartmentsDB {

    private static DepartmentsDB instance;

    private DepartmentsDB() {}

    public static DepartmentsDB getInstance() {
        if (instance == null) {
            instance = new DepartmentsDB();
        }
        return instance;
    }

    public Departments[] departments = new Departments[10];
    private int size = 0;

    public void expandArray() {
        Departments[] newDepartment = new Departments[departments.length * 2];
        System.arraycopy(departments, 0, newDepartment, 0, departments.length);
        departments = newDepartment;
    }

    public void create(Departments department) {
        if (size == departments.length) {
            expandArray();
        }
        for (int i = 0; i < departments.length; i++) {
            if (departments[i] == null) {
                String id = AppUtill.getUUID();
                department.setId(id);
                departments[i] = department;
                size++;
                break;
            }
        }
    }

    public void update(Departments department) {
        for (int i = 0; i < departments.length; i++) {
            if (departments[i].getId().equals(department.getId())) {
                departments[i] = department;
            }
        }
    }

    public void delete(String id) {
        Departments[] newDepartments = new Departments[departments.length];
        int newIndex = 0;
        DepEmpDB depEmpDB = DepEmpDB.getInstance();
        EmployeesDB empDB = EmployeesDB.getInstance();
        for (int i = 0; i < departments.length; i++) {
            try {
                if (departments[i] != null) {
                    if (departments[i].getId().equals(id)) {
                        String[] department = depEmpDB.findDepartment(id);
                        for (int j = 0; j < empDB.employees.length; j++) {
                            for (int k = 0; k < department.length; k++) {
                                if (empDB.employees[j].getId().equals(department[k])) {
                                    empDB.delete(department[k]);
                                    break;
                                }
                            }
                        }
                        departments[i] = null;

                    }
                }else {
                    newDepartments[newIndex] = departments[i];
                    newIndex++;
                }
            } catch (Exception e) {
            }
        }
        departments = newDepartments;
        size--;
    }

    public Departments findOne(String id) {
        for (Departments department : departments) {
            if (department.getId().equals(id)) {
               return department;
            }
        }
        return null;
    }

    public Departments[] findAll() {
        return departments;
    }
}
