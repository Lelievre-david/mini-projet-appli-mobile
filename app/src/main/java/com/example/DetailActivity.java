package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mini_projet_appli_mobile.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getExtras();
        Film movie = extras.getParcelable("leMovie");
        ImageView imageView = findViewById(R.id.affiche);
        TextView titre = findViewById(R.id.titre);
        TextView annee = findViewById(R.id.annee);
        TextView synopsis = findViewById(R.id.synopsis);


        titre.setText(movie.getTitle());
        annee.setText(movie.getYear().substring(0,4));
        synopsis.setText(movie.getSynopsis());
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie.getPath_poster()).into(imageView);


    }
}