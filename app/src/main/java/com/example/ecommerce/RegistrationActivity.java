package com.example.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {

    EditText name, email, password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Objects.requireNonNull(getSupportActionBar()).hide();

        auth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void signup(View view) {
        String userName = name.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();


        if (TextUtils.isEmpty(userName)){
            Toast.makeText(this, "Ingrese su nombre", Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Introduce tu correo", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)){
            Toast.makeText(this, "Ingrese su contraseña", Toast.LENGTH_SHORT).show();
            return;

        }
        if(userPassword.length()<6){
            Toast.makeText(this, "Contraseña muy corta, ingrese 6 caracteres como mínimo ", Toast.LENGTH_SHORT).show();
            return;
           }
        auth.createUserWithEmailAndPassword(userEmail,userPassword)
                        .addOnCompleteListener(RegistrationActivity.this, task -> {

                            if (task.isSuccessful()){
                                Toast.makeText(RegistrationActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                            }else {
                                Toast.makeText(RegistrationActivity.this, "Registro fallido"+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    public void singin(View view) {

    }
}