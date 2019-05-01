package com.example.googlebook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Nurs on 29.04.2019.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {
    ArrayList<Book> a_books;
    public BooksAdapter(ArrayList<Book> books){
        a_books = books;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View bookView = inflater.inflate(R.layout.item_book,parent,false);
        ViewHolder viewHolder = new ViewHolder(bookView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Book book = a_books.get(position);

        ImageView imageView = viewHolder.bookImageView;
        Picasso.get().load(book.getImgLink()).into(imageView);

        RatingBar ratingBar = viewHolder.ratingBar;
        ratingBar.setRating(book.getAvrRating());

        TextView ratingCount = viewHolder.ratingCount;
        ratingCount.setText(String.valueOf(book.getRatingCount()));

        TextView author = viewHolder.author;
        author.setText(book.getAuthor());

        TextView bookName = viewHolder.bookName;
        bookName.setText(book.getTitle());

    }

    @Override
    public int getItemCount() {
        Log.d("Log", Integer.toString(a_books.size()));
        return a_books.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView bookImageView;
        public RatingBar ratingBar;
        public TextView ratingCount;
        public TextView author;
        public TextView bookName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImageView = (ImageView) itemView.findViewById(R.id.bookImageView);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            ratingCount = (TextView) itemView.findViewById(R.id.ratingCount);
            author = (TextView) itemView.findViewById(R.id.author);
            bookName = (TextView) itemView.findViewById(R.id.bookName);
        }
    }
}
