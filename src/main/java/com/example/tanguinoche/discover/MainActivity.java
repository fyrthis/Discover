package com.example.rania.listview2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener  {

    ImageButton btn1;
    ImageButton btn2;
    ImageButton btn3;
    ImageButton btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);

        btn1 = (ImageButton)findViewById(R.id.favoris);
        btn2 = (ImageButton)findViewById(R.id.play);
        btn3 = (ImageButton)findViewById(R.id.recherche2);
        btn4 = (ImageButton)findViewById(R.id.recherche2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        // On récupère l'identifiant de la vue, et en fonction de cet identifiant…
        switch (v.getId()) {

            // Si l'identifiant de la vue est celui du bouton favoris
            case R.id.favoris:
                Toast.makeText(this, "Switching to Favoris!!!", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, SecondActivity.class);
                startActivity(i);
                break;

            case R.id.play:
                //play music
                break;

            case R.id.recherche1:
                Intent j = new Intent(this, Recherche1.class);
                startActivity(j);
                break;

            case R.id.recherche2:
                Intent g = new Intent(this, Recherche2.class);
                startActivity(g);
                break;
        }
    }
}

