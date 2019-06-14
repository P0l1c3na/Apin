package com.apin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AddAdmin extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText mail, pass;
    private ProgressBar progressBar;

    private Button mRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        pass = findViewById(R.id.passwordsi);
        mail = findViewById(R.id.usernamesi);
        mRegistration = (Button) findViewById(R.id.cadastrar);
        progressBar = (ProgressBar) findViewById(R.id.loading);
        mAuth = FirebaseAuth.getInstance();

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateForm()) {
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                String email = mail.getText().toString();
                String password = pass.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(AddAdmin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(AddAdmin.this, "Erro ao cadastrar!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddAdmin.this, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddAdmin.this, EditPanelAdmin.class);
                            //
                            startActivity(intent);
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }

        });
        //Fim onCreate
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mail.setError("O campo de email está vazio!");
            valid = false;
        } else {
            mail.setError(null);
        }

        String password = pass.getText().toString();
        if (TextUtils.isEmpty(password)) {
            pass.setError("O campo de senha está vazio!");
            valid = false;
        } else {
            pass.setError(null);
        }

        return valid;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}