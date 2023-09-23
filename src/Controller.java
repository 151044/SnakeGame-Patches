import java.awt.*;
import java.util.ArrayList;
import java.util.List;


// Controls all the game logic.
public class Controller extends Thread {
    private Direction direction;
    private final List<List<SquareData>> squares;
    private Point head;
    private int length = 3;
    private final long GAME_SPEED = 50;
    private final List<Point> positions = new ArrayList<>();
    private Point foodPosition;
    public Controller(Point positionDepart, List<List<SquareData>> lists, Window window) {
        squares = lists;

        head = new Point(positionDepart.x(), positionDepart.y());
        direction = Direction.RIGHT;

        Point headPos = new Point(head.x(), head.y());
        positions.add(headPos);

        foodPosition = new Point(window.height() - 1, window.width() - 1);
        spawnFood(foodPosition);

    }

    //Important part :
    public void run() {
        while (true) {
            move(direction);
            checkCollision();
            lightSquares();
            deleteTail();
            pause();
        }
    }

    /**
     * Pauses the application for some time.
     */
    private void pause() {
        try {
            sleep(GAME_SPEED);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Checking if the snake bites itself or is eating
    private void checkCollision() {
        Point criticalPos = positions.get(positions.size() - 1);
        for (int i = 0; i <= positions.size() - 2; i++) {
            boolean biteItself = criticalPos.x() == positions.get(i).x() && criticalPos.y() == positions.get(i).y();
            if (biteItself) {
                stopTheGame();
            }
        }

        boolean eatingFood = criticalPos.x() == foodPosition.x() && criticalPos.y() == foodPosition.y();
        if (eatingFood) {
            System.out.println("Yummy!");
            length = length + 1;
            foodPosition = getUnoccupied();

            spawnFood(foodPosition);
        }
    }

    //Stops the game
    private void stopTheGame() {
        System.out.println("You lose!");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // we're exiting anyways, ignore
        }
        System.exit(0);
    }

    //Put food in a position and displays it
    private void spawnFood(Point pos) {
        squares.get(pos.x()).get(pos.y()).light(Color.BLUE);
    }

    //return a position not occupied by the snake
    private Point getUnoccupied() {
        Point p;
        do  {
            int ranX = (int) (Math.random() * 19);
            int ranY = (int) (Math.random() * 19);
            p = new Point(ranX, ranY);
        } while (positions.contains(p));
        return p;
    }

    //Moves the head of the snake and refreshes the positions in the List.
    private void move(Direction direction) {
        switch (direction) {
            case DOWN -> {
                head = new Point(head.x(), (head.y() + 1) % 20);
                positions.add(new Point(head.x(), head.y()));
            }
            case UP -> {
                if (head.y() - 1 < 0) {
                    head = new Point(head.x(), 19);
                } else {
                    head = new Point(head.x(), Math.abs(head.y() - 1) % 20);
                }
                positions.add(new Point(head.x(), head.y()));
            }
            case LEFT -> {
                if (head.x() - 1 < 0) {
                    head = new Point(19, head.y());
                } else {
                    head = new Point(Math.abs(head.x() - 1) % 20, head.y());
                }
                positions.add(new Point(head.x(), head.y()));
            }
            case RIGHT -> {
                head = new Point(Math.abs(head.x() + 1) % 20, head.y());
                positions.add(new Point(head.x(), head.y()));
            }
            case NONE -> {
                // do nothing
            }
        }
    }

    // Light the needed squares
    private void lightSquares() {
        for (Point t : positions) {
            int y = t.x();
            int x = t.y();
            squares.get(x).get(y).light(Color.DARK_GRAY);
        }
    }

    //Refreshes the tail of the snake, by removing the superfluous data in positions arraylist
    //and refreshing the display of the things that is removed
    private void deleteTail() {
        int cmpt = length;
        for (int i = positions.size() - 1; i >= 0; i--) {
            if (cmpt == 0) {
                Point t = positions.get(i);
                squares.get(t.y()).get(t.x()).light(Color.WHITE);
            } else {
                cmpt--;
            }
        }
        cmpt = length;
        for (int i = positions.size() - 1; i >= 0; i--) {
            if (cmpt == 0) {
                positions.remove(i);
            } else {
                cmpt--;
            }
        }
    }

    public void setDirection(Direction d) {
        this.direction = d;
    }

    public Direction getDirection() {
        return direction;
    }
}
