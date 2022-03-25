package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mini_projet_appli_mobile.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    static final String api_key = "434a5b32f7281f2f7050697f579bd253";

    private ArrayList<Film> liste_film;
    private String poster_path="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        liste_film= new ArrayList<Film>();

        EditText nom = findViewById(R.id.titre);
        EditText date = findViewById(R.id.date_value);
        Spinner genre = findViewById(R.id.genre_value);
        SeekBar nombre = findViewById(R.id.nombre_value);
        Button recherche = findViewById(R.id.recherche);

        String[] arraySpinner = new String[] {
                "comedie", "thriller", "horreur", "aventure", "drame"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genre.setAdapter(adapter);

        recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("uri:","https://api.themoviedb.org/3/search/movie?api_key="+api_key+"&query="+nom.getText().toString()+"&year="+date.getText().toString()+"&language=fr&page=1");
                // Get the data from the server with Ion
                Ion.with(v.getContext())
                        .load("https://api.themoviedb.org/3/search/movie?api_key="+api_key+"&query="+nom.getText().toString()+"&year="+date.getText().toString()+"&language=fr&page=1")
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if (e != null) {
                                    Log.e("MainActivity", "Error: " + e.getMessage());
                                } else {
                                    Log.i("MainActivity", "Success: " + result.toString());
                                    JsonArray results = result.getAsJsonArray("results");
                                    if (results.size()>0) {

                                        for (int i = 0; i < results.size(); i++) {
                                            JsonObject movie = results.get(i).getAsJsonObject();
                                            try {
                                                poster_path = movie.get("poster_path").getAsString();
                                            } catch (Exception exception) {
                                                poster_path = "";
                                            }
                                            liste_film.add(new Film(
                                                    movie.get("id").getAsString(),
                                                    movie.get("title").getAsString(),
                                                    movie.get("overview").getAsString(),
                                                    movie.get("release_date").getAsString(),
                                                    poster_path
                                            ));
                                        }
                                        // Print the films
                                        for (Film film : liste_film) {
                                            Log.i("MainActivity", film.toString());
                                        }
                                        Intent versSecondaire = new Intent(MainActivity.this, ResultActivity.class);
                                        versSecondaire.putExtra("titre", liste_film);
                                        startActivity(versSecondaire);
                                    }
                                    else{
                                        Toast.makeText(v.getContext(), "aucun résultat trouvé", Toast.LENGTH_LONG);
                                        Log.i("not found:","true");
                                    }
                                }
                            }
                        });
            }
        });

    }

}