package game.map;

import game.actors.Actor;
import game.graphics.GameIcon;

import java.awt.*;

public class Cell {

    public static GameIcon BASIC_FLOOR = new GameIcon(46, Color.WHITE, Color.BLACK);

    GameIcon terrain;
    public Actor actor;
    Color fg;
    Color bg;

    Cell(GameIcon terrain, Actor actor, Color fg, Color bg) {
        this.terrain = terrain;
        this.actor = actor;
        this.fg = fg;
        this.bg = bg;
    }

    public GameIcon getTopIcon() {
        if (actor != null) return actor.getIcon();
        return this.terrain;
    }

}
