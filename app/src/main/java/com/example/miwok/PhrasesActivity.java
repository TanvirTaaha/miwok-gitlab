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
        ArrayList<Word> wordArrayList = new ArrayList<>();

        wordArrayList.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        wordArrayList.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        wordArrayList.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        wordArrayList.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        wordArrayList.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        wordArrayList.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        wordArrayList.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        wordArrayList.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        wordArrayList.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        wordArrayList.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        WordAdapter itemsAdapter = new WordAdapter(this, wordArrayList, R.color.phrases_background);

        ListView listView = (ListView) findViewById(R.id.phrases_list);

        listView.setAdapter(itemsAdapter);
    }
}