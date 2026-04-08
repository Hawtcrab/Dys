package game.actors;

import game.graphics.GameIcon;
import game.mechanics.Ability;

import java.awt.*;
import java.util.Dictionary;
import java.util.HashMap;

public class Player extends Actor {
    public HashMap<String,Ability> abilities = new HashMap<>() {    };



    public Player(int x, int y, GameIcon icon, String name) {
        super(x, y, icon, name);
    }

    public Player (int startx, int starty) {
        super(startx,starty, new GameIcon(2, Color.CYAN, Color.BLACK), "player");
        initializeAbilities();
    }

    @Override
    public String getHoverText() {
        return "\nThat's me!";
    }

    public boolean tryUseAbility(String ability) {
        if (!abilities.containsKey(ability)) {
            return false;
        }
        return abilities.get(ability).charge == 100.0f;
    }

    private void initializeAbilities() {
        abilities.put("count", new Ability("count", "counting", true));
        abilities.put("counton", new Ability("count on", "countingon", true));
        abilities.put("compare", new Ability("compare", "compare", true));

    }

    public boolean canComprehend(int number, boolean countable) {
        return true;
    }

    public boolean canComprehend(float number, boolean countable) {
        return true;
    }
}
