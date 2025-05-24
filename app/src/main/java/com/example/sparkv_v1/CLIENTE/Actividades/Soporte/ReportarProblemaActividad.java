package com.example.sparkv_v1.CLIENTE.Actividades.Soporte;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sparkv_v1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ReportarProblemaActividad extends AppCompatActivity {

    private EditText campoDescripcion;
    private Button botonEnviar;

    // Referencias a Firestore y Firebase Auth
    private FirebaseFirestore firestore;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_problema);

        // Inicializar Firestore y Firebase Auth
        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // Referencia al botón de Volver
        View botonVolver = findViewById(R.id.botonVolver);
        botonVolver.setOnClickListener(view -> finish()); // Cierra la actividad actual y vuelve a la anterior

        // Referencias a los elementos de la interfaz
        campoDescripcion = findViewById(R.id.campoDescripcion);
        botonEnviar = findViewById(R.id.botonEnviar);

        // Configurar el botón Enviar
        botonEnviar.setOnClickListener(view -> guardarReporteEnFirestore());
    }

    private void guardarReporteEnFirestore() {
        String descripcion = campoDescripcion.getText().toString().trim();

        // Validar que la descripción no esté vacía
        if (descripcion.isEmpty()) {
            Toast.makeText(this, "Por favor, describe el problema.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar si el usuario está autenticado
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Toast.makeText(this, "Debes iniciar sesión para enviar un reporte.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener el ID del usuario autenticado
        String userId = user.getUid();

        // Leer el nombre del usuario desde Firestore
        firestore.collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        // Obtener el nombre del usuario desde Firestore
                        String userName = documentSnapshot.getString("name");
                        if (userName == null || userName.isEmpty()) {
                            userName = "Usuario Anónimo"; // Valor predeterminado si no hay nombre
                        }

                        // Formatear la fecha actual
                        String fechaFormateada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(new Date());

                        // Crear un mapa con los datos del reporte
                        Map<String, Object> reporte = new HashMap<>();
                        reporte.put("descripcion", descripcion);
                        reporte.put("fecha", fechaFormateada); // Fecha legible
                        reporte.put("userId", userId); // ID del usuario
                        reporte.put("userName", userName); // Nombre del usuario

                        // Guardar el reporte en Firestore
                        firestore.collection("reportes_problemas")
                                .add(reporte)
                                .addOnSuccessListener(documentReference -> {
                                    Toast.makeText(this, "Reporte enviado correctamente.", Toast.LENGTH_SHORT).show();
                                    campoDescripcion.setText(""); // Limpiar el campo
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(this, "Error al enviar el reporte. Inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace(); // Imprimir el error en los logs
                                });
                    } else {
                        Toast.makeText(this, "No se encontraron datos del usuario.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al obtener los datos del usuario.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace(); // Imprimir el error en los logs
                });
    }
}