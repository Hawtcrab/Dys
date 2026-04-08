package game.map;

import game.Main;
import game.actors.Player;

import java.awt.*;

public class Map {
    public int width;
    public int height;
    public Cell[][] cells;

    public Map (int w, int h, int[] startposition) {
        width = w; height = h;
        cells = new Cell[w][h];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            cells[x][y] = new Cell(Cell.BASIC_FLOOR, null, Color.WHITE, Color.BLACK);
            }
        }

    }

}
