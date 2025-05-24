package com.example.sparkv_v1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth; // Autenticación de Firebase
    private FirebaseFirestore db; // Referencia a Firestore

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializar Firebase Auth y Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Referenciar elementos de la interfaz (ajustando los IDs)
        EditText nameInput = findViewById(R.id.usernameEditText); // Cambiado a "usernameEditText"
        EditText emailInput = findViewById(R.id.emailEditText); // Cambiado a "emailEditText"
        EditText phoneInput = findViewById(R.id.mobileNumberEditText); // Cambiado a "mobileNumberEditText"
        EditText passwordInput = findViewById(R.id.passwordEditText); // Cambiado a "passwordEditText"
        EditText confirmPasswordInput = findViewById(R.id.repeatPasswordEditText); // Cambiado a "repeatPasswordEditText"
        Button registerButton = findViewById(R.id.signupButton); // Cambiado a "signupButton"
        TextView loginLink = findViewById(R.id.loginLink); // Añadido para el enlace de inicio de sesión

        // Acción al hacer clic en el botón "Registrar"
        registerButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String phone = phoneInput.getText().toString().trim(); // Obtener teléfono
            String password = passwordInput.getText().toString().trim();
            String confirmPassword = confirmPasswordInput.getText().toString().trim();

            // Validar campos vacíos
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validar que las contraseñas coincidan
            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                return;
            }

            // Crear usuario en Firebase Authentication
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                String userId = user.getUid(); // UID del usuario registrado

                                // Datos adicionales a guardar en Firestore
                                Map<String, Object> userData = new HashMap<>();
                                userData.put("name", name); // Nombre del usuario
                                userData.put("email", email); // Correo electrónico
                                userData.put("phone", phone); // Teléfono
                                userData.put("role", "cliente"); // Rol predeterminado (puedes modificarlo según tu lógica)
                                userData.put("address", ""); // Dirección inicialmente vacía
                                userData.put("profileImage", null); // Imagen de perfil inicialmente nula

                                // Campos específicos según el rol
                                userData.put("favoriteCleaners", new ArrayList<>()); // Limpiadores favoritos vacíos

                                // Guardar los datos en Firestore
                                db.collection("users").document(userId).set(userData)
                                        .addOnSuccessListener(aVoid -> {
                                            Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                            finish();
                                        })
                                        .addOnFailureListener(e -> {
                                            Toast.makeText(RegisterActivity.this, "Error al guardar datos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        });
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registro fallido: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        // Acción al hacer clic en el enlace "¿Ya tienes una cuenta? Inicia sesión"
        loginLink.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }
}