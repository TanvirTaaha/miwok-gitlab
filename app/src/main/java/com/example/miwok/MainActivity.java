package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
        numbersTextView.setOnClickListener(this);
        familyMembersTextView.setOnClickListener(this);
        colorsTextView.setOnClickListener(this);
        phrasesTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.numbers:
//                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
//                Log.d("Main to Numbers", "onClick: before startActivity");
                startActivity(new Intent(MainActivity.this, NumbersActivity.class));
//
//                Log.d("Main to Numbers", "onClick: after startActivity");
                break;
            case R.id.family_members:
                startActivity(new Intent(MainActivity.this, FamilyMembersActivity.class));
                break;
            case R.id.colors:
                startActivity(new Intent(MainActivity.this, ColorsActivity.class));
                break;
            case R.id.phrases:
                startActivity(new Intent(MainActivity.this, PhrasesActivity.class));
                break;
        }
    }
}