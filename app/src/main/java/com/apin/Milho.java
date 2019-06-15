package com.apin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Milho extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milho);

        intent = new Intent(Milho.this, ConteudoPratylenchus.class);

        Button buttonBrachyaurus = findViewById(R.id.button_brachyauru);
        buttonBrachyaurus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.putExtra("titulo", "Pratylenchus Brachyurus");
                intent.putExtra("document", "milho");
                intent.putExtra("tipo", "pratylenchusbrachyurus");
                startActivity(intent);
            }
        });

        Button buttonZeae = findViewById(R.id.button_zeae);
        buttonZeae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.putExtra("titulo", "Pratylenchus Zeae");
                intent.putExtra("document", "milho");
                intent.putExtra("tipo", "pratylenchuszeae");
                startActivity(intent);
            }
        });
    }
}
