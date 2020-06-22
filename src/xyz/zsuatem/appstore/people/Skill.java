package xyz.zsuatem.appstore.people;

import org.jetbrains.annotations.Nullable;

public enum Skill {
    backend(0, "Back-end"),
    frontend(1, "Front-end"),
    database(2, "Dazy danych"),
    wordPress(3, "WordPress"),
    prestaShop(4, "PrestaShop"),
    mobileAppDevelopment(5, "Tworzenie aplikacji mobilnych");

    private final Integer id;
    private final String name;

    Skill(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static @Nullable Skill getById(Integer id) {
        for (Skill e : values()) {
            if (e.id.equals(id)) return e;
        }
        return null;
    }

    public static Integer getNumberOfSkills() {
        return values().length;
    }

    public String getName() {
        return name;
    }
}
