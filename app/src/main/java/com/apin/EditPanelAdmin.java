package com.apin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.apin.fragments.admin.HomeAdmin;
import com.apin.fragments.admin.HomeMilho;
import com.apin.fragments.admin.HomeSoja;
import com.google.firebase.auth.FirebaseAuth;

public class EditPanelAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_panel_admin);
        Fragment adm = HomeAdmin.newInstance();
        openFragment(adm);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_milho:
                        getSupportActionBar().setTitle("Generos");
                        Fragment hm = HomeMilho.newInstance();
                        openFragment(hm);
                        Toast.makeText(EditPanelAdmin.this, "Milho", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_home:
                        getSupportActionBar().setTitle("Home");
                        Fragment adm = HomeAdmin.newInstance();
                        openFragment(adm);
                        Toast.makeText(EditPanelAdmin.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_soja:
                        getSupportActionBar().setTitle("Generos");
                        Fragment hsja = HomeSoja.newInstance();
                        openFragment(hsja);
                        Toast.makeText(EditPanelAdmin.this, "Soja", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeSoja/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_inicio) {
            Intent inicio = new Intent(this, MainActivity.class);
            startActivity(inicio);
        }else if (id == R.id.action_addAdmin) {
            Intent addAdmin = new Intent(this, AddAdmin.class);
            startActivity(addAdmin);
        }else if (id == R.id.action_Logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(EditPanelAdmin.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
