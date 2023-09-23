import java.awt.*;

public class SquareData {

    private final SquarePanel square;

    public SquareData(Color color) {
        square = new SquarePanel(color);
    }

    public void light(Color color) {
        square.setColor(color);
    }

    public SquarePanel getPanel() {
        return square;
    }
}
