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
        ArrayList<Word> wordArrayList = new ArrayList<>();

        wordArrayList.add(new Word(R.drawable.family_father, "father", "әpә", R.raw.family_father));
        wordArrayList.add(new Word(R.drawable.family_mother, "mother", "әṭa", R.raw.family_mother));
        wordArrayList.add(new Word(R.drawable.family_son, "son", "angsi", R.raw.family_son));
        wordArrayList.add(new Word(R.drawable.family_daughter, "daughter", "tune", R.raw.family_daughter));
        wordArrayList.add(new Word(R.drawable.family_older_brother, "older brother", "taachi", R.raw.family_older_brother));
        wordArrayList.add(new Word(R.drawable.family_younger_brother, "younger brother", "chalitti", R.raw.family_younger_brother));
        wordArrayList.add(new Word(R.drawable.family_older_sister, "older sister", "teṭe", R.raw.family_older_sister));
        wordArrayList.add(new Word(R.drawable.family_younger_sister, "younger sister", "kolliti", R.raw.family_younger_sister));
        wordArrayList.add(new Word(R.drawable.family_grandmother, "grandmother", "ama", R.raw.family_grandmother));
        wordArrayList.add(new Word(R.drawable.family_grandfather, "grandfather", "paapa", R.raw.family_grandfather));

        WordAdapter itemsAdapter = new WordAdapter(this, wordArrayList, R.color.family_background);

        ListView listView = (ListView) findViewById(R.id.family_list);

        listView.setAdapter(itemsAdapter);
    }
}