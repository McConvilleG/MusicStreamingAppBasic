package MusicStreamingApp;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Song implements Runnable {

    private String title;
    private String artist;
    private String filePath; //filePath to song on machine 
    private Clip clip;
    private long clipPosition = 0;  // Stores the position when the song is paused
    private boolean isPlaying = false;
    
    /*
     * contructor with args 
     */
    public Song(String title, String artist, String filePath) {
        this.title = title;
        this.artist = artist;
        this.filePath = filePath;
    }

    /*
     * Method to play the song in a background thread
     */
    public void playInBackground() {
        if (!isPlaying) {
            Thread thread = new Thread(this);
            thread.start();  // Run the song play in a separate thread
        }
    }

    @Override
    public void run() {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.setMicrosecondPosition(clipPosition);  // If resuming, start from the paused position
            clip.start();
            isPlaying = true;

            System.out.println("Now playing: " + title + " by " + artist);

            // Wait until the song finishes playing
            while (!isFinished()) {
                Thread.sleep(100);  // Check every 100ms if the song has finished
            }

            isPlaying = false;  // Song is done
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Method to pause the song
     */
    public void pause() {
        if (clip != null && clip.isRunning()) {
            clipPosition = clip.getMicrosecondPosition();  // Save current position
            clip.stop();  // Pause playback
            isPlaying = false;
            System.out.println("Paused: " + title);
        }
    }

    /*
     * Method to stop the song
     */
    public void stop() {
        if (clip != null) {
            clipPosition = 0;  // Reset position
            clip.stop();  // Stop playback
            clip.close(); // Release resources
            isPlaying = false;
            System.out.println("Stopped: " + title);
        }
    }

    /*
     * Check if the song is still playing
     */
    public boolean isPlaying() {
        return isPlaying;
    }

    /*
     * Check if the song is finished playing
     */
    public boolean isFinished() {
        return clip != null && !clip.isRunning() && clip.getMicrosecondLength() == clip.getMicrosecondPosition();
    }
}
