package com.penguins.sound;

import com.google.common.collect.ImmutableMap;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import java.util.Map;

public class SoundController {


    private Music currentSong = null;
    private Map<Song, Music> music;
    private Map<SoundEffect, Sound> sfx;

    public SoundController() {}

    public void loadSounds() throws SlickException {
        sfx = ImmutableMap.of(SoundEffect.HOVER, new Sound("sfx/sfx_hover.ogg"),
                SoundEffect.SELECT, new Sound("sfx/sfx_select.ogg"));

        music = ImmutableMap.of(Song.TITLE, new Music("music/normal_menu.ogg"));

    }

    public void playSoundtrack(Song song) {
        music.get(song).loop();
        currentSong = music.get(song);
    }

    public Music getCurrentSong(){
        return currentSong;
    }

    public void playSoundEffect(SoundEffect effect) {
        sfx.get(effect).play();
    }

}
