package com.example.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;
    private ImageView talkIcon;

    private final AudioManager.OnAudioFocusChangeListener mFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            } else {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }
        }
    };

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            talkIcon.setVisibility(View.GONE);
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mFocusChangeListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create a list of Words
        final ArrayList<Word> wordArrayList = new ArrayList<>();

        wordArrayList.add(new Word(R.drawable.color_red,"red", "weṭeṭṭi", R.raw.color_red));
        wordArrayList.add(new Word(R.drawable.color_green, "green", "chokokki", R.raw.color_green));
        wordArrayList.add(new Word(R.drawable.color_brown, "brown", "ṭakaakki", R.raw.color_brown));
        wordArrayList.add(new Word(R.drawable.color_gray, "gray", "ṭopoppi", R.raw.color_gray));
        wordArrayList.add(new Word(R.drawable.color_black, "black", "kululli", R.raw.color_black));
        wordArrayList.add(new Word(R.drawable.color_white, "white", "kelelli", R.raw.color_white));
        wordArrayList.add(new Word(R.drawable.color_dusty_yellow, "dusty yellow", "ṭopiisә", R.raw.color_dusty_yellow));
        wordArrayList.add(new Word(R.drawable.color_mustard_yellow, "mustard yellow", "chiwiiṭә", R.raw.color_mustard_yellow));

        final WordAdapter itemsAdapter = new WordAdapter(this, wordArrayList, R.color.colors_background);

        final ListView listView = findViewById(R.id.color_list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            releaseMediaPlayer();
            talkIcon = view.findViewById(R.id.talk_icon);
            int result = mAudioManager.requestAudioFocus(mFocusChangeListener, AudioManager.USE_DEFAULT_STREAM_TYPE, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mMediaPlayer = MediaPlayer.create(ColorsActivity.this, wordArrayList.get(position).getAudioResourceId());
                mMediaPlayer.start();
                talkIcon.setVisibility(View.VISIBLE);
                mMediaPlayer.setOnCompletionListener(mp -> releaseMediaPlayer());
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}