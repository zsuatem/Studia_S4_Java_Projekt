package xyz.zsuatem.appstore.people.employee;

import xyz.zsuatem.appstore.Interface;

import java.util.concurrent.ThreadLocalRandom;

public class Salesman extends Employee {

    private Integer days;

    public Salesman() {
        super(ThreadLocalRandom.current().nextDouble(3000.0, 4000.0));
        days = 0;
    }

    public void addNextDay() {
        days++;
    }

    public Boolean isFiveDays() {
        if (days >= 5) {
            days = 0;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getBasicEmployeeInfo() {
        if (isEmployed().booleanValue() != false) {
            return "Sprzedawca: Imię i nazwisko: " + getFullName() + "\tPensja: " + Interface.decimalFormat.format(getSalary().doubleValue()) + "zł\n" +
                    "\tPoszukiwanie nowych klientów: " + days + "/5";
        } else {
            return "Sprzedawca: Imię i nazwisko: " + getFullName() + "\tPensja: " + Interface.decimalFormat.format(getSalary().doubleValue()) + "zł";
        }
    }

    @Override
    public String getEmployeeInfo() {
        return getBasicEmployeeInfo();
    }
}
