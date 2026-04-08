package game.graphics;

import game.Main;
import game.map.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class Grid extends JPanel {
    private BufferedImage fontImage;

    private final int gapSize = 2;
    private final int tileWidth = 16;
    private final int tileHeight = 32;

    private int mouseX = -1;
    private int mouseY = -1;

    private Rectangle currentTooltipBounds = null;

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

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Rectangle oldBounds = currentTooltipBounds;

                mouseX = e.getX();
                mouseY = e.getY();

                // repaint old tooltip area (to erase it)
                if (oldBounds != null) {
                    repaint(oldBounds);
                }

                // repaint area around new mouse position
                repaint(mouseX - 50, mouseY - 50, 100, 100);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintTiles(g);
        paintHoverText(g);
    }

    private void paintTiles(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        var map = Main.currentMap;
        if (map == null) return;

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        for (int y = 0; y < map.height; y++) {
            for (int x = 0; x < map.width; x++) {
                Cell cell = map.cells[x][y];

                int px = x * (tileWidth + gapSize);
                int py = y * (tileHeight + gapSize);

                g2.setColor(cell.getTopIcon().getBgColor());
                g2.fillRect(px, py, tileWidth + gapSize, tileHeight + gapSize);

                int index = cell.getTopIcon().getCharIndex();

                int sx = (index % tilesPerRow) * tileWidth;
                int sy = (index / tilesPerRow) * tileHeight;

                BufferedImage glyph = fontImage.getSubimage(sx, sy, tileWidth, tileHeight);

                BufferedImage tinted = new BufferedImage(tileWidth, tileHeight, BufferedImage.TYPE_INT_ARGB);
                Graphics2D tg = tinted.createGraphics();

                tg.drawImage(glyph, 0, 0, null);

                tg.setComposite(AlphaComposite.SrcAtop);
                tg.setColor(cell.getTopIcon().getColor());
                tg.fillRect(0, 0, tileWidth, tileHeight);

                tg.dispose();

                g2.drawImage(tinted, px, py, null);
            }
        }
    }

    private void paintHoverText(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        int tileX = mouseX / (tileWidth + gapSize);
        int tileY = mouseY / (tileHeight + gapSize);

        int localX = mouseX % (tileWidth + gapSize);
        int localY = mouseY % (tileHeight + gapSize);

        boolean insideTile = localX < tileWidth && localY < tileHeight;

        Cell hovered = null;

        if (insideTile &&
                tileX >= 0 && tileX < Main.currentMap.width &&
                tileY >= 0 && tileY < Main.currentMap.height) {

            hovered = Main.currentMap.cells[tileX][tileY];
        }

        // reset tooltip if nothing hovered
        currentTooltipBounds = null;

        if (hovered != null) {
            String text = hovered.getHoverText();
            if (text == null) return;

            g2.setFont(g2.getFont().deriveFont(14f));
            FontMetrics fm = g2.getFontMetrics();

            int padding = 4;
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getHeight();

            int tx = mouseX + 12;
            int ty = mouseY + 12;

            Rectangle bounds = new Rectangle(
                    tx,
                    ty,
                    textWidth + padding * 2,
                    textHeight + padding * 2
            );

            // draw background
            g2.setColor(new Color(0, 0, 0, 200));
            g2.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

            // draw text
            g2.setColor(Color.WHITE);
            g2.drawString(text, tx + padding, ty + padding + fm.getAscent());

            currentTooltipBounds = bounds;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        var map = Main.currentMap;

        if (map == null) return new Dimension(800, 450);

        return new Dimension(
                map.width * tileWidth + (map.width - 1) * gapSize,
                map.height * tileHeight + (map.height - 1) * gapSize
        );
    }
}