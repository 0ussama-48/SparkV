package com.example.sparkv_v1.CLIENTE.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sparkv_v1.R;
import com.example.sparkv_v1.CLIENTE.Clases.Tarea;

import java.util.ArrayList;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaViewHolder> {

    private Context context;
    private ArrayList<Tarea> listaTareas;

    // Constructor
    public TareaAdapter(Context context, ArrayList<Tarea> listaTareas) {
        this.context = context;
        this.listaTareas = listaTareas;
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tarea, parent, false);
        return new TareaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
        Tarea tarea = listaTareas.get(position);

        // Vincular los datos con los elementos de la vista
        holder.nombreCliente.setText(tarea.getNombreCliente());
        holder.direccion.setText(tarea.getDireccion());
        holder.estado.setText("Estado: " + tarea.getEstado());
    }

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    // ViewHolder
    public static class TareaViewHolder extends RecyclerView.ViewHolder {
        TextView nombreCliente, direccion, estado;

        public TareaViewHolder(@NonNull View itemView) {
            super(itemView);

            // Enlazar los elementos del layout
            nombreCliente = itemView.findViewById(R.id.nombreCliente);
            direccion = itemView.findViewById(R.id.direccion);
            estado = itemView.findViewById(R.id.estado);
        }
    }
}