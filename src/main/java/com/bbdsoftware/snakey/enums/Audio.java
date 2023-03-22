package com.bbdsoftware.snakey.enums;

public enum Audio {
    CRUNCH("crunch.wav"),
    DEATH("death.wav");

    private String fileName;

    private Audio(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return getClass().getResource(fileName).getFile();
    }
}
