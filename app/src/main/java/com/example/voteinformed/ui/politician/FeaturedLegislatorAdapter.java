package com.example.voteinformed.ui.politician;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.voteinformed.R;
import com.example.voteinformed.network.LegislatorResponse;

import java.util.ArrayList;
import java.util.List;

public class FeaturedLegislatorAdapter
        extends RecyclerView.Adapter<FeaturedLegislatorAdapter.ViewHolder> {

    private List<LegislatorResponse.Member> members = new ArrayList<>();

    public void setMembers(List<LegislatorResponse.Member> list) {
        members = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_politician_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LegislatorResponse.Member m = members.get(position);

        holder.name.setText(m.memberFullName);
        holder.party.setText(m.party);
        holder.bio.setText(m.shortDescription);

        Glide.with(holder.itemView.getContext())
                .load(R.drawable.user)
                .circleCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, party, bio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.politician_image);
            name = itemView.findViewById(R.id.politician_name);
            party = itemView.findViewById(R.id.politician_position);
            bio = itemView.findViewById(R.id.politician_bio);
        }
    }
}
