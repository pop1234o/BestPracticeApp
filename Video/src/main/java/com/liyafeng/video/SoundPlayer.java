package com.liyafeng.video;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.SoundPool;


import java.util.HashMap;

public class SoundPlayer {


    public static final int TYPE_DIAMOND = 0;

    HashMap<Integer, Integer> hashMap = new HashMap<>();
    private final SoundPool soundPool;

    public SoundPlayer() {
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        AudioAttributes build = builder.setLegacyStreamType(AudioManager.STREAM_MUSIC).build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(100)
                .setAudioAttributes(build).build();

//        hashMap.put(TYPE_DIAMOND, soundPool.load(BrandyApplication.getInstance(), R.raw.audio_bonus_points_diamonds, 1));

    }

    public void playDiamond() {
        play(TYPE_DIAMOND);
    }

    public void play(int type) {
        Integer id = hashMap.get(type);
        if (id != null) {
            soundPool.play(id, 1, 1, 1, 0, 1);
        }
    }

    /**
     * Calculates the playback duration of the file
     *
     * @param path the path of the file you want to calc duration
     * @return the duration in milliseconds, if no duration is available, 0 is returned.
     */
    private int calcDuration(String path) {
        int duration = 0;
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        try {
            mmr.setDataSource(path);
            String durationStr = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            if (durationStr != null) {
                duration = Integer.parseInt(durationStr);
            }

//            if (BuildConfig.DEBUG) Log.v(TAG, "Get duration (path) - OK");

        } finally {
            mmr.release();
        }
        return duration;
    }
}
