package audio;

import game.settings.AudioSettings;

import javax.sound.sampled.Clip;

public class MusicClip extends AudioClip {

    public MusicClip(Clip clip) {
        super(clip);
    }

    @Override
    protected float getVolume(AudioSettings settings) {
        return settings.getMusicVolume();
    }
}