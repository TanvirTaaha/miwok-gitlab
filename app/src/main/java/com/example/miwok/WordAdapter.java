package com.example.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

/*
 * {@link WordAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link Word} objects.
 * */
public class WordAdapter extends ArrayAdapter<Word> {

    /**
     * This refers to the item colors for each category
     */
    private final int mCategoryColorResourceId;

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param words A List of Word objects to display in a list
     */
    public WordAdapter(Context context, ArrayList<Word> words, int categoryColorResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        this.mCategoryColorResourceId = categoryColorResourceId;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView;
        Word currentWord = this.getItem(position);

        // Check if the existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.my_list_item_1, parent, false);
        } else {
            itemView = convertView;
        }

        /*
          Find the first TextView and miwokTranslation of the item of position position and populate the TextView
         */
        ((TextView) itemView.findViewById(R.id.item_text_1)).setText(currentWord.getMiwokTranslation());

        /*
          Find the second TextView and englishTranslation of the item of position position and populate the TextView
         */
        ((TextView) itemView.findViewById(R.id.item_text_2)).setText(currentWord.getEnglishTranslation());

        /*
          Find the ImageView and iconImageId of the item of position position and populate the ImageView
          If no ImageResource has passed to then make the ImageView invisible
         */
        if (this.getItem(position).hasImage()) {
            ((ImageView) itemView.findViewById(R.id.item_image)).setImageResource(currentWord.getImageResourceId());
            itemView.findViewById(R.id.item_image).setVisibility(View.VISIBLE);
        } else {
            itemView.findViewById(R.id.item_image).setVisibility(View.GONE);
        }


        /*
          Populates the Text LinearLayout background with appropriate color
         */
        itemView.findViewById(R.id.item_text_container).setBackgroundColor(ContextCompat.getColor(getContext(), mCategoryColorResourceId));

        // Set OnClickListener with the Text Container LinearLayout
        itemView.setOnClickListener(v -> play(currentWord.getAudioResourceId(), itemView.findViewById(R.id.talk_icon)));

        return itemView;
    }

    // This may not be the ideal way but what to do its complicated but it works
    private ImageView talkIcon;
    private MediaPlayer mediaPlayer;
    private void play(int audioID, ImageView talk) {
        if (mediaPlayer != null) {
            stop();
            talkIcon = talk;
        }
        mediaPlayer = MediaPlayer.create(getContext(), audioID);
        mediaPlayer.setOnCompletionListener(mp -> stop());
        mediaPlayer.start();
        talk.setVisibility(View.VISIBLE);
        talkIcon = talk;
    }
    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        talkIcon.setVisibility(View.GONE);
    }
}
