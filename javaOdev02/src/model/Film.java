package model;

import java.util.List;

public class Film {

    private String filmName;
    private int year;
    private String director;
    private double imdb;
    private List<Category> categoryArray;
    private String time;
    private List<Platform> platformList;

    public Film(String filmName, int year, String director, double imdb, List<Category> categoryArray, String time, List<Platform> platformList) {
        this.filmName = filmName;
        this.year = year;
        this.director = director;
        this.imdb = imdb;
        this.categoryArray = categoryArray;
        this.time = time;
        this.platformList = platformList;
    }

    public Film(){

    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getImdb() {
        return imdb;
    }

    public void setImdb(double imdb) {
        this.imdb = imdb;
    }

    public List<Category> getCategoryArray() {
        return categoryArray;
    }

    public void setCategoryArray(List<Category> categoryArray) {
        this.categoryArray = categoryArray;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Platform> getPlatformList() {
        return platformList;
    }

    public void setPlatformList(List<Platform> platformList) {
        this.platformList = platformList;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmName='" + filmName + '\'' +
                ", year=" + year +
                ", director='" + director + '\'' +
                ", imdb=" + imdb +
                ", categoryArray=" + categoryArray +
                ", time='" + time + '\'' +
                ", platformList=" + platformList +
                '}';
    }
}
