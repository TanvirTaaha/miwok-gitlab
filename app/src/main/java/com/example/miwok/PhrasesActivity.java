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

public class PhrasesActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_phrases);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create a list of Words
        final ArrayList<Word> wordArrayList = new ArrayList<>();

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

        final WordAdapter itemsAdapter = new WordAdapter(this, wordArrayList, R.color.phrases_background);

        final ListView listView = findViewById(R.id.phrases_list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            releaseMediaPlayer();
            talkIcon = view.findViewById(R.id.talk_icon);
            int result = mAudioManager.requestAudioFocus(mFocusChangeListener, AudioManager.USE_DEFAULT_STREAM_TYPE, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, wordArrayList.get(position).getAudioResourceId());
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