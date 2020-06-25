package xyz.zsuatem.appstore;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ThreadLocalRandom;

public enum ProjectType {
    easy(0, "łatwy"),
    medium(1, "średni"),
    hard(2, "trudny");

    private final Integer id;
    private final String name;

    ProjectType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Integer getNumberOfSkills() {
        return values().length;
    }

    private static @Nullable ProjectType getById(Integer id) {
        for (ProjectType e : values()) {
            if (e.id.equals(id)) return e;
        }
        return null;
    }

    public static ProjectType getRandomProjectType() {
        Integer randomProjectTypeId = ThreadLocalRandom.current().nextInt(0, getNumberOfSkills());
        return getById(randomProjectTypeId);
    }

    public String getName() {
        return name;
    }
}
