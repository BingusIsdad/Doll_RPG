package entity;

import main.GamePanel;

public class NPC_Demon extends Entity{
    public NPC_Demon(GamePanel gp){
        super(gp);
        direction = "down";
        speed = 1;
        getImage();
    }
    public void getImage(){

        up1 = setup("/npc/demonup1");
        up2 = setup("/npc/demonup2");
        down1 = setup("/npc/demondown1");
        down2 = setup("/npc/demondown2");
        left1 = setup("/npc/demonleft1");
        left2 = setup("/npc/demonleft2");
        right1 = setup("/npc/demonright1");
        right2 = setup("/npc/demonright2");
    }

}
