package xyz.zsuatem.appstore.people.employee;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ThreadLocalRandom;

public enum EmployeeType {
    programmer(0, "programista"),
    salesman(1, "sprzedawca"),
    randomEmployeeTypeId(2, "tester");

    private final Integer id;
    private final String name;

    EmployeeType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private static @Nullable EmployeeType getById(Integer id) {
        for (EmployeeType e : values()) {
            if (e.id.equals(id)) return e;
        }
        return null;
    }

    public static EmployeeType getRandomEmployeeType() {
        Integer randomEmployeeTypeId = ThreadLocalRandom.current().nextInt(0, values().length);
        return getById(randomEmployeeTypeId);
    }

    public String getName() {
        return name;
    }
}
