package game;

import game.actors.Player;
import game.graphics.Grid;
import game.map.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
  public static int initialMapSize = 19;
  public static int[] startPosition = new int[] {8,8};

  public static Grid GRID;
  public static Player currentPlayer;
  public static Map currentMap = new Map(initialMapSize, initialMapSize, startPosition );
  public static Font gameFont = new Font("Monospaced", Font.PLAIN, 18);


  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
    JFrame frame = new JFrame("ASCII Grid Demo");
    GRID = new Grid();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(GRID);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setResizable(false);
      var Controls = new Controls();
      frame.addKeyListener(Controls);
  });
    currentPlayer = new Player();
    Main.currentPlayer.setPosition(Main.startPosition[0], Main.startPosition[1]);
}


}
