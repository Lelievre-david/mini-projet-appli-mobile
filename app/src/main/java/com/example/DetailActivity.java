package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mini_projet_appli_mobile.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getExtras();
        Film titanic = extras.getParcelable("leMovie");

        TextView titre = findViewById(R.id.titre);
        TextView annee = findViewById(R.id.annee);
        TextView synopsis =findViewById(R.id.synopsis);

        titre.setText(titanic.getTitle());
        annee.setText(titanic.getYear());
        synopsis.setText(titanic.getSynopsis());



    }
}