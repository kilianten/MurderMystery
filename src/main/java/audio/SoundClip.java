package audio;

import game.settings.GameSettings;

import javax.sound.sampled.Clip;

public class SoundClip extends AudioClip {

    public SoundClip(Clip clip) {
        super(clip);
    }

    @Override
    protected float getVolume(GameSettings settings) {
        return settings.getSoundVolume();
    }
}
