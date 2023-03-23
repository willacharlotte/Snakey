package com.bbdsoftware.snakey.controllers;

import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import com.bbdsoftware.snakey.enums.Audio;

public class AudioController {
    private final HashMap<String, Clip> clips = new HashMap<>();

    public AudioController() {
        try {
            for (Audio audio : Audio.values()) {
                clips.put(audio.name(), AudioSystem.getClip());
            }
            setClips();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Clip getClipOfAudio(Audio audio) {
        return clips.get(audio.name());
    }

    private void setClips() throws LineUnavailableException, IOException {
        for (Audio audio : Audio.values()) {
            getClipOfAudio(audio).open(audio.getSound());
        }
    }

    public void play(Audio audio) {
        Clip clip = getClipOfAudio(audio);
        clip.setMicrosecondPosition(0);
        clip.start();
    }

    public void stop(Audio audio) {
        getClipOfAudio(audio).stop();
    }

    public void closeAll() {
        for (Clip clip : clips.values()) {
            clip.stop();
            clip.close();
        }
    }
}
