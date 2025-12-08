package com.example.voteinformed.ui.elections;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voteinformed.R;
import com.example.voteinformed.model.ElectionModel;
import java.util.List;

public class ElectionsAdapter extends RecyclerView.Adapter<ElectionsAdapter.ElectionViewHolder> {

    private final List<ElectionModel> electionsList;

    public ElectionsAdapter(List<ElectionModel> electionsList) {
        this.electionsList = electionsList;
    }

    @NonNull
    @Override
    public ElectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_election, parent, false);
        return new ElectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ElectionViewHolder holder, int position) {
        ElectionModel election = electionsList.get(position);

        holder.nameTextView.setText(election.getName());
        holder.dateTextView.setText(
                String.format("Date: %s", election.getElectionDay())
        );
        holder.idTextView.setText(election.getId()); // Hidden ID

        holder.itemView.setOnClickListener(v -> {
            String message = String.format("Selected Election: %s (ID: %s)",
                    election.getName(),
                    election.getId());
            Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    public int getItemCount() {
        return electionsList.size();
    }

    static class ElectionViewHolder extends RecyclerView.ViewHolder {
        final TextView nameTextView;
        final TextView dateTextView;
        final TextView idTextView;

        ElectionViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_election_name);
            dateTextView = itemView.findViewById(R.id.tv_election_date);
            idTextView = itemView.findViewById(R.id.tv_election_id);
        }
    }

    public void updateElections(List<ElectionModel> newElections) {
        electionsList.clear();
        electionsList.addAll(newElections);
        notifyDataSetChanged();
    }
}