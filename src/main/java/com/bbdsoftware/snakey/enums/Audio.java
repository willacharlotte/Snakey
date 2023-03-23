package com.bbdsoftware.snakey.enums;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public enum Audio {
    CRUNCH("crunch.wav"),
    DEATH("death.wav");

    private AudioInputStream sound;

    private Audio(String fileName) {
        try {
            File file = new File(getPath(fileName));
            this.sound = AudioSystem.getAudioInputStream(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getPath(String fileName) {
        return "src/main/resources/" + fileName;
    }

    public AudioInputStream getSound() {
        return this.sound;
    }
}
