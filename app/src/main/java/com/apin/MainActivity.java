package com.apin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonHeterodera = findViewById(R.id.button_heterodera);
        buttonHeterodera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Heterodera.class);
                startActivity(intent);
            }
        });

        Button buttonMeloidogyne = findViewById(R.id.button_meloidogyne);
        buttonMeloidogyne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Meloidogyne.class);
                startActivity(intent);
            }
        });

        Button buttonPratylenchus = findViewById(R.id.button_pratylenchus);
        buttonPratylenchus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Pratylenchus.class);
                startActivity(intent);
            }
        });

    }
}
