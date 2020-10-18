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
        ArrayList<Word> wordArrayList = new ArrayList<Word>();

        wordArrayList.add(new Word(R.drawable.color_red,"red", "weṭeṭṭi"));
        wordArrayList.add(new Word(R.drawable.color_green, "green", "chokokki"));
        wordArrayList.add(new Word(R.drawable.color_brown, "brown", "ṭakaakki"));
        wordArrayList.add(new Word(R.drawable.color_gray, "gray", "ṭopoppi"));
        wordArrayList.add(new Word(R.drawable.color_black, "black", "kululli"));
        wordArrayList.add(new Word(R.drawable.color_white, "white", "kelelli"));
        wordArrayList.add(new Word(R.drawable.color_dusty_yellow, "dusty yellow", "ṭopiisә"));
        wordArrayList.add(new Word(R.drawable.color_mustard_yellow, "mustard yellow", "chiwiiṭә"));


        WordAdapter itemsAdapter = new WordAdapter(this, wordArrayList, R.color.colors_background);

        ListView listView = (ListView) findViewById(R.id.color_list);

        listView.setAdapter(itemsAdapter);
    }
}