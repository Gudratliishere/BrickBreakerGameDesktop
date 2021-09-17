package com.company.brickbreakergame.game;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Dunay Gudratli
 */
public class Sound
{

    public static final Logger LOG = Logger.getLogger(Sound.class.getName());

    public static void playBrickBreaking()
    {
        try
        {
            URL url = Sound.class.getResource("/sounds/BrickBreaking.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);

            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex)
        {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public static void playGameIsOver()
    {
        //TODO choose sound for game is over
    }

    public static void playGameIsFinished()
    {
        //TODO choose sound for game is finished
    }
}
