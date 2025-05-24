package com.example.sparkv_v1.Helper;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class FirebaseUtil {
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Método para registrar un nuevo usuario
    public static void registerUser(String email, String password, Map<String, Object> userData, Context context) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            String userId = user.getUid();
                            db.collection("users").document(userId).set(userData)
                                    .addOnSuccessListener(aVoid -> {
                                        // Registro exitoso
                                    })
                                    .addOnFailureListener(e -> {
                                        // Manejar error
                                    });
                        }
                    } else {
                        // Manejar error
                    }
                });
    }

    // Método para iniciar sesión
    public static void loginUser(String email, String password, Context context) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Inicio de sesión exitoso
                    } else {
                        // Manejar error
                    }
                });
    }

    // Método para cerrar sesión
    public static void logoutUser() {
        mAuth.signOut();
    }

    // Método para obtener una referencia a una colección específica
    public static CollectionReference getCollectionReference(String collectionName) {
        return db.collection(collectionName);
    }

    // Método para guardar datos en una colección específica
    public static void saveData(String collectionName, String documentId, Map<String, Object> data) {
        db.collection(collectionName).document(documentId).set(data)
                .addOnSuccessListener(aVoid -> {
                    // Datos guardados exitosamente
                })
                .addOnFailureListener(e -> {
                    // Manejar error
                });
    }

    // Método para recuperar datos de una colección específica
    public static void fetchData(String collectionName, String documentId, OnDataFetchedListener listener) {
        db.collection(collectionName).document(documentId).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        listener.onDataFetched(documentSnapshot.getData());
                    } else {
                        listener.onDataFetched(null);
                    }
                })
                .addOnFailureListener(e -> {
                    listener.onError(e.getMessage());
                });
    }


    // Interfaz para manejar datos recuperados
    public interface OnDataFetchedListener {
        void onDataFetched(Map<String, Object> data);
        void onError(String errorMessage);
    }
}