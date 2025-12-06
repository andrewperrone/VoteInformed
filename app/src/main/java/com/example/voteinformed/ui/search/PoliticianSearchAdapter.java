package com.example.voteinformed.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voteinformed.R;
import com.example.voteinformed.data.entity.Politician;

import java.util.ArrayList;
import java.util.List;

public class PoliticianSearchAdapter extends RecyclerView.Adapter<PoliticianSearchAdapter.ViewHolder> {

    public interface OnPoliticianClick {
        void onClick(Politician p);
    }

    private final Context context;
    private final OnPoliticianClick listener;
    private List<Politician> items = new ArrayList<>();

    public PoliticianSearchAdapter(Context context, OnPoliticianClick listener) {
        this.context = context;
        this.listener = listener;
    }

    public void submitList(List<Politician> newItems) {
        items = newItems != null ? newItems : new ArrayList<>();
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView party;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.resultName);
            party = itemView.findViewById(R.id.resultParty);
        }

        void bind(Politician p, OnPoliticianClick listener) {
            name.setText(p.getPolitician_name());
            party.setText(p.getPolitician_party());

            itemView.setOnClickListener(v -> {
                if (listener != null) listener.onClick(p);
            });
        }
    }

    @NonNull
    @Override
    public PoliticianSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_search_result, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PoliticianSearchAdapter.ViewHolder holder, int position) {
        holder.bind(items.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
