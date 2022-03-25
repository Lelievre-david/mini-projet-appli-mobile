package com.example.mini_projet_appli_mobile;

public class Film {
    private String id;
    private String title;
    private String synopsis;
    private String year;
    private String path_poster;

    public Film(String id, String title, String synopsis, String year, String path_poster) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.year = year;
        this.path_poster = path_poster;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPath_poster() {
        return path_poster;
    }

    public void setPath_poster(String path_poster) {
        this.path_poster = path_poster;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", year='" + year + '\'' +
                ", path_poster='" + path_poster + '\'' +
                '}';
    }


}