package tubeVideosManager;

import java.util.ArrayList;

/**
 * Debugging Quiz driver. Make sure this file is placed in the tests package of
 * thhe TubeVideosManager project.
 * 
 * @author UMCP CS Department
 *
 */
public class DebuggingQuizDriver {

	public static void processing(TubeVideosManager tubeVideosManager, String title, String url,
			int durationInMinutes) {
		Genre genre = Genre.Educational;

		tubeVideosManager.addVideoToDB(title, url, durationInMinutes, genre);
	}

	public static void main(String[] args) {
		TubeVideosManager tubeVideosManager = new TubeVideosManager();

		String title = "Raining", url = "http://raining.not.real";
		int duration = 45;

		processing(tubeVideosManager, title, url, duration);
		ArrayList<Video> videos = tubeVideosManager.getAllVideosInDB();
		System.out.println(videos);
		System.out.println(tubeVideosManager.getStats());
	}
}
