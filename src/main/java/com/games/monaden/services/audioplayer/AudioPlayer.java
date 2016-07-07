package com.games.monaden.services.audioplayer;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 Responsible for playing sounds by request from rest of program
 */
public class AudioPlayer {

    private Media audioMusic;
    private Media audioSound;
    private MediaPlayer mediaplayerMusic;
    private MediaPlayer mediaplayerSound;
    private double volume = 0.8;
    private int songNr = 0;

    //TODO: Remove MusicList and only use musicMap
    //TODO: Change the way to GameLoop so it works.

    private List<String> musicList;
    private HashMap<String,String> musicMap;
    private HashMap<String,String> soundMap;
    private ClassLoader classLoader = this.getClass().getClassLoader();
    public AudioPlayer() {
        musicList = new ArrayList<>();
        musicMap = new HashMap<>();
        soundMap = new HashMap<>();
        addMusic("music/Dogsong.mp3");
        addMusic("music/song2");
        addMusic("dogsong" ,"music/Dogsong.mp3");
        loadSoundMap("step","sound/step.m4a");
    }

    public void stopMusic() {
        this.mediaplayerMusic.stop();
    }

    public void playMusic(String song) {
        if(musicMap.containsKey(song)) {
            try {
                String filepath = musicMap.get(song);
                this.audioMusic = new Media(filepath.toString());
                this.mediaplayerMusic = new MediaPlayer(this.audioMusic);

                this.mediaplayerMusic.setOnEndOfMedia(new Runnable() {
                    public void run() {
                        mediaplayerMusic.seek(Duration.ZERO);
                    }
                });
                this.mediaplayerMusic.setAutoPlay(true);
                this.mediaplayerMusic.setVolume(volume);
            } catch (Exception e) {
                System.err.println("error: sound can't play");
                System.err.println("error: " + e);
            }
        }
    }
    public void playMusic(int songNr) {

        try {
            this.songNr = (Math.floorMod(this.songNr + songNr, musicList.size()));
            String filepath = musicList.get(this.songNr);
            this.audioMusic = new Media(filepath.toString());
            this.mediaplayerMusic = new MediaPlayer(this.audioMusic);

            this.mediaplayerMusic.setOnEndOfMedia(new Runnable() {
                public void run() {
                    mediaplayerMusic.seek(Duration.ZERO);
                }
            });
            this.mediaplayerMusic.setAutoPlay(true);
            this.mediaplayerMusic.setVolume(volume);
        } catch (Exception e) {
            System.err.println("error: sound can't play");
            System.err.println("error: " + e);
        }

    }

    public void playSound(String filepath) {
        try {
            this.audioSound = new Media(soundMap.get(filepath));
            mediaplayerSound = new MediaPlayer(this.audioSound);
            mediaplayerSound.setStopTime(javafx.util.Duration.ONE);
            mediaplayerSound.play();
        } catch (Exception e) {
            System.err.println("error: sound can't play");
        }

    }

    public double volumeDown() {
        if (volume > 0.1) {
            volume = volume - 0.1;
            mediaplayerMusic.setVolume(volume);
        }
        return volume;

    }

    public double volumeUp() {
        if (volume < 1) {
            volume = volume + 0.1;
            mediaplayerMusic.setVolume(volume);
        }
        return volume;

    }

    public void addMusic(String music) {
        musicList.add(classLoader.getResource("music/song2.mp3").toExternalForm());
        System.out.println("Loaded music: song2.mp3");
    }

    public void addMusic(String name,String music){
        musicMap.put( name ,classLoader.getResource(music).toExternalForm());

    }

    private void loadSoundMap(String name, String sound){
        soundMap.put(name, classLoader.getResource(sound).toExternalForm());
        soundMap.put("step_sand_left",classLoader.getResource("sound/step_sand_l.flac").toExternalForm());
    }
}