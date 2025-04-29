import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;
import org.openstreetmap.gui.jmapviewer.Coordinate;


public class Driver {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.setProperty(
            "http.agent",
            "Project5MapAnimator/1.0 (thaufin.altaf-1@ou.edu)"
        );

     // Read and filter trip data (h1)
        TripPoint.readFile("triplog.csv");
        TripPoint.h1StopDetection();

        // Load raccoon icon
        Image raccoon = ImageIO.read(new File("raccoon.png"));

        // Create frame
        JFrame frame = new JFrame("Thaufin Altaf - Trip Animation");
        frame.setSize(1868, 615);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Control panel
        ControlPanel controls = new ControlPanel();
        frame.add(controls, BorderLayout.NORTH);

        // Map viewer
        JMapViewer map = new JMapViewer();
        map.setLayout(null); // use absolute for overlay
        frame.add(map, BorderLayout.CENTER);

        // Trail overlay
        TrailOverlay overlay = new TrailOverlay(map);
        overlay.setBounds(0, 0, map.getWidth(), map.getHeight());
        map.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                overlay.setSize(map.getSize());
            }
        });
        map.add(overlay);

        // Initial center on full trip
        for (TripPoint p : TripPoint.getTrip()) {
            map.addMapMarker(new MapMarkerCircle(
                new Coordinate(p.getLat(), p.getLon()), 1));
        }
        map.setDisplayToFitMapMarkers();
        map.removeAllMapMarkers();

        // Animation controller
        AnimationController animator = new AnimationController(map, overlay, raccoon);
        controls.setAnimator(animator);

        frame.setVisible(true);
    }
}


