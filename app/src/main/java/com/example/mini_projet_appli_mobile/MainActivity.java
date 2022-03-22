package com.example.mini_projet_appli_mobile;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_ID = 19;

    EditText nom = findViewById(R.id.nom);
    EditText date = findViewById(R.id.date_value);
    Spinner genre = findViewById(R.id.genre_value);
    SeekBar nombre = findViewById(R.id.nombre_value);
    Button recherche = findViewById(R.id.recherche);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent versSecondaire = new Intent(MainActivity.this, ResultActivity.class);
                versSecondaire.putExtra("titre", (Parcelable) nom);
                versSecondaire.putExtra("date", (Parcelable) date);
                versSecondaire.putExtra("genre", (Parcelable) genre);
                versSecondaire.putExtra("nombre_result", (Parcelable) nombre);
                startActivityForResult(versSecondaire,REQUEST_ID);
            }
        });
    }

}