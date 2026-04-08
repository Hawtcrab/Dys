package game.graphics;

import game.Main;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {

    // The y offset from whence the ability icons start;
    int abilityoffset = 200;

    public StatsPanel() {
        setPreferredSize(new Dimension(200, 0));
        setBackground(Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        var p = Main.currentPlayer;
        if (p == null) return;

        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.PLAIN, 14));

        int y = 20;

        g.drawString("== PLAYER STATS ==", 10, y);
        y += 20;

        g.drawString("Name: " + p.getName(), 10, y); y += 20;
        g.drawString("X: " + p.getX(), 10, y); y += 20;
        g.drawString("Y: " + p.getY(), 10, y); y += 20;

        // add more later:
        g.drawString("HP: 100", 10, y); y += 20;
        g.drawString("Attack: 10", 10, y); y += 20;

        paintAbilities(g);
    }

    private void paintAbilities(Graphics g) {
        var abilities = Main.currentPlayer.abilities.values();

        int counter = 0;

        int slotW = 74;
        int slotH = 94;
        int iconSize = 64;

        FontMetrics fm = g.getFontMetrics();

        for (var ability : abilities) {

            int col = counter % 2;
            int row = counter / 2;

            int slotX = 10 + col * slotW;
            int slotY = abilityoffset + row * slotH + (row * 10);

            // --- center icon inside slot ---
            int iconX = slotX + (slotW - iconSize) / 2;
            int iconY = slotY;

            g.drawImage(ability.icon, iconX, iconY, iconSize, iconSize, null);

            // --- name centered above icon ---
            int nameWidth = fm.stringWidth(ability.name);
            int nameX = slotX + (slotW - nameWidth) / 2;

            g.drawString(ability.name, nameX, slotY -5);

            // --- charge centered below icon ---
            String charge = (int) ability.charge + "%";
            int chargeWidth = fm.stringWidth(charge);
            int chargeX = slotX + (slotW - chargeWidth) / 2;

            g.drawString(charge, chargeX, slotY + iconSize + 15);

            counter++;
        }
    }

}