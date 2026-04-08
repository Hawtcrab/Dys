package game.actors;

import game.Main;
import game.graphics.GameIcon;

public class Actor {
    private GameIcon icon;
    private int x, y;
    private String name;

    public Actor (int x, int y, GameIcon icon, String name) {
        this.icon = icon;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public GameIcon getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return this.x;
    }
    public int getY() { return this.y;}

    public void setPositionOffseted(int offsetx, int offsety, boolean offset) {
        if (offset)
            setPosition(x + offsetx, y + offsety);
        else
            setPosition(offsetx,offsety);
    }

    public void setPosition(int newx, int newy) {
        var map = Main.currentMap;

        // bounds check
        if (newx < 0 || newx >= map.width ||
                newy < 0 || newy >= map.height) {
            return;
        }

        // remove from old cell
        if (map.cells[x][y].actor == this) {
            map.cells[x][y].actor = null;
        }

        // update position
        this.x = newx;
        this.y = newy;

        // place in new cell
        if (map.cells[newx][newy].actor != null) {
            return; // or handle combat, etc.
        }
        map.cells[x][y].actor = this;
    }

    public void place() {
        Main.currentMap.cells[x][y].actor = this;
    }

    public String getHoverText() {
        return "This is a thing.";
    }

    public void onBumpedIntoBy (Actor a) {

    }

    public String bumpText () {
        return "Test";
    }

    public void onTick() {

    }
}
