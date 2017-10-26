package javase04.serialization;

import java.io.Serializable;

public class Actor implements Serializable {

    private String name;

    public Actor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
