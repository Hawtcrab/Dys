package game.actors;

import game.graphics.GameIcon;

import java.awt.*;

public class Player extends Actor {
    public Player(GameIcon icon, String name) {
        super(icon, name);
    }

    public Player () {
        super(new GameIcon(2, Color.CYAN, Color.BLACK), "player");
    }
}
