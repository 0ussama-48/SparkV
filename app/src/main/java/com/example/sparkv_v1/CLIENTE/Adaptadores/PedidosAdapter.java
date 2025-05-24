package com.example.sparkv_v1.CLIENTE.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sparkv_v1.CLIENTE.Clases.Pedido;
import com.example.sparkv_v1.R;

import java.util.ArrayList;


public class PedidosAdapter extends RecyclerView.Adapter<PedidosAdapter.PedidoViewHolder> {

    private Context context;
    private ArrayList<Pedido> listaPedidos;

    public PedidosAdapter(Context context, ArrayList<Pedido> listaPedidos) {
        this.context = context;
        this.listaPedidos = listaPedidos;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pedido, parent, false);
        return new PedidoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {
        Pedido pedido = listaPedidos.get(position);

        holder.idPedido.setText("ID: " + pedido.getId());
        holder.estado.setText("Estado: " + pedido.getEstado());
        holder.total.setText("Total: $" + pedido.getTotal());
        holder.metodoPago.setText("Método de Pago: " + pedido.getMetodoPago());
    }

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }

    public static class PedidoViewHolder extends RecyclerView.ViewHolder {
        TextView idPedido, estado, total, metodoPago;

        public PedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            idPedido = itemView.findViewById(R.id.idPedido);
            estado = itemView.findViewById(R.id.estadoPedido);
            total = itemView.findViewById(R.id.totalPedido);
            metodoPago = itemView.findViewById(R.id.metodoPagoPedido);
        }
    }
}