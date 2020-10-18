package com.example.miwok;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        // Create a list of Words
        ArrayList<Word> wordArrayList = new ArrayList<Word>();

        wordArrayList.add(new Word(R.drawable.number_one, "one", "lutti"));
        wordArrayList.add(new Word(R.drawable.number_two, "two", "otiiko"));
        wordArrayList.add(new Word(R.drawable.number_three, "three", "tolookosu"));
        wordArrayList.add(new Word(R.drawable.number_four, "four", "oyyisa"));
        wordArrayList.add(new Word(R.drawable.number_five, "five", "massokka"));
        wordArrayList.add(new Word(R.drawable.number_six, "six", "temmokka"));
        wordArrayList.add(new Word(R.drawable.number_seven, "seven", "kenekaku"));
        wordArrayList.add(new Word(R.drawable.number_eight, "eight", "kawinta"));
        wordArrayList.add(new Word(R.drawable.number_nine, "nine", "wo'e"));
        wordArrayList.add(new Word(R.drawable.number_ten, "ten", "na'aacha"));

        WordAdapter itemsAdapter = new WordAdapter(this, wordArrayList, R.color.numbers_background);

        ListView listView = (ListView) findViewById(R.id.number_list);

        listView.setAdapter(itemsAdapter);


    }
}
