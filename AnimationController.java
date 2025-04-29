
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import javax.swing.Timer;
import java.awt.Image;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationController {
    private final JMapViewer map;
    private final TrailOverlay overlay;
    private final Image icon;
    private Timer timer;
    private List<TripPoint> points;
    private int index;

    public AnimationController(JMapViewer map, TrailOverlay overlay, Image icon) {
        this.map = map;
        this.overlay = overlay;
        this.icon = icon;
    }

    public void start(int durationSec, boolean includeStops) {
        try {
            if (includeStops) {
                TripPoint.readFile("triplog.csv");
                points = TripPoint.getTrip();
            } else {
                TripPoint.h1StopDetection();
                points = TripPoint.getMovingTrip();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Center map
        map.removeAllMapMarkers();
        for (TripPoint p : points) {
            map.addMapMarker(new MapMarkerCircle(
                new Coordinate(p.getLat(), p.getLon()), 1));
        }
        map.setDisplayToFitMapMarkers();
        map.removeAllMapMarkers();

        // Clear previous overlay trail
        overlay.clearTrail();

        int total = points.size();
        int delay = durationSec * 1000 / total;
        index = 0;

        timer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (index >= total) {
                    timer.stop();
                    return;
                }

                Coordinate coord = new Coordinate(
                    points.get(index).getLat(), points.get(index).getLon());

                // extend trail
                overlay.addPoint(coord);

                // move icon
                map.removeAllMapMarkers();
                map.addMapMarker(new IconMarker(coord, icon));
                map.repaint();
                index++;
            }
        });
        timer.start();
    }

    public void reset() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        overlay.clearTrail();
        map.removeAllMapMarkers();
        map.removeAllMapPolygons();
        // re-center
        for (TripPoint p : TripPoint.getTrip()) {
            map.addMapMarker(new MapMarkerCircle(
                new Coordinate(p.getLat(), p.getLon()), 1));
        }
        map.setDisplayToFitMapMarkers();
        map.removeAllMapMarkers();
    }
}