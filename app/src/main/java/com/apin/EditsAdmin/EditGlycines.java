package com.apin.EditsAdmin;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Scroller;
import android.widget.Toast;

import com.apin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class EditGlycines extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = EditGlycines.class.getSimpleName();
    private ProgressBar progressBar;
    TextInputEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_glycines);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Heterodera Glycines");
        progressBar = (ProgressBar) findViewById(R.id.loading);

        editText = (TextInputEditText) findViewById(R.id.edtdata);

        editText.setScroller(new Scroller(getApplicationContext()));
        editText.setVerticalScrollBarEnabled(true);
        editText.setMaxLines(50);
        editText.setMovementMethod(new ScrollingMovementMethod());

        progressBar.setVisibility(View.VISIBLE);
        db.collection("nematoides")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        progressBar.setVisibility(View.VISIBLE);
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.getString("heteroderaglycines")!=null) {
                                    String data = document.getString("heteroderaglycines");
                                    editText.setText(data);
                                }
                            }
                        } else {
                            Toast.makeText(EditGlycines.this, "Erro ao carregar conteúdo!", Toast.LENGTH_SHORT).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });

        FloatingActionButton fab = findViewById(R.id.btnFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // [START update_document]
                DocumentReference reference = db.collection("nematoides").document("soja");

                // Set the "heteroderaglycines" field of the culture 'soja'
                reference
                        .update("heteroderaglycines", editText.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                db.collection("nematoides")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        if(document.getString("heteroderaglycines")!=null) {
                                                            String data = document.getString("heteroderaglycines");
                                                            editText.setText(data);
                                                        }
                                                    }
                                                } else {
                                                    Toast.makeText(EditGlycines.this, "Erro ao carregar conteúdo!", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                Toast.makeText(EditGlycines.this, "Conteúdo editado com sucesso!", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "DocumentSnapshot successfully updated!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(EditGlycines.this, "Erro ao tentar editar o conteúdo!", Toast.LENGTH_SHORT).show();
                            }
                        });
                // [END update_document]
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
