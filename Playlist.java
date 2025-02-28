import java.util.ArrayList;
import java.util.StringJoiner;
import java.io.*;

public class Playlist {
    private ArrayList<Song> songs;
    private static final int MIN_CAPACITY = 3;
    
    public Playlist() {
        this.songs = new ArrayList<>(MIN_CAPACITY);
    }
    
    public Playlist(String filename) throws IOException {
        this();
        addSongs(filename);
    }
    
    public int getNumSongs() {
        return songs.size();
    }
    
    public Song getSong(int index) {
        if (index < 0 || index >= songs.size()) {
            return null;
        }
        return songs.get(index);
    }
    
    public Song[] getSongs() {
        return songs.toArray(new Song[0]);
    }
    
    public boolean addSong(Song song) {
        return addSong(songs.size(), song);
    }
    
    public boolean addSong(int index, Song song) {
        if (song == null || index < 0 || index > songs.size()) {
            return false;
        }
        songs.add(index, song);
        return true;
    }
    
    public int addSongs(Playlist playlist) {
        if (playlist == null) {
            return 0;
        }
        int initialSize = songs.size();
        for (Song song : playlist.getSongs()) {
            addSong(song);
        }
        return songs.size() - initialSize;
    }
    
    public int addSongs(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        int count = 0;
        while ((line = reader.readLine()) != null) {
            if (addSong(new Song(line))) {
                count++;
            }
        }
        reader.close();
        return count;
    }
    
    public Song removeSong() {
        return removeSong(songs.size() - 1);
    }
    
    public Song removeSong(int index) {
        if (index < 0 || index >= songs.size()) {
            return null;
        }
        return songs.remove(index);
    }
    
    public void saveSongs(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Song song : songs) {
            writer.write(song.toString());
            writer.newLine();
        }
        writer.close();
    }
    
    public int[] getTotalTime() {
    	int totalSeconds = 0;
        for (Song song : songs) {
            int[] time = song.getTime();
            totalSeconds += time[0] + (time.length > 1 ? time[1] * 60 : 0) + (time.length > 2 ? time[2] * 3600 : 0);
        }

        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        // Adjust array length for time output
        if (hours > 0) {
            return new int[]{seconds, minutes, hours};// sec, min hr
        } else if (minutes > 0) {
            return new int[]{seconds, minutes};// sec, min
        } else {
            return new int[]{seconds};// sec
        }
    }
    
    @Override
    public String toString() {
        if (songs.isEmpty()) {
            return "";
        }
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        for (Song song : songs) {
            joiner.add(song.toString());
        }
        return joiner.toString();
    }
}
