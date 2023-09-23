import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


class Window extends JFrame {
    private static List<List<SquareData>> grid;
    public static int width = 20;
    public static int height = 20;

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

        // initial position of the snake
        Point position = new Point(10, 10);
        // passing this value to the controller
        controller = new Controller(new Point(10, 10), grid);
        //Let's start the game now
        controller.start();

        // Links the window to the KeyboardListener
        this.addKeyListener(new KeyHandler(controller));
    }
}
