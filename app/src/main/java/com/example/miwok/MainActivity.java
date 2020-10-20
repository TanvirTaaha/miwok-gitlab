package com.example.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //Variables
    TextView numbersTextView;
    TextView familyMembersTextView;
    TextView colorsTextView;
    TextView phrasesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiation
        numbersTextView = findViewById(R.id.numbers);
        familyMembersTextView = findViewById(R.id.family_members);
        colorsTextView = findViewById(R.id.colors);
        phrasesTextView = findViewById(R.id.phrases);

        // register buttons to listen on click events
        numbersTextView.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, NumbersActivity.class)));
        familyMembersTextView.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, FamilyMembersActivity.class)));
        colorsTextView.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ColorsActivity.class)));
        phrasesTextView.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PhrasesActivity.class)));
    }
}