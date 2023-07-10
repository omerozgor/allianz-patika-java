package model;

import java.util.ArrayList;

public class Category {

    private String name;
    private ArrayList<Film> filmArrayList;


    public Category(String name) {
        this.name = name;
        filmArrayList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addFilm(Film film) {
        filmArrayList.add(film);
    }

    public ArrayList<Film> getFilmArrayList() {
        return filmArrayList;
    }
}
