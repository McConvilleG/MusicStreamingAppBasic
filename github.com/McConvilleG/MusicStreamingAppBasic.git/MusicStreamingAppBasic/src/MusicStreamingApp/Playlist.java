package MusicStreamingApp;

import java.util.ArrayList;
import java.util.List;

/*
 * class handles song playlist 
 * @author Gary 
 */
public class Playlist {

    private List<Song> songs = new ArrayList<>();
    private int currentSongIndex = 0;

    public void addSong(Song song) {
        songs.add(song);
    }

    /*
     *  Method to play the current song in the background
     */
    public void play() {
        if (!songs.isEmpty() && !songs.get(currentSongIndex).isPlaying()) {
            songs.get(currentSongIndex).playInBackground();
        } else {
            System.out.println("Song is already playing.");
        }
    }

    /*
     *  Pause the current song
     */
    public void pause() {
        if (!songs.isEmpty()) {
            songs.get(currentSongIndex).pause();
        }
    }

    /*
     *  Stop the current song
     */
    public void stop() {
        if (!songs.isEmpty()) {
            songs.get(currentSongIndex).stop();
        }
    }
    
    /*
     * Restart the current song from the beginning
     */
    public void restart() {
        if (!songs.isEmpty()) {
            songs.get(currentSongIndex).restart();
        }
    }

    /*
     * Skip to the next song
     */
    public void next() {
        stop();  // Stop the current song
        if (currentSongIndex < songs.size() - 1) {
            currentSongIndex++;
            play();  // Play the next song
        } else {
            System.out.println("End of playlist reached.");
        }
    }

    /*
     * Go to the previous song
     */
    public void previous() {
        stop();  // Stop the current song
        if (currentSongIndex > 0) {
            currentSongIndex--;
            play();  // Play the previous song
        } else {
            System.out.println("At the beginning of the playlist.");
        }
    }

    /*
     * Automatically move to the next song when the current one finishes
     */
    public void checkForAutoNext() {
        if (!songs.isEmpty() && songs.get(currentSongIndex).isFinished()) {
            next();  // Automatically go to the next song
        }
    }
    
    
    //***add functionality here to put song back to start***
}//end of class 
