package com.sinan.cet.barkod;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by ÇET on 16.5.2015.
 */
public class Giris extends ActionBarActivity {
Button sorgula;
Button ekle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giris);

         sorgula = (Button) findViewById(R.id.sorgula); // Sorgula butonu atanır ve Sorgula.java'nın çağrılması sağlanır
             sorgula.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 Intent intent = new Intent(Giris.this,Sorgula.class);
                    startActivity(intent);


                 }
            }
         );

        ekle = (Button) findViewById(R.id.ekle); // Ekle Butonu atanır ve Ekle.java'nın çağrılması sağlanır
             ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Giris.this,Ekle.class);
                    startActivity(intent);


                }
             }
        );


        }

    }



