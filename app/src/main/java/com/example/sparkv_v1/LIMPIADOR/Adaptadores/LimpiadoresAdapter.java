package com.example.sparkv_v1.LIMPIADOR.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sparkv_v1.LIMPIADOR.Clases.Limpiador;
import com.example.sparkv_v1.R;

import java.util.ArrayList;

public class LimpiadoresAdapter extends RecyclerView.Adapter<LimpiadoresAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Limpiador> listaLimpiadores;
    private OnItemSelectedListener listener;

    public interface OnItemSelectedListener {
        void onItemSelected(String cleanerId);
    }

    public LimpiadoresAdapter(Context context, ArrayList<Limpiador> listaLimpiadores, OnItemSelectedListener listener) {
        this.context = context;
        this.listaLimpiadores = listaLimpiadores;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_limpiador, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Limpiador limpiador = listaLimpiadores.get(position);
        holder.email.setText(limpiador.getEmail());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemSelected(limpiador.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaLimpiadores.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.tvLimpiadorEmail); // Asegúrate de tener este ID en item_limpiador.xml
        }
    }
}