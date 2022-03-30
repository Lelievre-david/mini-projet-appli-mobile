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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mini_projet_appli_mobile.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    static final String api_key = "434a5b32f7281f2f7050697f579bd253";
    private ArrayList<Film> liste_film;
    private ArrayList<Genre> liste_genre;
    private String poster_path;
    private String release_date;
    private ArrayAdapter<Genre> adapter;
    private String genre_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        liste_film= new ArrayList<Film>();
        liste_genre= new ArrayList<Genre>();
        liste_genre.add(new Genre("null","Tous"));
        adapter = new ArrayAdapter<Genre>(this,android.R.layout.simple_spinner_item,liste_genre);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Récupération des composants de la vue
        EditText nom = findViewById(R.id.titre);
        EditText date = findViewById(R.id.date_value);

        Spinner genre = findViewById(R.id.genre_value);
        genre.setAdapter(adapter);

        SeekBar nombre = findViewById(R.id.nombre_value);
        TextView seekbar_value = findViewById(R.id.seekbar_value);
        Button recherche = findViewById(R.id.recherche);

        //recupération des genres disponibles dans l'API
        Ion.with(this)
                .load("https://api.themoviedb.org/3/genre/movie/list?api_key=434a5b32f7281f2f7050697f579bd253&language=fr")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (e != null) {
                            Log.e("get_genre", "Error: " + e.getMessage());
                        } else {
                            Log.i("get_genre", "Success: " + result.toString());
                            JsonArray results = result.getAsJsonArray("genres");
                            for (int i = 0; i < results.size(); i++) {
                                JsonObject genre = results.get(i).getAsJsonObject();
                                liste_genre.add(new Genre(genre.get("id").getAsString(), genre.get("name").getAsString()));
                            }
                            adapter.notifyDataSetChanged();

                        }
                    }
                });


        //branchement de la seekbar avec le text qui affiche sa valeur
        nombre.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub
                seekbar_value.setText(String.valueOf(nombre.getProgress()));
            }
        });

        //lancement de la recherche
        recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query_value=nom.getText().toString();
                if (query_value.matches("")){
                    Toast.makeText(v.getContext(), "merci de rentrer une recherche dans le champs rechercher des films", Toast.LENGTH_LONG).show();
                }
                else{
                    query_value=query_value.replace(" ","+");
                    genre_id="";

                    //récupération de l'id du genre selectionné dans le spinner
                    for (Genre element:liste_genre) {
                        if (genre.getSelectedItem().toString()==element.getName()){
                            genre_id=element.getId();
                        }
                    }

                    // Get the data from the server with Ion
                    Ion.with(v.getContext())
                            .load("https://api.themoviedb.org/3/search/movie?api_key="+api_key+"&query="+query_value+"&year="+date.getText().toString()+"&language=fr")
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    if (e != null) {
                                        Log.e("MainActivity", "Error: " + e.getMessage());
                                    } else {
                                        Log.i("MainActivity", "Success: " + result.toString());
                                        JsonArray results = result.getAsJsonArray("results");

                                        //on parcourt tout les films
                                        for (int i = 0; i < results.size(); i++) {
                                            JsonObject movie = results.get(i).getAsJsonObject();

                                            //on gère les valeurs null renvoyés dans certains cas par l'API
                                            try {
                                                poster_path = movie.get("poster_path").getAsString();
                                                release_date = movie.get("release_date").getAsString();
                                            } catch (Exception exception) {
                                                poster_path = "";
                                                release_date= "";
                                            }
                                            //si l'utilisateur n'a pas selectionné de genre on ajoute le film sans regarder
                                            if(genre_id.matches("null")){
                                                liste_film.add(new Film(
                                                        movie.get("id").getAsString(),
                                                        movie.get("title").getAsString(),
                                                        movie.get("overview").getAsString(),
                                                        release_date,
                                                        poster_path
                                                ));
                                            }

                                            //sinon on récupère le tableau de genre_ids du film et on le parcours
                                            else {
                                                JsonArray genre_ids = movie.get("genre_ids").getAsJsonArray();
                                                for (int j = 0; j < genre_ids.size(); j++) {
                                                    //si il y a le genre selectionné on ajoute le film
                                                    if (genre_ids.get(j).getAsString().matches(genre_id)) {
                                                        liste_film.add(new Film(
                                                                movie.get("id").getAsString(),
                                                                movie.get("title").getAsString(),
                                                                movie.get("overview").getAsString(),
                                                                release_date,
                                                                poster_path
                                                        ));
                                                    }
                                                }
                                            }

                                        }

                                        //gestion du nombre de résultats
                                        if (liste_film.size()!=0) {
                                            Log.i("progress", String.valueOf(nombre.getProgress()));
                                            Films films;                                            //on tronque la liste de film si nécessaire
                                            if (nombre.getProgress()<liste_film.size()){
                                                films = new Films(new ArrayList<>(liste_film.subList(0,nombre.getProgress())));
                                            }
                                            else{
                                                films = new Films(liste_film);
                                            }

                                            // Print the films
                                            for (Film film : films.getListe_film()) {
                                                Log.i("MainActivity here", film.toString());
                                            }

                                            Intent versSecondaire = new Intent(MainActivity.this, ResultActivity.class);
                                            versSecondaire.putExtra("films", films);
                                            startActivity(versSecondaire);
                                        }
                                        else {
                                            Toast.makeText(v.getContext(),"aucun résultat trouvé",Toast.LENGTH_LONG).show();;
                                        }
                                    }
                                }
                            });

                }

            }
        });

    }

}