package uet.oop.bomberman.Sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;

public class Sound {
    public static boolean isPlaying = true;
    public static void play(String name) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            getClass().getResourceAsStream("/music/" + name + ".wav"));
                    clip.open(inputStream);
                    clip.start();
                    isPlaying = true;
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();

    }
    public static void stop(String name){
        new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("00");
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            getClass().getResourceAsStream("/music/" + name + ".wav"));
                    clip.open(inputStream);
                    clip.stop();
                    isPlaying = false;
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}
