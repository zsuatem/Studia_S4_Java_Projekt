package xyz.zsuatem.appstore.people;

public enum SubcontractorType {
    bad(3000.0, 1.2, 1.2),
    average(5000.0, 1.0, 1.1),
    good(7000.0, 1.0, 1.0);

    private final Double salary;
    private final Double timeMultiplier;
    private final Double errorMultiplier;

    SubcontractorType(Double salary, Double timeMultiplier, Double errorMultiplier) {
        this.salary = salary;
        this.timeMultiplier = timeMultiplier;
        this.errorMultiplier = errorMultiplier;
    }

    public Double getSalary() {
        return salary;
    }

    public Double getTimeMultiplier() {
        return timeMultiplier;
    }

    public Double getErrorMultiplier() {
        return errorMultiplier;
    }
}
