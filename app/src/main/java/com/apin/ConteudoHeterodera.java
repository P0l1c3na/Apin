package com.apin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.apin.database.FirebaseConfig;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ConteudoHeterodera extends AppCompatActivity {

    TextView titulo;
    TextView conteudo;

    FirebaseFirestore db = FirebaseConfig.getFirebaseFirestore();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo_heterodera);

        Intent intent = getIntent();
        String tituloIntent = intent.getStringExtra("titulo");

        titulo = findViewById(R.id.titulo);
        conteudo = findViewById(R.id.conteudo);

        titulo.setText(tituloIntent);

        // FIrebaseFirestore
        read(intent.getStringExtra("document"), intent.getStringExtra("tipo"));
    }

    // Carregando do Firestore
    private void read(String document, final String tipo) {
        DocumentReference user = db.collection("nematoides").document(document);

        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    DocumentSnapshot doc = task.getResult();
                    StringBuilder fields = new StringBuilder("");
                    fields.append(doc.get(tipo));

                    conteudo.setText(fields.toString());
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }
}