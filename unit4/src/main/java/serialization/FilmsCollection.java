package serialization;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class FilmsCollection implements Serializable {

    private HashSet<Film> films = new HashSet<>();

    public void addNewFilm(Film film) {
        films.add(film);
    }

    public Set<Film> getFilms() {
        return films;
    }
}
