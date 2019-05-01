package com.example.googlebook;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Book>> {
    EditText entryWord;
    RecyclerView rv_result;
    Button searchButton;
    String query;
    String LOG_TAG = "Log";
    BooksAdapter bAdapter;

    private static final String GOOGL_BOOK_BASE_URI = "https://www.googleapis.com/books/v1/volumes?";
    private static final String QUERY_PARAM = "q";
    private static final String MAX_RESULT = "maxResults";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entryWord = (EditText) findViewById(R.id.edit_text);
        rv_result = (RecyclerView) findViewById(R.id.rv_result);
        searchButton = (Button) findViewById(R.id.search_btn);


    }


    @NonNull
    @Override
    public Loader<ArrayList<Book>> onCreateLoader(int i, @Nullable Bundle bundle) {
        Log.d(LOG_TAG,"onCreateLoader is started");

        Uri baseUri = Uri.parse(GOOGL_BOOK_BASE_URI)
                .buildUpon()
                .appendQueryParameter(QUERY_PARAM, query)
                .appendQueryParameter(MAX_RESULT, "20")
                .build();
        Log.d("Log", baseUri.toString());
        return new BooksLoader(this, baseUri.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Book>> loader, ArrayList<Book> books) {
        RecyclerView rcview = (RecyclerView) findViewById(R.id.rv_result);
        bAdapter = new BooksAdapter(books);
        rcview.setAdapter(bAdapter);
        rcview.setLayoutManager(new GridLayoutManager(this,3));
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Book>> loader) {

    }
    public void onSearchStarted(View v){
        query = entryWord.getText().toString();
        rv_result.setVisibility(View.VISIBLE);
        searchButton.setVisibility(View.GONE);
        entryWord.setVisibility(View.GONE);
        Log.d("Log", query);
        getSupportLoaderManager().initLoader(0,null,this);
    }
}
