package audio;

import game.settings.AudioSettings;
import game.settings.GameSettings;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public abstract class AudioClip {


    private final Clip clip;

    public AudioClip(Clip clip) {
        this.clip = clip;
        clip.start();
    }

    public void update(AudioSettings settings){
        setVolume(settings);
    }

    public void setVolume(AudioSettings settings) {
        final FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = control.getMaximum() - control.getMinimum();
        float gain = (range * getVolume(settings)) + control.getMinimum();

        control.setValue(gain);
    }

    protected abstract float getVolume(AudioSettings settings);

    public boolean hasFinishedPlaying(){
        return !clip.isRunning();
    }

    public void cleanUp(){
        clip.close();
    }
}
