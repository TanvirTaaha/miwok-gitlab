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

public class FamilyMembersFragment extends Fragment {

    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;
    private ImageView talkIcon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View thisFragmentLayout = inflater.inflate(R.layout.fragment_layout, container, false);
        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) Objects.requireNonNull(getActivity()).getSystemService(Context.AUDIO_SERVICE);

        // Create a list of Words
        final ArrayList<Word> wordArrayList = new ArrayList<>();

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

        final WordAdapter itemsAdapter = new WordAdapter(getActivity(), wordArrayList, R.color.family_background);

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