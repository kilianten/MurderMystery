package audio;

import game.settings.AudioSettings;
import game.settings.GameSettings;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AudioPlayer {

    private AudioSettings audioSettings;
    private List<AudioClip> audioClips;

    public AudioPlayer(AudioSettings audioSettings) {
        this.audioSettings = audioSettings;
        audioClips = new ArrayList<>();
    }

    public void update(){
        audioClips.forEach(audioClip -> audioClip.update(audioSettings));
        List.copyOf(audioClips).forEach(audioClip -> {
            if(audioClip.hasFinishedPlaying()){
                audioClip.cleanUp();
                audioClips.remove(audioClip);
            }
        });
    }

    public void playMusic(String fileName){
        final Clip clip = getClip(fileName);
        final MusicClip musicClip = new MusicClip(clip);
        musicClip.setVolume(audioSettings);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        audioClips.add(musicClip);
    }

    public void playSound(String fileName){
        final Clip clip = getClip(fileName);
        final SoundClip soundClip = new SoundClip(clip);
        soundClip.setVolume(audioSettings);
        audioClips.add(soundClip);
    }

    private Clip getClip(String fileName){
        final URL soundFile = AudioPlayer.class.getResource("/audio/" + fileName);
        try(AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile)){
            final Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setMicrosecondPosition(0);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
        return null;
    }

    public void clear(){
        audioClips.forEach(AudioClip::cleanUp);
        audioClips.clear();
    }

}
