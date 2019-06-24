package com.apin;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Soja extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soja);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(Soja.this, Contato.class);
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
                intent.putExtra("tipo", "meloidogyneincognita");
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
