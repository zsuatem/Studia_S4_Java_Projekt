package xyz.zsuatem.appstore;

public class Office {
    public final Integer maxNumberOfEmployees;
    public final Double rentalFee;

    public Office(Integer maxNumberOfEmployees, Double rentalFee) {
        this.maxNumberOfEmployees = maxNumberOfEmployees;
        this.rentalFee = rentalFee;
    }

    public Integer getMaxNumberOfEmployees() {
        return maxNumberOfEmployees;
    }

    public Double getRentalFee() {
        return rentalFee;
    }
}
