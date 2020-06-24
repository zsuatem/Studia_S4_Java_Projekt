package xyz.zsuatem.appstore;

public class Office {
    public final Integer maxNumberOfEmployees;
    public final Double rentalFee;

    public Office(Integer maxNumberOfEmployees) {
        this.maxNumberOfEmployees = maxNumberOfEmployees;
        this.rentalFee = maxNumberOfEmployees * 250.0;
    }

    public Integer getMaxNumberOfEmployees() {
        return maxNumberOfEmployees;
    }

    public Double getRentalFee() {
        return rentalFee;
    }
}
