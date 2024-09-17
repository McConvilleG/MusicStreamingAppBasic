/**
 * 
 */
package MusicStreamingApp;

import java.util.Scanner;

/**
 * @author Gary
 *
 */
public class MusicStreamingApp {

	/**
	 * start point for application
	 * @param args
	 */
	public static void main(String[] args) {
		
		Song song1 = new Song("Girl From the North Country", "Bob Dylan", "AudioResources/Girl_from_the_North_Country.wav");
        Song song2 = new Song("War of Man", "Neil Young", "AudioResources/War_of_Man.wav");
        Song song3 = new Song("Walk Away", "Tom Waits", "AudioResources/WalkAway.wav");
		
        Playlist playlist = new Playlist();
        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.addSong(song3);
        
        //set up scanner to take user input 
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        //loop
        while (running) {
            // Check if the current song has finished playing, and move to the next if necessary
            playlist.checkForAutoNext();

            // Prompt user for input
            System.out.println("Enter command (play, pause, stop, next, previous, exit): ");
            String userInput = scanner.nextLine();

            switch (userInput.toLowerCase()) {
                case "play":
                    playlist.play();
                    break;
                case "pause":
                    playlist.pause();
                    break;
                case "stop":
                    playlist.stop();
                    break;
                case "next":
                    playlist.next();
                    break;
                case "previous":
                    playlist.previous();
                    break;
                case "exit":
                    running = false;
                    playlist.stop();  // Stop the current song before exiting
                    break;
                default:
                    System.out.println("Invalid command.");
            }
        }
        
        //close resource 
        scanner.close();
    }

}//end of class 
