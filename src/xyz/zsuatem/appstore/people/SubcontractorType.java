package xyz.zsuatem.appstore.people;

public enum SubcontractorType {
    bad(3000.0, 7, 2),
    average(5000.0, 8, 1),
    good(7000.0, 8, 0);

    private final Double salary;
    private final Integer punctuality;
    private final Integer errors;

    SubcontractorType(Double salary, Integer punctuality, Integer errors) {
        this.salary = salary;
        this.punctuality = punctuality;
        this.errors = errors;
    }

    public Double getSalary() {
        return salary;
    }

    public Integer getPunctuality() {
        return punctuality;
    }

    public Integer getErrors() {
        return errors;
    }
}
