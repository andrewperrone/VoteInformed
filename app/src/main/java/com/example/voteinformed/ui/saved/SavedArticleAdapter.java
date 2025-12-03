package com.example.voteinformed.ui.saved;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.voteinformed.R;
import com.example.voteinformed.data.entity.SavedArticle;

public class SavedArticleAdapter extends ListAdapter<SavedArticle, SavedArticleAdapter.ViewHolder> {

    public SavedArticleAdapter() {
        super(new DiffUtil.ItemCallback<SavedArticle>() {
            @Override
            public boolean areItemsTheSame(@NonNull SavedArticle oldItem, @NonNull SavedArticle newItem) {
                return oldItem.articleId.equals(newItem.articleId);
            }

            @Override
            public boolean areContentsTheSame(@NonNull SavedArticle oldItem, @NonNull SavedArticle newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.savedImage);
            title = itemView.findViewById(R.id.savedTitle);
        }

        void bind(SavedArticle item) {
            title.setText(item.title);

            Glide.with(itemView.getContext())
                    .load(item.imageUrl)
                    .placeholder(R.color.app_background)
                    .into(image);

            // 카드 클릭 시 기사 링크 열기 (articleId를 url로 사용)
            itemView.setOnClickListener(v -> {
                if (item.articleId != null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.articleId));
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_saved_article, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
