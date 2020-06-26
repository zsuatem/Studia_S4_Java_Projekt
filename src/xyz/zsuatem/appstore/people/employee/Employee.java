package xyz.zsuatem.appstore.people.employee;

import xyz.zsuatem.appstore.FullFullNameGeneratorFromTxtFile;
import xyz.zsuatem.appstore.people.Human;

public abstract class Employee extends Human {

    private Boolean employed = false;
    private Double salary;

    public Employee(Double salary) {
        super(new FullFullNameGeneratorFromTxtFile().getRandomFullName());
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }

    public abstract String getBasicEmployeeInfo();

    public abstract String getEmployeeInfo();

    public void setAsEmployed() {
        employed = true;
    }

    public Boolean isEmployed() {
        return employed;
    }
}
