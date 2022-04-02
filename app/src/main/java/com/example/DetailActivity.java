package com.example;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mini_projet_appli_mobile.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //recupération du film passé en paramètre de l'intent
        Bundle extras = getIntent().getExtras();
        Film movie = extras.getParcelable("leMovie");

        //recuperation des composants de la vue
        ImageView imageView = findViewById(R.id.affiche);
        TextView titre = findViewById(R.id.titre);
        TextView annee = findViewById(R.id.annee);
        TextView synopsis = findViewById(R.id.synopsis);
        ImageButton back = findViewById(R.id.close);
        String justYear = "";

        //on tronque la string  de la date pour juste garder l'année si elle n'est pas vide
        if (!movie.getYear().matches("")){
            justYear = movie.getYear().substring(0,4);
        }

        //ajout des informations dans les composants
        titre.setText(movie.getTitle());
        annee.setText(justYear);
        synopsis.setText(movie.getSynopsis());
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie.getPath_poster()).into(imageView);

        //branchement du bouton close
        back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}