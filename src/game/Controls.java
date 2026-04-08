package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controls extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> tryMoveByKey(0, -1);
            case KeyEvent.VK_RIGHT -> tryMoveByKey(1, 0);
            case KeyEvent.VK_DOWN -> tryMoveByKey(0, 1);
            case KeyEvent.VK_LEFT -> tryMoveByKey(-1, 0);
        }
        Main.GRID.repaint();

    }

    public void tryMoveByKey(int yoffset, int xoffset) {
        Main.currentPlayer.setPosition(xoffset, yoffset);
    }
}
