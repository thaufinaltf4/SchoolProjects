
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private final JComboBox<Integer> durationBox;
    private final JCheckBox includeStops;
    private final JButton playReset;
    private AnimationController animator;

    public ControlPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        durationBox = new JComboBox<>(new Integer[]{15, 30, 60, 90});
        durationBox.setSelectedIndex(1);
        add(durationBox);

        includeStops = new JCheckBox("Include stops");
        includeStops.setSelected(false);
        add(includeStops);

        playReset = new JButton("Play");
        add(playReset);

        playReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (playReset.getText().equals("Play")) {
                    playReset.setText("Reset");
                    animator.start(
                        (int) durationBox.getSelectedItem(),
                        includeStops.isSelected());
                } else {
                    playReset.setText("Play");
                    animator.reset();
                }
            }
        });
    }

    void setAnimator(AnimationController a) {
        this.animator = a;
    }
}
