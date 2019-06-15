package com.apin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soja extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soja);

        Button buttonMeloidogyne = findViewById(R.id.button_meloidogyne);
        buttonMeloidogyne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Soja.this, ConteudoMeloydogine.class);

                intent.putExtra("titulo", "Melodoigyne Javanica");
                intent.putExtra("document", "soja");
                intent.putExtra("tipo", "melodoigynejavanica");
                startActivity(intent);
            }
        });

        Button buttonMeloidogyneIncognita = findViewById(R.id.button_meloydogyneincognita);
        buttonMeloidogyneIncognita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Soja.this, ConteudoMeloydogine.class);

                intent.putExtra("titulo", "Melodoigyne Incognita");
                intent.putExtra("document", "soja");
                intent.putExtra("tipo", "meloydogyneincognita");
                startActivity(intent);
            }
        });

        Button buttonHeterodera = findViewById(R.id.button_heterodera);
        buttonHeterodera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Soja.this, ConteudoHeterodera.class);

                intent.putExtra("titulo", "Heterodera Glycines");
                intent.putExtra("document", "soja");
                intent.putExtra("tipo", "heteroderaglycines");
                startActivity(intent);
            }
        });
    }
}
