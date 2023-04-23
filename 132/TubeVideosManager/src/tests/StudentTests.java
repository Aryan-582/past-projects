package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import tubeVideosManager.Genre;
import tubeVideosManager.Playlist;
import tubeVideosManager.TubeVideosManager;
import tubeVideosManager.Video;

import java.util.Random;

/**
 * 
 * You need student tests if you are asking for help during office hours about
 * bugs in your code. Feel free to use tools available in TestingSupport.java
 * 
 * @author UMCP CS Department
 *
 */
public class StudentTests {
// tests playlist constructor, getName, addToPlaylist, and playlist toString
	@Test
	public void test1() {
		String match = "Aryan's Playlist\nPlaylist Name: Aryan's Playlist\nVideoTitles: [The World]";
		String answer = new String();
		Playlist test = new Playlist("Aryan's Playlist");
		answer += test.getName();
		test.addToPlaylist("The World");
		answer += "\n" + test.toString();
		assertTrue(answer.equals(match));
	}

// tests getPlaylistVideosTitles and removeFromPlaylistAll	
	@Test
	public void test2() {
		String match = "Aryan's Playlist\nThe World\nMonti " + "Carlo\nThrottle\nPlaylist Name: Aryan's"
				+ " Playlist\nVideoTitles: [Monti Carlo, Throttle]";
		String answer = new String();
		Playlist test = new Playlist("Aryan's Playlist");
		answer += test.getName();
		test.addToPlaylist("The World");
		test.addToPlaylist("Monti Carlo");
		test.addToPlaylist("Throttle");
		ArrayList<String> temp = test.getPlaylistVideosTitles();
		for (String videos : temp) {
			answer += "\n" + videos.toString();
		}
		test.removeFromPlaylistAll("The World");
		answer += "\n" + test.toString();
		System.out.println(answer);
		assertTrue(answer.equals(match));

	}

	// tests shuffleVideoTitles
	@Test
	public void test3() {
		String match = "Playlist Name: Aryan's Playlist\n" + "VideoTitles: [Throttle, The World, Monti Carlo]";
		String answer = new String();
		Random rando = new Random(2);
		Playlist test = new Playlist("Aryan's Playlist");
		test.addToPlaylist("The World");
		test.addToPlaylist("Monti Carlo");
		test.addToPlaylist("Throttle");
		test.shuffleVideoTitles(rando);
		answer += test.toString();
		System.out.println(answer);
		System.out.println(match);
		assertTrue(answer.equals(match));
	}

	// tests all video get methods, constructor, addComments and getComments
	@Test
	public void test4() {
		Genre genre = Genre.Comedy;
		String match = "Bro why?www.youtube.com/watch110Comedy[Nice job!, Trash!]";
		String answer = new String();
		Video chungle = new Video("Bro why?", "www.youtube.com/watch1", 10, genre);
		answer += chungle.getTitle() + chungle.getUrl() + chungle.getDurationInMinutes() + chungle.getGenre();
		chungle.addComments("Nice job!");
		chungle.addComments("Trash!");
		answer += chungle.getComments();
		assertTrue(answer.equals(match));
	}

	// testing compareTo
	@Test
	public void test5() {
		Genre genre = Genre.Comedy;
		Video chungle = new Video("Bro why?", "www.youtube.com/watch1", 10, genre);
		Video chungle2 = new Video("Bro why?", "www.youtube.com/watch2", 11, genre);
		Video chungle3 = new Video("Bro dont?", "www.youtube.com/watch1", 10, genre);
		assertTrue(chungle.compareTo(chungle2) == 0 && chungle.compareTo(chungle3) == 1);
	}

	// testing equals
	@Test
	public void test6() {
		Genre genre = Genre.Comedy;
		Video chungle = new Video("Bro why?", "www.youtube.com/watch1", 10, genre);
		Video chungle2 = new Video("Bro why?", "www.youtube.com/watch2", 11, genre);
		Video chungle3 = new Video("Bro dont?", "www.youtube.com/watch3", 10, genre);
		assertTrue(chungle.equals(chungle2) == true && chungle.equals(chungle3) == false);
	}

	// tests tube constructor, addVideoToDB, getAllVideosInDB, findVideo, and
	// addComments
	@Test
	public void test7() {
		Genre genre = Genre.Comedy;
		String match = "Title: \"Bro why?\"\n" + "Url: www.youtube.com/watch1\n" + "Duration (minutes): 10\n"
				+ "Genre: Comedy\n" + "[Title: \"Bro why?\"\n" + "Url: www.youtube.com/watch1\n"
				+ "Duration (minutes): 10\n" + "Genre: Comedy\n" + ", Title: \"Bro dont?\"\n"
				+ "Url: www.youtube.com/watch1\n" + "Duration (minutes): 10\n" + "Genre: Comedy\n" + "][I dont know]";
		String answer = new String();
		TubeVideosManager tubeVideosManager = new TubeVideosManager();
		tubeVideosManager.addVideoToDB("Bro why?", "www.youtube.com/watch1", 10, genre);
		tubeVideosManager.addVideoToDB("Bro dont?", "www.youtube.com/watch1", 10, genre);
		ArrayList<Video> videos = tubeVideosManager.getAllVideosInDB();
		tubeVideosManager.addComments("Bro why?", "I dont know");
		Video search = tubeVideosManager.findVideo("Bro why?");
		answer += search.toString() + videos.toString();
		answer += search.getComments();
		assertTrue(answer.equals(match));
	}
	//tests addPlaylist, getPlaylistsNames, addVideoToPlaylist, getPlaylist, and clearDatabase
	@Test
	public void test8() {
		Genre genre = Genre.Comedy;
		String match = "\n" + "Aryan's Playlist\n" + "Chill\n" + "Workout\n" + "Playlist Name: Aryan's Playlist\n"
				+ "VideoTitles: [Bro why?]\n" + "Database Cleared\n" + "Sleep\n" + "Study";
		String answer = new String();
		TubeVideosManager tVM = new TubeVideosManager();
		tVM.addVideoToDB("Bro why?", "www.youtube.com/watch1", 10, genre);
		tVM.addPlaylist("Aryan's Playlist");
		tVM.addPlaylist("Chill");
		tVM.addPlaylist("Workout");
		String[] playlists = tVM.getPlaylistsNames();
		tVM.addVideoToPlaylist("Bro why?", "Aryan's Playlist");
		Playlist temp = tVM.getPlaylist("Aryan's Playlist");
		for (String names : playlists) {
			answer += "\n" + names.toString();
		}
		answer += "\n" + temp.toString();
		tVM.clearDatabase();
		answer += "\n" + "Database Cleared";
		tVM.addPlaylist("Sleep");
		tVM.addPlaylist("Study");
		String[] cleared = tVM.getPlaylistsNames();
		for (String names : cleared) {
			answer += "\n" + names.toString();
		}
		assertTrue(answer.equals(match));

	}
	//testing searchForVideos
	@Test
	public void test9() {
		Genre genre = Genre.Comedy;
		String match = "Playlist Name: temp\n"
				+ "VideoTitles: [Bro why?]";
		String answer = new String();
		TubeVideosManager tVM = new TubeVideosManager();
		Playlist videos = new Playlist("Videos");
		tVM.addPlaylist("temp");
		tVM.addVideoToDB("Bro why?", "www.youtube.com/watch1", 10, genre);
		videos = tVM.searchForVideos("temp", null, -10, genre);
		answer += videos.toString();
		System.out.println(match);
		System.out.println(answer);
		assertTrue(answer.equals(match));
	}

}
