import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.Coordinate;

public class TrailOverlay extends JComponent {
    private static final long serialVersionUID = 1L;
    private final JMapViewer map;
    private final List<Coordinate> trail = new ArrayList<>();

    public TrailOverlay(JMapViewer map) {
        this.map = map;
        setOpaque(false);
    }

    public void addPoint(Coordinate c) {
        trail.add(c);
        repaint();
    }

    public void clearTrail() {
        trail.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0;
        g.setColor(Color.RED);
        g.setStroke(new BasicStroke(2f));

        Point prev = null;
        for (Coordinate coord : trail) {
            Point p = map.getMapPosition(coord, false);
            if (p != null && prev != null) {
                g.drawLine(prev.x, prev.y, p.x, p.y);
            }
            prev = p;
        }
    }
}
