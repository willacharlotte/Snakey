package com.bbdsoftware.snakey.enums;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public enum Audio {
    CRUNCH("crunch.wav"),
    DEATH("death.wav");

    private String fileName;

    private Audio(String fileName) {
        this.fileName = fileName;
    }

    private String getPath(String fileName) {
        return "src/main/resources/" + fileName;
    }

    public AudioInputStream getSound() {
        try {
            File file = new File(getPath(fileName));
            return AudioSystem.getAudioInputStream(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
