package com.example.miwok;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FamilyMembersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_members);

        // Create a list of Words
        ArrayList<Word> wordArrayList = new ArrayList<Word>();

        wordArrayList.add(new Word(R.drawable.family_father, "father", "әpә"));
        wordArrayList.add(new Word(R.drawable.family_mother, "mother", "әṭa"));
        wordArrayList.add(new Word(R.drawable.family_son, "son", "angsi"));
        wordArrayList.add(new Word(R.drawable.family_daughter, "daughter", "tune"));
        wordArrayList.add(new Word(R.drawable.family_older_brother, "older brother", "taachi"));
        wordArrayList.add(new Word(R.drawable.family_younger_brother, "younger brother", "chalitti"));
        wordArrayList.add(new Word(R.drawable.family_older_sister, "older sister", "teṭe"));
        wordArrayList.add(new Word(R.drawable.family_younger_sister, "younger sister", "kolliti"));
        wordArrayList.add(new Word(R.drawable.family_grandmother, "grandmother", "ama"));
        wordArrayList.add(new Word(R.drawable.family_grandfather, "grandfather", "paapa"));

        WordAdapter itemsAdapter = new WordAdapter(this, wordArrayList, R.color.family_background);

        ListView listView = (ListView) findViewById(R.id.family_list);

        listView.setAdapter(itemsAdapter);
    }
}