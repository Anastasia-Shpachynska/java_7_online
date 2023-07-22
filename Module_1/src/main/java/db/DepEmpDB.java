package db;

public class DepEmpDB {

    private static DepEmpDB instance;

    private DepEmpDB() {
    }

    public static DepEmpDB getInstance() {
        if (instance == null) {
            instance = new DepEmpDB();
        }
        return instance;
    }

    DepartmentsDB depDB = DepartmentsDB.getInstance();
    EmployeesDB empDB = EmployeesDB.getInstance();

    private String[][] DepEmp = new String[10][2];
    String[] findDepartment = new String[DepEmp.length];
    String[] findEmployee = new String[DepEmp.length];
    int size = 0;

    public void expandArray() {
        String[][] newDepEmp = new String[DepEmp.length * 2][2];
        for (int i = 0; i < DepEmp.length; i++) {
            System.arraycopy(DepEmp[i], 0, newDepEmp[i], 0, DepEmp[i].length);
        }
        DepEmp = newDepEmp;
    }


    public void addEmployee(String departmentId, String employeeId) {
        if (size == DepEmp.length) {
            expandArray();
        }
        for (int i = 0; i < depDB.departments.length; i++) {
            if (depDB.departments[i].getId().equals(departmentId)) {
                for (int j = 0; j < empDB.employees.length; j++) {
                    if (empDB.employees[j].getId().equals(employeeId)) {
                        for (int k = 0; k < DepEmp.length; k++) {
                            if (DepEmp[k][0] == null && DepEmp[k][1] == null) {
                                DepEmp[k][0] = departmentId;
                                DepEmp[k][1] = employeeId;
                                size++;
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
    }

    public void deleteEmployee(String employeeId) {
        boolean left = false;
        for (int i = 0; i < DepEmp.length; i++) {
            for (int j = 0; j < DepEmp[i].length; j++) {
                try {
                    if (DepEmp[i][j].equals(employeeId)) {
                        DepEmp[i][0] = null;
                        DepEmp[i][1] = null;
                    }
                    if (left) {
                        DepEmp[i] = DepEmp[i + 1];
                    }
                }catch (Exception e) {
                }
            }
        }
        size--;
    }

    public String[] findEmployee(String employeeId) {
        for (int i = 0; i < DepEmp.length; i++) {
            for (int j = 0; j < DepEmp[i].length; j++) {
                if (DepEmp[i][j] != null && DepEmp[i][j].equals(employeeId)) {
                    for (int k = 0; k < findEmployee.length; k++) {
                        if (findEmployee[k] == null) {
                            findEmployee[k] = DepEmp[i][0];
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return findEmployee;
    }

    public String[] findDepartment(String departmentId) {
        for (int i = 0; i < DepEmp.length; i++) {
            for (int j = 0; j < DepEmp[i].length; j++) {
                if (DepEmp[i][j] != null && DepEmp[i][j].equals(departmentId)) {
                    for (int k = 0; k < findDepartment.length; k++) {
                        if (findDepartment[k] == null) {
                            findDepartment[k] = DepEmp[i][1];
                            break;
                        }
                    }
                }
            }
        }
        return findDepartment;
    }

    public String[] allEmployees() {
        String[] uniqueEmployees = new String[DepEmp.length];
        boolean isUnique = false;
        String employee = null;
        int count = 0;
        for (int i = 0; i < DepEmp.length; i++) {
            for (int j = 0; j < DepEmp[i].length; j++) {
                if (DepEmp[i][j] != null) {
                    employee = DepEmp[i][1];
                    isUnique = true;
                    for (int k = 0; k < count; k++) {
                        if (uniqueEmployees[k] != null && uniqueEmployees[k].equals(employee)) {
                            isUnique = false;
                        }
                    }
                }
                if (isUnique) {
                    uniqueEmployees[count] = employee;
                    count++;
                }
            }
        }
        return uniqueEmployees;
    }

    public String[] allDepartments() {
        String[] uniqueDepartments = new String[DepEmp.length];
        boolean isUnique = false;
        String department = null;
        int count = 0;
        for (int i = 0; i < DepEmp.length; i++) {
            for (int j = 0; j < DepEmp[i].length; j++) {
                if (DepEmp[i][j] != null) {
                    department = DepEmp[i][0];
                    isUnique = true;
                    for (int k = 0; k < count; k++) {
                        if (uniqueDepartments[k] != null && uniqueDepartments[k].equals(department)) {
                            isUnique = false;
                        }
                    }
                }
                if (isUnique) {
                    uniqueDepartments[count] = department;
                    count++;
                }
            }

        }
        return uniqueDepartments;
    }
}
