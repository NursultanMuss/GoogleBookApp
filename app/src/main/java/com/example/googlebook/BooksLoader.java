package com.example.googlebook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

public class BooksLoader extends AsyncTaskLoader<List<Book>> {
    String mUrl;
    public BooksLoader(@NonNull Context context, String url) {
        super(context);
        mUrl=url;
    }

    @Nullable
    @Override
    public ArrayList<Book> loadInBackground() {

        return NetworkUtils.fetchBooksData(mUrl);
    }
}
