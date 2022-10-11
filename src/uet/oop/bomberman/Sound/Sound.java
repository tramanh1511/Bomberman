package uet.oop.bomberman.Sound;

import javax.sound.sampled.*;
import java.io.IOException;

public class Sound {
    private Clip clip;

    public Sound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    getClass().getResourceAsStream("/music/" + soundName + ".wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play(Boolean playing, int loop) {
        if (!playing) {
            if (clip.isRunning()) {
                clip.stop();
            }
        } else {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public boolean isPlaying() {
        return clip.isRunning();
    }

}
