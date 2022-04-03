package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mini_projet_appli_mobile.R;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    ListView myList;
    Films receivedFilms;
    TextView myText;
    ImageView myPoster;
    ImageButton back;
    ArrayList<Film> listFilms;
    ArrayList<String> movieTitles;
    ArrayList<SubjectData> theList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Récupération des composants de la vue
        myList = (ListView)findViewById(R.id.ListViewMovies);
        myText = findViewById(R.id.TextViewMovies);
        myPoster = findViewById(R.id.list_image);
        back = findViewById(R.id.close);
        movieTitles = new ArrayList<String>();
        theList = new ArrayList<SubjectData>();

        //Récupération des extras envoyés dans l'Intent
        Bundle extras = getIntent().getExtras();
        receivedFilms = extras.getParcelable("films");
        listFilms = receivedFilms.getListe_film();

        //Création d'un Adapter pour mettre en place la ListView
        for (Film f:listFilms) {
            movieTitles.add(f.getTitle());
            theList.add(new SubjectData(f, f.getTitle(), f.getPath_poster()));
        }
        CustomAdapter customAdapter = new CustomAdapter(this, theList);
        myList.setAdapter(customAdapter);


        //Création d'un Listener pour passer à la prochaine vue
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SubjectData selectedItem = (SubjectData) parent.getItemAtPosition(position);
                Film film = selectedItem.film;
                Intent versTertiaire = new Intent(ResultActivity.this, DetailActivity.class);
                versTertiaire.putExtra("leMovie", film);
                startActivity(versTertiaire);
            }
        });

        //branchement du bouton close
        back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}