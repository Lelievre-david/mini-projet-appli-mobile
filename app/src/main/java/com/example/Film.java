package com.example;

import android.os.Parcel;
import android.os.Parcelable;

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

    protected Film(Parcel in) {
        id = in.readString();
        title = in.readString();
        synopsis = in.readString();
        year = in.readString();
        path_poster = in.readString();
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
