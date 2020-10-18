package com.example.miwok;

public class Word {

    private final String mEnglishText;
    private final String mMiwokText;
    private final int mImageResourceId;
    private static final int NO_IMAGE_ID = -1;

    public Word(int imageResourceId, String english, String miwok) {
        mImageResourceId = imageResourceId;
        mEnglishText = english;
        mMiwokText = miwok;
    }

    public Word(String english, String miwok) {
        mEnglishText = english;
        mMiwokText = miwok;
        mImageResourceId = NO_IMAGE_ID;
    }

    public String getEnglishTranslation() {
        return mEnglishText;
    }

    public String getMiwokTranslation() {
        return mMiwokText;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Determines if an image resources is provided or not
     * @return a boolean
     */
    public boolean hasImage() {
        return (mImageResourceId != NO_IMAGE_ID);
    }
}
