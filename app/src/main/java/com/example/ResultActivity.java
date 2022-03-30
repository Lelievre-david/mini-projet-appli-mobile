package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    int i;

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
        i = 0;
        for (Film f:listFilms) {
            movieTitles.add(f.getTitle());
        }
        ArrayAdapter<Film> arrayAdapter = new ArrayAdapter<Film>(this, R.layout.list_view, R.id.TextViewMovies, listFilms);
        myList.setAdapter(arrayAdapter);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Film selectedItem = (Film) parent.getItemAtPosition(position);
                Intent versTertiaire = new Intent(ResultActivity.this, DetailActivity.class);
                versTertiaire.putExtra("leMovie", selectedItem);
                Log.i("ResultActivity", selectedItem.toStringLe2());
                startActivity(versTertiaire);
            }
        });
    }
}