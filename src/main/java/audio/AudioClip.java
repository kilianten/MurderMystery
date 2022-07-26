package audio;

import game.settings.GameSettings;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public abstract class AudioClip {


    private final Clip clip;

    public AudioClip(Clip clip) {
        this.clip = clip;
        clip.start();
    }

    public void update(GameSettings settings){
        final FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(getVolume(settings));
    }

    protected abstract float getVolume(GameSettings settings);

    public boolean hasFinishedPlaying(){
        return !clip.isRunning();
    }

    public void cleanUp(){
        clip.close();
    }
}
