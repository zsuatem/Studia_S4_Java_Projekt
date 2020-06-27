package xyz.zsuatem.appstore.people;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ThreadLocalRandom;

public enum ClientType {
    easy(0),
    medium(1),
    hard(2);

    private final Integer id;

    ClientType(Integer id) {
        this.id = id;
    }

    private static @Nullable ClientType getById(Integer id) {
        for (ClientType clientType : values()) {
            if (clientType.id.equals(id)) return clientType;
        }
        return null;
    }

    public static ClientType getRandomClientType() {
        Integer randomClientTypeId = ThreadLocalRandom.current().nextInt(0, values().length);
        return getById(randomClientTypeId);
    }

}
