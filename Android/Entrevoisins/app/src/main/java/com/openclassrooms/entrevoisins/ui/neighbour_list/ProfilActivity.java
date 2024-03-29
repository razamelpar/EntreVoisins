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

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


public class ProfilActivity extends AppCompatActivity {

    private NeighbourApiService mApiService;
    ImageView imgProfil;
    TextView txtProfil;
    TextView txtTitre;
    TextView txtWeb;
    TextView txtAdresse;
    TextView txtTel;
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
        txtWeb = (TextView)findViewById(R.id.txtWeb);
        txtAdresse = (TextView) findViewById(R.id.txtAdresse);
        txtTel = (TextView) findViewById(R.id.txtTel);

        /** recuperation de mon objet **/
        mApiService = DI.getNeighbourApiService();
        profil = getIntent().getParcelableExtra("profil");

        Glide.with(this).load(profil.getAvatarUrl()).into(imgProfil);

        txtProfil.setText(profil.getName());
        txtTitre.setText(profil.getName());
        txtWeb.setText(txtWeb.getText() + profil.getName());


        retour_Button = (ImageButton)findViewById(R.id.retourButton);
        retour_Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });


        favoris_Button = (FloatingActionButton)findViewById(R.id.favorisButton);
        if (profil.isFavoris() == false){
            favoris_Button.setImageResource(R.drawable.ic_star_white_24dp);
        }
        else favoris_Button.setImageResource(R.drawable.ic_star_gold_24dp);

        favoris_Button.setOnClickListener(new View.OnClickListener() {

            /**
             * utilisation de ma methode de services changeFavoris
             * @param v
             */
            @Override
            public void onClick(View v) {

                if (profil.isFavoris() == false){
                    mApiService.changeFavoris(profil);
                    favoris_Button.setImageResource(R.drawable.ic_star_gold_24dp);
                }
                else if (profil.isFavoris() == true) {
                    mApiService.changeFavoris(profil);
                    favoris_Button.setImageResource(R.drawable.ic_star_white_24dp);
                }


            }
        });
    }

}
