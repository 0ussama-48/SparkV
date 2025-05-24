package com.example.sparkv_v1.CLIENTE.Actividades.Perfil;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sparkv_v1.CLIENTE.Clases.Direccion;
import com.example.sparkv_v1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class AgregarDireccionActivity extends AppCompatActivity {

    private EditText calleInput, numeroCasaInput, ciudadInput, codigoPostalInput;
    private Button guardarButton;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_direccion);

        // Inicializar Firebase
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // Referencias a los elementos del layout
        calleInput = findViewById(R.id.calleInput);
        numeroCasaInput = findViewById(R.id.numeroCasaInput);
        ciudadInput = findViewById(R.id.ciudadInput);
        codigoPostalInput = findViewById(R.id.codigoPostalInput);
        guardarButton = findViewById(R.id.guardarButton);

        // Acción del botón "Guardar"
        guardarButton.setOnClickListener(v -> {
            String calle = calleInput.getText().toString().trim();
            String numeroCasa = numeroCasaInput.getText().toString().trim();
            String ciudad = ciudadInput.getText().toString().trim();
            String codigoPostal = codigoPostalInput.getText().toString().trim();

            // Validar campos
            if (calle.isEmpty() || numeroCasa.isEmpty() || ciudad.isEmpty() || codigoPostal.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Verificar si el usuario está autenticado
            if (mAuth.getCurrentUser() == null) {
                Toast.makeText(this, "Debes iniciar sesión para agregar una dirección", Toast.LENGTH_SHORT).show();
                return;
            }

            // Desactivar el botón "Guardar" mientras se procesa la solicitud
            guardarButton.setEnabled(false);

            // Obtener el ID del usuario actual
            String userId = mAuth.getCurrentUser().getUid();

            // Generar un ID único para la dirección
            String direccionId = db.collection("users").document(userId).collection("direcciones").document().getId();

            // Crear el objeto Dirección
            Direccion direccion = new Direccion(direccionId, calle, numeroCasa, ciudad, codigoPostal);

            // Guardar la dirección en Firestore
            db.collection("users").document(userId).collection("direcciones")
                    .document(direccionId) // Usar el ID generado
                    .set(direccion)
                    .addOnSuccessListener(documentReference -> {
                        Log.d("AgregarDireccion", "Dirección guardada exitosamente");
                        Toast.makeText(this, "Dirección guardada exitosamente", Toast.LENGTH_SHORT).show();
                        finish(); // Regresar a la actividad anterior
                    })
                    .addOnFailureListener(e -> {
                        Log.e("AgregarDireccion", "Error al guardar la dirección: " + e.getMessage());
                        Toast.makeText(this, "Error al guardar la dirección", Toast.LENGTH_SHORT).show();
                        guardarButton.setEnabled(true); // Reactivar el botón en caso de error
                    });
        });
    }
}