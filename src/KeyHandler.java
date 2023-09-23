import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {

    private final Controller cont;

    public KeyHandler(Controller cont) {
        this.cont = cont;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Direction d = cont.getDirection();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT -> {
                //if it's not the opposite direction
                if (d != Direction.LEFT)
                    cont.setDirection(Direction.RIGHT);
            }
            case KeyEvent.VK_UP -> {
                if (d != Direction.DOWN)
                    cont.setDirection(Direction.UP);
            }
            case KeyEvent.VK_LEFT -> {
                if (d != Direction.RIGHT)
                    cont.setDirection(Direction.LEFT);
            }
            case KeyEvent.VK_DOWN -> {
                if (d != Direction.UP)
                    cont.setDirection(Direction.DOWN);
            }
            default -> {
            }
        }
    }

}
