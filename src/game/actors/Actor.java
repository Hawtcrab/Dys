package game.actors;

import game.Main;
import game.graphics.GameIcon;

public class Actor {
    private GameIcon icon;
    private String name;
    private int[] position = new int[2];

    public Actor (GameIcon icon, String name) {
        this.icon = icon;
        this.name = name;
        this.position = position;
    }

    public GameIcon getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public int[] getPosition() {
        return this.position;
    }

    public void setPosition(int offsetx, int offsety) {
        var newpos = new int[] {position[0] + offsetx, position[1] + offsety};
        setPosition(newpos);
    }

    public void setPosition(int[] newpos) {

        if (position != null) {
            Main.currentMap.cells[position[0]][position[1]].actor = null;
        }
        Main.currentMap.cells[newpos[0]][newpos[1]].actor = this;
        this.position = newpos;
    }
}
