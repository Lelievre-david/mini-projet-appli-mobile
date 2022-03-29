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
    String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        myList = (ListView)findViewById(R.id.ListViewMovies);
        myText = findViewById(R.id.TextViewMovies);
        Bundle extras = getIntent().getExtras();
        receivedFilms = extras.getParcelable("films");
        Log.i("ResultActivity", receivedFilms.getListe_film().toString());
        ArrayAdapter<Film> arrayAdapter = new ArrayAdapter<Film>(this, R.layout.list_view, R.id.TextViewMovies, receivedFilms.getListe_film());
        myList.setAdapter(arrayAdapter);
    }
}