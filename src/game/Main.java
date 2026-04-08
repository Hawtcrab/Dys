package game;

import game.actors.Player;
import game.graphics.Grid;
import game.graphics.StatsPanel;
import game.map.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
  public static int initialMapSize = 19;
  public static int[] startPosition = new int[] {8,8};

  public static Grid GRID;
  public static StatsPanel STATS;
  public static Player currentPlayer;
  public static Map currentMap = new Map(initialMapSize, initialMapSize, startPosition );
  public static Font gameFont = new Font("Monospaced", Font.PLAIN, 18);


  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("ASCII Grid Demo");

      GRID = new Grid();
      STATS = new StatsPanel();

      JSplitPane split = new JSplitPane(
              JSplitPane.HORIZONTAL_SPLIT,
              GRID,
              STATS
      );

      split.setResizeWeight(1.0); // grid takes most space
      split.setDividerSize(5);

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(split);

      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setResizable(false);
      frame.setVisible(true);

      frame.addKeyListener(new Controls());

      new javax.swing.Timer(100, e -> {
        GRID.repaint();
        STATS.repaint();
      }).start();
    });

    Main.currentPlayer = new Player(currentMap.startx, currentMap.starty);
    Main.currentPlayer.place();
}


}
