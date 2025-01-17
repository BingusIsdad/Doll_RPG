package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_StewieLaser extends Projectile {
    GamePanel gp;
    public OBJ_StewieLaser(GamePanel gp){
        super(gp);
        this.gp = gp;
        //name = "Laser";
        speed = 5;
        maxArmor = 80;
        armor = maxArmor;
        //attack = 2;
        //useCost = 0;
        //alive = false;
        getImage();
    }
    public void getImage
}
