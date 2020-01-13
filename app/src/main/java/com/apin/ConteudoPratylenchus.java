package com.apin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.apin.database.FirebaseConfig;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ConteudoPratylenchus extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private String[] imageUrls = new String[]{

            //Soja
            "https://i.ibb.co/d6322Qp/parte-a-rea-3.jpg",
            "https://i.ibb.co/d6322Qp/parte-a-rea-4.jpg",
            "https://i.ibb.co/YQfhZLn/parte-a-rea-6.jpg",
            "https://i.ibb.co/hHsm1mM/parte-a-rea-7.jpg",
            "https://i.ibb.co/0qnFhFM/parte-a-rea-9.jpg",
            "https://i.ibb.co/2sYfD92/raiz.jpg"
    };
    TextView titulo;
    TextView conteudo;
    WebView wb;
    FirebaseFirestore db = FirebaseConfig.getFirebaseFirestore();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo_pratylenchus);
        Toolbar toolbar = findViewById(R.id.toolbar);

        ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, imageUrls);
        viewPager.setAdapter(adapter);

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
        String tituloIntent = intent.getStringExtra("titulo").substring(13,23);

        titulo = findViewById(R.id.titulo);
        conteudo = findViewById(R.id.conteudo);
        wb = findViewById(R.id.webViewPraty);


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
                    wb.getSettings().setJavaScriptEnabled(true);
                    wb.loadDataWithBaseURL("", fields.toString(), "text/html",
                            "UTF-8", "");
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