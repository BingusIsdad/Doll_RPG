package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Demon extends Entity{
    public NPC_Demon(GamePanel gp){
        super(gp);
        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
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
    public void setDialogue(){
        dialogues[0] = "Graahhh I'm a demon!!!11!1!";

    }
    public void setAction(){
        actionLockCounter ++;
        if(actionLockCounter == 120) {


            Random random = new Random();
            int i = random.nextInt(100) + 1; //pick up a number from 1 to 100
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }

    }
    public void speak(){
        gp.ui.currentDialogue = dialogues[0];
    }
}
