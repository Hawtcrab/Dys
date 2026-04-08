package game.graphics;

import game.Controls;
import game.map.Cell;
import game.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Grid extends JPanel {
    private BufferedImage fontImage;

    private final int gapSize = 2;
    private final int tileWidth = 16;
    private final int tileHeight = 32;

    private int tilesPerRow;

    public Grid() {
        try {
            fontImage = javax.imageio.ImageIO.read(
                    getClass().getResource("/images/bitmap.png")
            );

            tilesPerRow = fontImage.getWidth() / tileWidth;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        var map = Main.currentMap;
        if (map == null) return;

        // 🔹 Keep pixels sharp
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        for (int y = 0; y < map.height; y++) {
            for (int x = 0; x < map.width; x++) {
                Cell cell = map.cells[y][x];

                int px = x * (tileWidth + gapSize);
                int py = y * (tileHeight + gapSize);

                // Background
                g2.setColor(cell.getTopIcon().getBgColor());
                g2.fillRect(px, py, tileWidth + gapSize, tileHeight + gapSize);

                // Glyph lookup
                int index = cell.getTopIcon().getCharIndex();

                int sx = (index % tilesPerRow) * tileWidth;
                int sy = (index / tilesPerRow) * tileHeight;

                // Draw glyph
// Extract glyph
                BufferedImage glyph = fontImage.getSubimage(sx, sy, tileWidth, tileHeight);

// Create tinted version
                BufferedImage tinted = new BufferedImage(tileWidth, tileHeight, BufferedImage.TYPE_INT_ARGB);
                Graphics2D tg = tinted.createGraphics();

// Draw original glyph
                tg.drawImage(glyph, 0, 0, null);

// Apply tint
                tg.setComposite(AlphaComposite.SrcAtop);
                tg.setColor(cell.getTopIcon().getColor());
                tg.fillRect(0, 0, tileWidth, tileHeight);

                tg.dispose();

// Draw to screen
                g2.drawImage(tinted, px, py, null);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        var map = Main.currentMap;

        if (map == null) return new Dimension(800, 450);

        return new Dimension(
                map.width * tileWidth,
                map.height * tileHeight
        );
    }
}