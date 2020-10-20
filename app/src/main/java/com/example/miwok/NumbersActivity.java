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
        ArrayList<Word> wordArrayList = new ArrayList<>();

        wordArrayList.add(new Word(R.drawable.number_one, "one", "lutti", R.raw.number_one));
        wordArrayList.add(new Word(R.drawable.number_two, "two", "otiiko", R.raw.number_two));
        wordArrayList.add(new Word(R.drawable.number_three, "three", "tolookosu", R.raw.number_three));
        wordArrayList.add(new Word(R.drawable.number_four, "four", "oyyisa", R.raw.number_four));
        wordArrayList.add(new Word(R.drawable.number_five, "five", "massokka", R.raw.number_five));
        wordArrayList.add(new Word(R.drawable.number_six, "six", "temmokka", R.raw.number_six));
        wordArrayList.add(new Word(R.drawable.number_seven, "seven", "kenekaku", R.raw.number_seven));
        wordArrayList.add(new Word(R.drawable.number_eight, "eight", "kawinta", R.raw.number_eight));
        wordArrayList.add(new Word(R.drawable.number_nine, "nine", "wo'e", R.raw.number_nine));
        wordArrayList.add(new Word(R.drawable.number_ten, "ten", "na'aacha", R.raw.number_ten));

        WordAdapter itemsAdapter = new WordAdapter(this, wordArrayList, R.color.numbers_background);

        ListView listView = (ListView) findViewById(R.id.number_list);

        listView.setAdapter(itemsAdapter);


    }
}
