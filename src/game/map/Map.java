package game.map;

import game.Main;
import game.actors.Player;

import java.awt.*;

public class Map {
    public int width;
    public int height;
    public Cell[][] cells;
    public int startx;
    public int starty;

    public Map (int w, int h, int[] startPosition) {
        width = w; height = h;
        cells = new Cell[w][h];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            cells[x][y] = new Cell(x,y,Cell.BASIC_FLOOR, null, Color.WHITE, Color.BLACK);
            }
        }
        startx = startPosition[0];
        starty = startPosition[1];

    }

}
