package xyz.zsuatem.appstore.people;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public enum Technology {
    backend(0, "Back-end"),
    frontend(1, "Front-end"),
    database(2, "Dazy danych"),
    wordPress(3, "WordPress"),
    prestaShop(4, "PrestaShop"),
    mobileAppDevelopment(5, "Aplikacje mobilna");

    private final Integer id;
    private final String name;

    Technology(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static @Nullable Technology getById(Integer id) {
        for (Technology e : values()) {
            if (e.id.equals(id)) return e;
        }
        return null;
    }

    public static Integer getNumberOfSkills() {
        return values().length;
    }

    public static Technology getRandomTechnology() {
        Integer randomTechnologyId = ThreadLocalRandom.current().nextInt(0, getNumberOfSkills());
        return getById(randomTechnologyId);
    }

    public static @NotNull ArrayList<Technology> getRandomTechnologyList(Integer amount) {
        ArrayList<Technology> technologyArrayList = new ArrayList<>();
        Integer tmpNumberOfTechnology = 0;

        while (tmpNumberOfTechnology <= amount) {
            Technology technology = Technology.getRandomTechnology();

            if (!technologyArrayList.contains(technology)) {
                technologyArrayList.add(technology);
                tmpNumberOfTechnology++;
            }
        }

        return technologyArrayList;
    }

    public String getName() {
        return name;
    }
}
