package javase04.serialization;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FilmsCollection implements Serializable {

    private HashSet<Film> films = new HashSet<>();

    public void addNewFilm(Film film) {
        films.add(film);
    }

    public Set<Film> getFilms() {
        return films;
    }
}
