package game.graphics;

import java.awt.*;

public class GameIcon {
    private int charIndex;
    private Color baseColor;
    private Color bgColor;

    public GameIcon(int charIndex, Color baseColor, Color bgColor) {
        this.charIndex = charIndex;
        this.baseColor = baseColor;
        this.bgColor = bgColor;
    }

    public int getCharIndex() {
        return charIndex;
    }
    public Color getColor() {
        return baseColor;
    }

    public Color getBgColor() {
        return bgColor;
    }
}
