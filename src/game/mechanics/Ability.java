package game.mechanics;

import java.awt.image.BufferedImage;

public class Ability {

    public String name;
    public float charge;
    public boolean unlocked = false;
    public BufferedImage icon;

    public Ability(String name, String image, boolean startunlocked) {
        this.name = name;
        unlocked = startunlocked;
        if (startunlocked)
            charge = 100.0f;
        try {
            icon = javax.imageio.ImageIO.read(
                    getClass().getResource("/images/abilities/" + image + ".png")
            );
        } catch (Exception e) {
            try  {
                icon = javax.imageio.ImageIO.read(
                        getClass().getResource("/images/abilities/default.png"));
            }catch (Exception e2) {
                e.printStackTrace();
                e2.printStackTrace();
            }

        }

    }

    public void setCharge(float charge) {
        this.charge = Math.clamp(charge, 0, 100);
    }

    public void offsetCharge (float offset) {
        this.charge = Math.clamp(charge + offset, 0, 100);
    }

}
