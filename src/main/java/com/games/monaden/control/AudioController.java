package com.games.monaden.control;

import com.games.monaden.services.audioplayer.AudioPlayer;

import java.io.File;

/**
 Class is responsible for calling audio playing.
 As a link between the GameLoop and CharacterController to the service AudioPlayer.
 */

public class AudioController {
    AudioPlayer musicPlayer;

    public AudioController(){
        musicPlayer = new AudioPlayer();
    }

    // Changing the music by keys in the game.
    public void playMusic(int i){
        musicPlayer.playMusic(i);
    }

    // Stop the current music
    public void stopMusic(){
        musicPlayer.stopMusic();
    }

    // Loaded from mapname.xml for the level-music.
    public void playMusic(String filepath) { musicPlayer.playMusic(filepath); }

    // Loaded soundEffects
    public void playSound(String filepath){
        musicPlayer.playSound(filepath);
    }

    public double volumeDown(){
        return musicPlayer.volumeDown();
    }

    public double volumeUp(){
        return musicPlayer.volumeUp();
    }

    public void addMusic(String path){
        musicPlayer.addMusic(path);
    }
}
