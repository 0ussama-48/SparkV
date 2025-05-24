package com.example.sparkv_v1.ADMIN.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sparkv_v1.ADMIN.Clases.Cleaner;
import com.example.sparkv_v1.R;

import java.util.List;

public class CleanerAdapter extends RecyclerView.Adapter<CleanerAdapter.ViewHolder> {
    private final List<Cleaner> cleaners;
    private OnItemSelectedListener listener;

    public CleanerAdapter(List<Cleaner> cleaners) {
        this.cleaners = cleaners;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cleaner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cleaner cleaner = cleaners.get(position);
        holder.email.setText(cleaner.getEmail());
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemSelected(cleaner.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cleaners.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.tvCleanerEmail);
        }
    }

    public interface OnItemSelectedListener {
        void onItemSelected(String cleanerId);
    }
}