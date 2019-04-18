package Game.Mechanics;

import java.io.*;
import javax.sound.sampled.*;

/**
 * Plays sound files
 *
 * @author Zachary Martin & James Clark
 */
public class Sound {

    /**
     * For sounds that need to be played on a continuous loop
     *
     * @param file name of the file
     */
    public void playLoop(String file) {

        File sound = new File(file);
        try {
            
            Clip c = AudioSystem.getClip();
            c.open(AudioSystem.getAudioInputStream(sound));
            c.loop(javax.sound.midi.Sequencer.LOOP_CONTINUOUSLY);
            c.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            
        }
        
    }

    /**
     * For sounds that need to be played once
     *
     * @param file name of the file
     */
    public void playOnce(String file) {

        File sound = new File(file);
        try {
            
            Clip c = AudioSystem.getClip();
            c.open(AudioSystem.getAudioInputStream(sound));
            c.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            
        }
        
    }
    
}
