package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class Profil extends AppCompatActivity {

    ImageView imgProfil;
    TextView txtProfil;
    TextView txtTitre;
    ImageButton retour_Button;
    FloatingActionButton favoris_Button;
    Neighbour profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imgProfil = (ImageView)findViewById(R.id.imgProfil);
        txtProfil = (TextView)findViewById(R.id.txtProfil);
        txtTitre = (TextView)findViewById(R.id.txtTitre);

        Intent intent = getIntent();
        Integer profilID = intent.getIntExtra("profil", -1);
        profil = DummyNeighbourApiService.getProfil(this, profilID);

        imgProfil.setImageURI(Uri.parse(profil.getAvatarUrl()));
        txtProfil.setText(profil.getName());
        txtTitre.setText(profil.getName());

        retour_Button = (ImageButton)findViewById(R.id.retourButton);
        retour_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        favoris_Button = (FloatingActionButton)findViewById(R.id.favorisButton);
        favoris_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (profil.isFavoris() == false){
                    profil.setFavoris(true);
                }
                else profil.setFavoris(false);


            }
        });
    }

}
