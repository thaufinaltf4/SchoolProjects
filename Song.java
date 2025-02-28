import java.util.Arrays;

public class Song {
	
	private String title;
	private String artist;
	private int[] time;
	private static final String INFO_DELIMITER = "; ";
    private static final String TIME_DELIMITER = ":";
    private static final int IDX_TITLE = 0;
    private static final int IDX_ARTIST = 1;
    private static final int IDX_TIME = 2;
	
	public Song(String title, String artist, int[] time) {
		this.title = title;
		this.artist = artist;
		this.time = Arrays.copyOf(time, time.length);
	}
	 public Song(String info) {
	        String[] parts = info.split(INFO_DELIMITER);
	        this.title = parts[IDX_TITLE];
	        this.artist = parts[IDX_ARTIST];
	        String[] timeParts = parts[IDX_TIME].split(TIME_DELIMITER);
	        
	        if (timeParts.length == 3) {
	            this.time = new int[]{Integer.parseInt(timeParts[2]), Integer.parseInt(timeParts[1]), Integer.parseInt(timeParts[0])};
	        } else if (timeParts.length == 2) {
	            this.time = new int[]{Integer.parseInt(timeParts[1]), Integer.parseInt(timeParts[0]), 0};
	        } else {
	            this.time = new int[]{Integer.parseInt(timeParts[0]), 0, 0};
	        }
	    }
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public int[] getTime() {
		int seconds = time.length > 0 ? time[0] : 0;
	    int minutes = time.length > 1 ? time[1] : 0;
	    int hours = time.length > 2 ? time[2] : 0;

	    if (hours > 0) {
	        return new int[]{seconds, minutes, hours};// Full array
	    } else if (minutes > 0) {
	        return new int[]{seconds, minutes};// Drop hours if 0
	    } else {
	        return new int[]{seconds};// Only seconds if < 1 min
	    }
	}
	
	@Override
	public String toString() {
	    int seconds = time.length > 0 ? time[0] : 0;
	    int minutes = time.length > 1 ? time[1] : 0;
	    int hours = time.length > 2 ? time[2] : 0;

	    String formattedTime;
	    if (hours > 0) {
	        formattedTime = String.format("%d:%02d:%02d", hours, minutes, seconds);
	    } else if (minutes > 0) {
	        formattedTime = String.format("%d:%02d", minutes, seconds);
	    } else {
	        formattedTime = String.format("%d", seconds); // **Fix: Only show seconds if < 1 min**
	    }

	    return title + INFO_DELIMITER + artist + INFO_DELIMITER + formattedTime;
	}
}

