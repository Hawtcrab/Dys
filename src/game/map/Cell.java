package game.map;

import game.actors.Actor;
import game.graphics.GameIcon;

import java.awt.*;

public class Cell {

    public static GameIcon BASIC_FLOOR = new GameIcon(46, Color.WHITE, Color.BLACK);

    int x;
    int y;
    GameIcon terrain;
    public Actor actor;
    Color fg;
    Color bg;

    Cell(int x, int y, GameIcon terrain, Actor actor, Color fg, Color bg) {
        this.x = x;
        this.y = y;
        this.terrain = terrain;
        this.actor = actor;
        this.fg = fg;
        this.bg = bg;
    }

    public GameIcon getTopIcon() {
        if (actor != null) return actor.getIcon();
        return this.terrain;
    }

    public String getHoverText() {
        var text = "x: " + this.x + ", y: " + this.y;
        if (actor != null) text += actor.getHoverText();
        return text;
    }

}
