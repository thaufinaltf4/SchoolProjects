import java.util.Arrays;
public class Playlist {
	private Song[] songs;
    private int numSongs;
    private static final int MIN_CAPACITY = 3;

    // Constructor with default capacity
    public Playlist() {
        this(MIN_CAPACITY);
    }

    // Constructor with specified capacity
    public Playlist(int capacity) {
        if (capacity < MIN_CAPACITY) {
            capacity = MIN_CAPACITY;
        }
        songs = new Song[capacity];
        numSongs = 0;
    }

    //getters
    public int getCapacity() {
        return songs.length;
    }

    
    public int getNumSongs() {
        return numSongs;
    }

    
    public Song getSong(int index) {
        if (index < 0 || index >= numSongs) {
            return null;
        }
        return songs[index];
    }

    
    public Song[] getSongs() {
        return Arrays.copyOf(songs, numSongs);
    }

    // add song
    public boolean addSong(Song song) {
        if (numSongs >= songs.length || song == null) {
            return false;
        }
        songs[numSongs++] = song;
        return true;
    }

    // add a song at specific spot
    public boolean addSong(int index, Song song) {
        if (numSongs >= songs.length || index < 0 || index > numSongs || song == null) {
            return false;
        }
        // Shift songs right
        for (int i = numSongs; i > index; i--) {
            songs[i] = songs[i - 1];
        }
        songs[index] = song;
        numSongs++;
        return true;
    }

    // add songs from another playlist
    public int addSongs(Playlist playlist) {
        if (playlist == null) {
            return 0;
        }
        int addedCount = 0;
        for (Song song : playlist.getSongs()) {
            if (addSong(song)) {
                addedCount++;
            }
        }
        return addedCount;
    }

    // remove last song
    public Song removeSong() {
        if (numSongs == 0) {
            return null;
        }
        Song removedSong = songs[--numSongs];
        songs[numSongs] = null; 
        return removedSong;
    }

    // remove a song at specific spot
    public Song removeSong(int index) {
        if (index < 0 || index >= numSongs) {
            return null;
        }
        Song removedSong = songs[index];
        
        // shift songs to the left
        for (int i = index; i < numSongs - 1; i++) {
            songs[i] = songs[i + 1];
        }
        songs[--numSongs] = null; 
        return removedSong;
    }

}
