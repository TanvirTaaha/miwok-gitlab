package com.example.miwok;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        // Create a list of Words
        ArrayList<Word> wordArrayList = new ArrayList<Word>();

        wordArrayList.add(new Word("Where are you going?", "minto wuksus"));
        wordArrayList.add(new Word("What is your name?", "tinnә oyaase'nә"));
        wordArrayList.add(new Word("My name is...", "oyaaset..."));
        wordArrayList.add(new Word("How are you feeling?", "michәksәs?"));
        wordArrayList.add(new Word("I’m feeling good.", "kuchi achit"));
        wordArrayList.add(new Word("Are you coming?", "әәnәs'aa?"));
        wordArrayList.add(new Word("Yes, I’m coming.", "hәә’ әәnәm"));
        wordArrayList.add(new Word("I’m coming.", "әәnәm"));
        wordArrayList.add(new Word("Let’s go.", "yoowutis"));
        wordArrayList.add(new Word("Come here.", "әnni'nem"));

        WordAdapter itemsAdapter = new WordAdapter(this, wordArrayList, R.color.phrases_background);

        ListView listView = (ListView) findViewById(R.id.phrases_list);

        listView.setAdapter(itemsAdapter);
    }
}