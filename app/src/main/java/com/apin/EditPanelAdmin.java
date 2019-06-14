package com.apin;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.apin.fragments.admin.HomeAdmin;
import com.apin.fragments.admin.HomeMilho;
import com.apin.fragments.admin.HomeSoja;

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
                    getSupportActionBar().setTitle("Milho");
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
                    getSupportActionBar().setTitle("Soja");
                    Fragment hsja = HomeSoja.newInstance();
                    openFragment(hsja);
                    Toast.makeText(EditPanelAdmin.this, "Soja", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
    });

}
    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
