package javase04.serialization;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Film implements Serializable {

    private String name;

    private Set<Actor> actors = new HashSet<>();

    public Film(String name) {
        this.name = name;
    }

    public Film(String name, Set<Actor> actors) {
        this.name = name;
        this.actors = actors;
    }


    public String getName() {
        return name;
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }
}
