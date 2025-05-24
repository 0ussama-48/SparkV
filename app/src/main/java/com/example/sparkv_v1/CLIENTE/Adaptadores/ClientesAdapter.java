package com.example.sparkv_v1.CLIENTE.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sparkv_v1.R;
import com.example.sparkv_v1.CLIENTE.Clases.Cliente;

import java.util.ArrayList;

public class ClientesAdapter extends RecyclerView.Adapter<ClientesAdapter.ClienteViewHolder> {

    private ArrayList<Cliente> clientes;

    public ClientesAdapter(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cliente, parent, false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        Cliente cliente = clientes.get(position);
        holder.nombre.setText(cliente.getNombre());
        holder.correo.setText(cliente.getCorreo());
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    public static class ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, correo;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombreCliente);
            correo = itemView.findViewById(R.id.correoCliente);
        }
    }
}
