
import java.io.*;
import java.util.*;

public class TripPoint {
    private double lat;
    private double lon;
    private int time;
    private static ArrayList<TripPoint> trip = new ArrayList<>();

    // Constructor
    public TripPoint(int time, double lat, double lon) {
        this.time = time;
        this.lat = lat;
        this.lon = lon;
    }

    // Getters
    public int getTime() { return time; }
    public double getLat() { return lat; }
    public double getLon() { return lon; }
    public static ArrayList<TripPoint> getTrip() { return new ArrayList<>(trip); }

    // Read file and populate trip list
    public static void readFile(String filename) throws FileNotFoundException, IOException {
        trip = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) { 
                    firstLine = false;  // Skip the header row
                    continue; 
                }
                String[] data = line.split(",");
                int time = Integer.parseInt(data[0].trim());
                double lat = Double.parseDouble(data[1].trim());
                double lon = Double.parseDouble(data[2].trim());
                trip.add(new TripPoint(time, lat, lon));
            }
        }
    }

    // Calculate Haversine distance between two points
    public static double haversineDistance(TripPoint a, TripPoint b) {
        final double R = 6371; // Radius of Earth in km
        double latDiff = Math.toRadians(b.getLat() - a.getLat());
        double lonDiff = Math.toRadians(b.getLon() - a.getLon());
        double lat1 = Math.toRadians(a.getLat());
        double lat2 = Math.toRadians(b.getLat());

        double aCalc = Math.sin(latDiff / 2) * Math.sin(latDiff / 2) +
                       Math.cos(lat1) * Math.cos(lat2) *
                       Math.sin(lonDiff / 2) * Math.sin(lonDiff / 2);
        double c = 2 * Math.atan2(Math.sqrt(aCalc), Math.sqrt(1 - aCalc));
        return R * c;
    }

    // Calculate total distance of trip
    public static double totalDistance() {
        double total = 0;
        for (int i = 0; i < trip.size() - 1; i++) {
            total += haversineDistance(trip.get(i), trip.get(i + 1));
        }
        return total;
    }

    // Calculate total time in hours
    public static double totalTime() {
        if (trip.isEmpty()) return 0;
        int startTime = trip.get(0).getTime();
        int endTime = trip.get(trip.size() - 1).getTime();
        return (endTime - startTime) / 60.0;
    }

    // Calculate average speed between two points
    public static double avgSpeed(TripPoint a, TripPoint b) {
        double distance = haversineDistance(a, b);
        double timeDiff = Math.abs(b.getTime() - a.getTime()) / 60.0;
        return timeDiff == 0 ? 0 : distance / timeDiff;
    }
}
