import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Window extends JFrame {
    private List<List<SquareData>> grid;
    private int width = 20;
    private int height = 20;

	private final Controller controller;

    public Window() {
        grid = new ArrayList<>();
        List<SquareData> data;

        for (int i = 0; i < width; i++) {
            data = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                SquareData c = new SquareData(2);
                data.add(c);
            }
            grid.add(data);
        }

        // Setting up the layout of the panel
        getContentPane().setLayout(new GridLayout(20, 20, 0, 0));

        // Start & pauses all threads, then adds every square of each thread to the panel
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                getContentPane().add(grid.get(i).get(j).getPanel());
            }
        }
        controller = new Controller(new Point(10, 10), grid, this);
        //Let's start the game now
        controller.start();

        // Links the window to the KeyboardListener
        this.addKeyListener(new KeyHandler(controller));
    }

    public int width() {
        return width;
    }
    public int height() {
        return height;
    }
}
