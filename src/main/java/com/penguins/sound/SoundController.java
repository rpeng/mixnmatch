package com.penguins.sound;

import com.google.common.collect.ImmutableMap;
import org.newdawn.slick.Music;
import org.newdawn.slick.MusicListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import java.util.Map;

public class SoundController {


    private Music currentSong;
    private Map<Song, Music> music;
    private Map<SoundEffect, Sound> sfx;

    public SoundController() {}

    public void loadSounds() throws SlickException {
        sfx = ImmutableMap.of(SoundEffect.HOVER_C, new Sound("sfx/sfx_hover_C.ogg"),
                SoundEffect.HOVER_D, new Sound("sfx/sfx_hover_D.ogg"),
                SoundEffect.HOVER_E, new Sound("sfx/sfx_hover_E.ogg"),
                SoundEffect.SELECT, new Sound("sfx/sfx_select.ogg"),
                SoundEffect.TYPE, new Sound("sfx/sfx_type.ogg"));

        music = ImmutableMap.of(Song.TITLE, new Music("music/normal_menu.ogg"),
                Song.BOARD, new Music("music/normal_board.ogg"));

    }

    public void fadeTo(Song nextSong){
        if (currentSong != null){
            currentSong.fade(1000, 0, true);
            currentSong.addListener(new MusicListener() {
                @Override
                public void musicEnded(Music m) {
                    playSoundtrack(nextSong);
                }

                @Override
                public void musicSwapped(Music music, Music newMusic) {}
            });
        }else{
            playSoundtrack(nextSong);

        }
    }

    public void playSoundtrack(Song song) {
        music.get(song).loop();
        currentSong = music.get(song);
    }

    public void playSoundtrack(Song song, float volume) {
        music.get(song).setVolume(volume);
        music.get(song).loop();
        currentSong = music.get(song);
    }
    public void fadeOut() {
        if (currentSong != null) {
            currentSong.fade(1000, 0, true);
        }
    }

    public Music getCurrentSong(){
        return currentSong;
    }

    public void playSoundEffect(SoundEffect effect) {
        sfx.get(effect).play();
    }

}
