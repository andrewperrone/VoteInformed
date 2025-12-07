package com.example.voteinformed.ui.saved;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.voteinformed.Article;
import com.example.voteinformed.R;
import com.example.voteinformed.data.repository.BookmarkRepository;

import java.util.ArrayList;
import java.util.List;

public class SavedArticleAdapter extends RecyclerView.Adapter<SavedArticleAdapter.ViewHolder> {
    private final List<Article> bookmarkedArticles = new ArrayList<>();
    private final BookmarkRepository bookmarkRepo;

    public SavedArticleAdapter(BookmarkRepository bookmarkRepo) {
        this.bookmarkRepo = bookmarkRepo;
    }

    public void updateBookmarks() {
        bookmarkedArticles.clear();
        bookmarkedArticles.addAll(bookmarkRepo.getBookmarkedArticles());
        notifyDataSetChanged();
        Log.d("SavedAdapter", "Updated " + bookmarkedArticles.size() + " articles");
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
        Article article = bookmarkedArticles.get(position);
        holder.bind(article);
    }

    @Override
    public int getItemCount() {
        return bookmarkedArticles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        ImageButton heartButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.savedImage);
            title = itemView.findViewById(R.id.savedTitle);
            heartButton = itemView.findViewById(R.id.btnSavedHeart);
        }

        void bind(Article article) {
            title.setText(article.title != null ? article.title : "No title");

            Glide.with(itemView.getContext())
                    .load(article.urlToImage)
                    .placeholder(android.R.color.white)
                    .error(android.R.color.white)
                    .into(image);

            heartButton.setImageResource(R.drawable.ic_heart_filled);

            heartButton.setOnClickListener(v -> {
                if (article.url != null) {
                    bookmarkRepo.removeBookmark(article.url);

                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        bookmarkedArticles.remove(position);
                        notifyItemRemoved(position);
                    }

                    Toast.makeText(itemView.getContext(), "Removed bookmark", Toast.LENGTH_SHORT).show();
                    Log.d("SavedAdapter", "Removed: " + article.url);
                }
            });

            itemView.setOnClickListener(v -> {
                if (article.url != null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.url));
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
