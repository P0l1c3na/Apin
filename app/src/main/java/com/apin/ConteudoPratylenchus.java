package com.apin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.apin.database.FirebaseConfig;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ConteudoPratylenchus extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    TextView titulo;
    TextView conteudo;

    FirebaseFirestore db = FirebaseConfig.getFirebaseFirestore();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo_pratylenchus);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(ConteudoPratylenchus.this, Contato.class);
                startActivity(c);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // INICIO
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_milho) {
            Intent intent = new Intent(this, Milho.class);
            startActivity(intent);
        } else if (id == R.id.nav_soja) {
            Intent intent = new Intent(this, Soja.class);
            startActivity(intent);
        } else if (id == R.id.nav_wheater) {
            Intent wheater = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cptec.inpe.br/"));
            startActivity(wheater);
        } else if (id == R.id.nav_adm) {
            Intent login = new Intent(this, AdminLogin.class);
            startActivity(login);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}