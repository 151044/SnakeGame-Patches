import javax.swing.*;
import java.awt.*;

public class SquarePanel extends JPanel {

    public SquarePanel(Color d) {
        this.setBackground(d);
    }

    public void setColor(Color d) {
        this.setBackground(d);
        this.repaint();
    }

}

