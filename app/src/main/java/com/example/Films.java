package com.example;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Films implements Parcelable {
    private ArrayList<Film> liste_film;

    public Films(ArrayList<Film> liste_film) {
        this.liste_film=liste_film;
    }

    protected Films(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Films> CREATOR = new Creator<Films>() {
        @Override
        public Films createFromParcel(Parcel in) {
            return new Films(in);
        }

        @Override
        public Films[] newArray(int size) {
            return new Films[size];
        }
    };

    public ArrayList<Film> getListe_film() {
        return liste_film;
    }

    public void setListe_film(ArrayList<Film> liste_film) {
        this.liste_film = liste_film;
    }




}
