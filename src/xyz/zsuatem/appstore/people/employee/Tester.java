package xyz.zsuatem.appstore.people.employee;

import xyz.zsuatem.appstore.Interface;

import java.util.concurrent.ThreadLocalRandom;

public class Tester extends Employee {

    public Tester() {
        super(ThreadLocalRandom.current().nextDouble(4000.0, 5000.0));
    }

    @Override
    public String getBasicEmployeeInfo() {
        return "Tester: Imię i nazwisko: " + getFullName() + "\tPensja: " + Interface.decimalFormat.format(getSalary().doubleValue()) + "zł";
    }

    @Override
    public String getEmployeeInfo() {
        return getBasicEmployeeInfo();
    }
}
