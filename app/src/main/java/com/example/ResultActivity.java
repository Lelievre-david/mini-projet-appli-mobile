package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mini_projet_appli_mobile.R;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    ListView myList;
    Films receivedFilms;
    TextView myText;
    ArrayList<Film> listFilms;
    ArrayList<String> movieTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        myList = (ListView)findViewById(R.id.ListViewMovies);
        myText = findViewById(R.id.TextViewMovies);
        movieTitles = new ArrayList<String>();
        Bundle extras = getIntent().getExtras();
        receivedFilms = extras.getParcelable("films");
        listFilms = receivedFilms.getListe_film();
        for (Film f:listFilms) {
            movieTitles.add(f.getTitle());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_view, R.id.TextViewMovies, movieTitles);
        myList.setAdapter(arrayAdapter);
    }
}