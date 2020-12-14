package uet.oop.bomberman.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SimpleAudioPlayer
{
    Long currentFrame;
    Clip clip;
    String status;

    AudioInputStream audioInputStream;
    static String filePath;

    public SimpleAudioPlayer()  throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        audioInputStream =
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void open(String s, int turn)
    {
        SimpleAudioPlayer audioPlayer;
        try
        {
            //filePath = "res/sound/soundtrack.wav";
            filePath = "res/sound/" + s;
            audioPlayer = new SimpleAudioPlayer();
            audioPlayer.play(turn);
        }
        catch (Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();

        }

    }

    public void play(int x)
    {
        if (x == 0)
            clip.start();
        else clip.loop(x - 1);
        status = "play";
    }

    public void pause()
    {
        if (status.equals("paused"))
        {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame =
                this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

}