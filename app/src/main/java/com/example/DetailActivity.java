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

        Film titanic = new Film("597","Titanic","Southampton, 10 avril 1912. Le paquebot le plus grand et le plus moderne du monde, réputé pour son insubmersibilité, le « Titanic », appareille pour son premier voyage. Quatre jours plus tard, il heurte un iceberg. À son bord, un artiste pauvre et une grande bourgeoise tombent amoureux.","1997-11-18","/vpsvHLkoeKUjceIMeNSqCp3xEyY.jpg");

        TextView titre = findViewById(R.id.titre);
        TextView annee = findViewById(R.id.annee);
        TextView synopsis =findViewById(R.id.synopsis);

        titre.setText(titanic.getTitle());
        annee.setText(titanic.getYear());
        synopsis.setText(titanic.getSynopsis());



    }
}