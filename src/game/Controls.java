package game;

import game.actors.Actor;
import game.map.Cell;
import game.mechanics.GameTime;

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

    public boolean tryMoveByKey(int xoffset, int yoffset) {
        var p = Main.currentPlayer;
        if (CanMoveThere(Main.currentPlayer, p.getX() + xoffset, p.getY() + yoffset)) {
            Main.currentPlayer.setPositionOffseted(xoffset, yoffset, true);
            GameTime.tickTurn();
            return true;
        }
        return false;
    }

    public static boolean CanMoveThere(Actor a, int x, int y) {
        Cell cell = null;
        if (x < Main.currentMap.width && y < Main.currentMap.height && x >= 0 && y >= 0) {
            cell = Main.currentMap.cells[x][y];
        }
        var result = CanMoveThere(a, cell);
        if (result == MovementResult.BlockedByActor && cell != null) {
            cell.actor.onBumpedIntoBy(a);
            return true;
        }
        return result == MovementResult.Valid;
    }

    /// In case that an actor tries to move into itself, returns false, to prevent any further movement logic from firing.
    public static MovementResult CanMoveThere(Actor a, Cell c) {
        if (c == null) return MovementResult.BlockedByBoundaries;
        if (c.actor == null) return MovementResult.Valid;
        if (a == c.actor) return MovementResult.InvalidMovement;
        return MovementResult.BlockedByActor;
    }
}

