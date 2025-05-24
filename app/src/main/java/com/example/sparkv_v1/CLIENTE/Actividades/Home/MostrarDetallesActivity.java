package com.example.sparkv_v1.CLIENTE.Actividades.Home;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.example.sparkv_v1.CLIENTE.Clases.CarritoItem;
import com.example.sparkv_v1.CLIENTE.Adaptadores.PopularDomain;
import com.example.sparkv_v1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MostrarDetallesActivity extends AppCompatActivity {
    private TextView tituloTxt, precioTxt, descripcionTxt, categoriaTxt, duracionTxt, detallesAdicionalesTxt;
    private TextView numeroDeOrdenTxt, fechaSeleccionada, horaSeleccionada;
    private ImageView plusBtn, minusBtn, fechaBtn, horaBtn, picService;
    private Button anadirAlCarroBtn;
    private int numeroOrden = 1;
    private String fechaSeleccionadaStr = "Selecciona una fecha";
    private String horaSeleccionadaStr = "Selecciona una hora";
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private PopularDomain item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_detalles);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Inicializar vistas
        tituloTxt = findViewById(R.id.tituloTxt);
        precioTxt = findViewById(R.id.precioTxt);
        descripcionTxt = findViewById(R.id.descripcionTxt);
        categoriaTxt = findViewById(R.id.categoriaTxt);
        duracionTxt = findViewById(R.id.duracionTxt);
        detallesAdicionalesTxt = findViewById(R.id.detallesAdicionalesTxt);
        numeroDeOrdenTxt = findViewById(R.id.numeroDeOrdenTxt);
        fechaSeleccionada = findViewById(R.id.fechaSeleccionada);
        horaSeleccionada = findViewById(R.id.horaSeleccionada);
        fechaBtn = findViewById(R.id.fechaBtn);
        horaBtn = findViewById(R.id.horaBtn);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picService = findViewById(R.id.picService);
        anadirAlCarroBtn = findViewById(R.id.anadirAlCarroBtn);

        // Cargar datos del servicio
        item = (PopularDomain) getIntent().getSerializableExtra("object");
        if (item == null) {
            Toast.makeText(this, "Error: No se recibió el servicio", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Asignar datos al layout
        tituloTxt.setText(item.getTitle());
        precioTxt.setText("€" + item.getFee());
        descripcionTxt.setText(item.getDescripcion());
        categoriaTxt.setText("Categoría: " + item.getCategoria());
        duracionTxt.setText("Duración: " + item.getDuracion());
        detallesAdicionalesTxt.setText("Detalles: " + item.getDetallesAdicionales());

        // Cargar imagen con Glide
        String resourceName = item.getPic();
        if (resourceName != null && !resourceName.isEmpty()) {
            int drawableResourceId = getResources().getIdentifier(
                    resourceName,
                    "drawable",
                    getPackageName()
            );
            Glide.with(this).load(drawableResourceId).into(picService);
        }

        // Configurar botones de incremento/decremento
        plusBtn.setOnClickListener(v -> {
            numeroOrden++;
            numeroDeOrdenTxt.setText(String.valueOf(numeroOrden));
        });

        minusBtn.setOnClickListener(v -> {
            if (numeroOrden > 1) {
                numeroOrden--;
                numeroDeOrdenTxt.setText(String.valueOf(numeroOrden));
            }
        });

// Configurar selector de fecha
        fechaBtn.setOnClickListener(v -> {
            MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Seleccionar fecha")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build();

            datePicker.addOnPositiveButtonClickListener(selection -> {
                long selectedDate = datePicker.getSelection();
                String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date(selectedDate));
                fechaSeleccionadaStr = formattedDate; // Asegúrate de actualizar la variable
                fechaSeleccionada.setText(formattedDate);
            });

            getSupportFragmentManager().beginTransaction().add(datePicker, "DATE_PICKER").commit();
        });
        // Selector de hora
        horaBtn.setOnClickListener(v -> {
            MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(10)
                    .setMinute(30)
                    .build();

            timePicker.addOnPositiveButtonClickListener(v1 -> {
                horaSeleccionadaStr = String.format("%02d:%02d", timePicker.getHour(), timePicker.getMinute());
                horaSeleccionada.setText(horaSeleccionadaStr);
            });

            timePicker.show(getSupportFragmentManager(), "TIME_PICKER");
        });

        // Agregar al carrito
        anadirAlCarroBtn.setOnClickListener(v -> {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser == null) {
                Toast.makeText(this, "Debes iniciar sesión", Toast.LENGTH_SHORT).show();
                return;
            }

            if (item == null) {
                Toast.makeText(this, "Error: No se recibió el servicio", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            db.collection("users").document(currentUser.getUid()).collection("carrito")
                    .add(new CarritoItem(
                            item.getTitle(),
                            item.getFee(),
                            numeroOrden,
                            item.getCategoria(),
                            item.getDuracion(),
                            item.getDetallesAdicionales(),
                            fechaSeleccionadaStr, // Asegúrate de que se pasa correctamente
                            horaSeleccionadaStr,
                            item.getServicioId()
                    ))
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(this, "Servicio añadido al carrito", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Error al añadir al carrito: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("Firestore", "Error al guardar fecha: ", e);
                    });
        });
    }
}