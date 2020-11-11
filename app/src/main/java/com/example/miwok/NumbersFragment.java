package com.example.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Objects;

public class NumbersFragment extends Fragment {

    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;
    private ImageView talkIcon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View thisFragmentLayout = inflater.inflate(R.layout.fragment_layout, container, false);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) Objects.requireNonNull(getActivity()).getSystemService(Context.AUDIO_SERVICE);

        // Create a list of Words
        final ArrayList<Word> wordArrayList = new ArrayList<>();

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

        final WordAdapter itemsAdapter = new WordAdapter(getActivity(), wordArrayList, R.color.numbers_background);

        final ListView listView = thisFragmentLayout.findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);


        listView.setOnItemClickListener((parent, view, position, id) -> {
            releaseMediaPlayer();
            talkIcon = view.findViewById(R.id.sound_icon);
            int result = mAudioManager.requestAudioFocus(mFocusChangeListener, AudioManager.USE_DEFAULT_STREAM_TYPE, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mMediaPlayer = MediaPlayer.create(getActivity(), wordArrayList.get(position).getAudioResourceId());
                mMediaPlayer.start();
                talkIcon.setVisibility(View.VISIBLE);
                mMediaPlayer.setOnCompletionListener(mp -> releaseMediaPlayer());
            }
        });
        // Inflate the layout for this fragment
        return thisFragmentLayout;
    }

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
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}