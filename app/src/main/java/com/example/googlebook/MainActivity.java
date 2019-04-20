package com.example.googlebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText entryWord;
    TextView resultText;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entryWord = (EditText) findViewById(R.id.edit_text);
        resultText = (TextView) findViewById(R.id.result);
        searchButton = (Button) findViewById(R.id.search_btn);

        String queryString = entryWord.getText().toString();

    }
}
