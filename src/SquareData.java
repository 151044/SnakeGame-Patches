import java.awt.*;
import java.util.List;

public class SquareData {


    // Static list of colors
    private static final List<Color> COLORS = List.of(Color.DARK_GRAY, Color.BLUE, Color.WHITE);
    private SquarePanel square;

    public SquareData(int color) {
        square = new SquarePanel(COLORS.get(color));
    }

    public void light(int c) {
        square.setColor(COLORS.get(c));
    }

    public SquarePanel getPanel() {
        return square;
    }
}
