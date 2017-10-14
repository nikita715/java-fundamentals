package serialization;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class FilmsApp {

    private FilmsCollection filmsCollection;
    private String collectionFilePath = Paths.get("unit4/src/main/resources/films").toFile().getAbsolutePath();

    public FilmsApp(FilmsCollection filmsCollection) {
        this.filmsCollection = filmsCollection;
    }

    public FilmsApp() {
        filmsCollection = new FilmsCollection();
    }

    public void addFilm(Film film) {
        filmsCollection.addNewFilm(film);
    }

    public Set<Film> getFilms() {
        return filmsCollection.getFilms();
    }

    public void save() {
        try (FileOutputStream fos = new FileOutputStream(collectionFilePath)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(filmsCollection);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try (FileInputStream fis = new FileInputStream(collectionFilePath)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            filmsCollection = (FilmsCollection) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FilmsApp filmsApp = new FilmsApp();
        filmsApp.load();
        Film film1 = new Film("film35");
        Actor actor1 = new Actor("actor32");
        film1.addActor(actor1);
        filmsApp.addFilm(film1);
        filmsApp.save();
        for (Film film : filmsApp.getFilms()) {
            System.out.println(film.getName());
        }
    }
}
