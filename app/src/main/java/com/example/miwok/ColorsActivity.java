package com.example.miwok;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        // Create a list of Words
        ArrayList<Word> wordArrayList = new ArrayList<>();

        wordArrayList.add(new Word(R.drawable.color_red,"red", "weṭeṭṭi", R.raw.color_red));
        wordArrayList.add(new Word(R.drawable.color_green, "green", "chokokki", R.raw.color_green));
        wordArrayList.add(new Word(R.drawable.color_brown, "brown", "ṭakaakki", R.raw.color_brown));
        wordArrayList.add(new Word(R.drawable.color_gray, "gray", "ṭopoppi", R.raw.color_gray));
        wordArrayList.add(new Word(R.drawable.color_black, "black", "kululli", R.raw.color_black));
        wordArrayList.add(new Word(R.drawable.color_white, "white", "kelelli", R.raw.color_white));
        wordArrayList.add(new Word(R.drawable.color_dusty_yellow, "dusty yellow", "ṭopiisә", R.raw.color_dusty_yellow));
        wordArrayList.add(new Word(R.drawable.color_mustard_yellow, "mustard yellow", "chiwiiṭә", R.raw.color_mustard_yellow));


        WordAdapter itemsAdapter = new WordAdapter(this, wordArrayList, R.color.colors_background);

        ListView listView = (ListView) findViewById(R.id.color_list);

        listView.setAdapter(itemsAdapter);
    }
}