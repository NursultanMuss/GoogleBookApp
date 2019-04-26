package com.example.googlebook;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    EditText entryWord;
    RecyclerView rv_result;
    Button searchButton;
    String query;
    String LOG_TAG = "Log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entryWord = (EditText) findViewById(R.id.edit_text);
        rv_result = (RecyclerView) findViewById(R.id.rv_result);
        searchButton = (Button) findViewById(R.id.search_btn);

        query = entryWord.getText().toString();
        getSupportLoaderManager().initLoader(0,null,this);
    }


    @NonNull
    @Override
    public Loader<List<Book>> onCreateLoader(int i, @Nullable Bundle bundle) {
        Log.d(LOG_TAG,"onCreateLoader is started");
        return new BooksLoader(this, query);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Book>> loader, List<Book> books) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Book>> loader) {

    }
}
