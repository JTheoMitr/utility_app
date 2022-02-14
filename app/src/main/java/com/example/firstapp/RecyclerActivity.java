package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class RecyclerActivity extends AppCompatActivity {
    String[] languages = new String[] {"English","Hindi","French","Arabic",
            "Haitian Creole","Spanish","Afrikaans","Bengali", "German","Japanese","Danish","Swahili",
            "Mandarin","Turkish","Cantonese","Chichewa"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        RecyclerView lRecyclerView = findViewById(R.id.lrecycler_view);
        LanguageAdapter adapter = new LanguageAdapter(languages);
        lRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        lRecyclerView.setAdapter(adapter);

    }
}