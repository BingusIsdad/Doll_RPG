package main;

import entity.NPC_Demon;
import object.*;

public class AssetSetter {
GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;

    }
    public void setObject(){
        gp.obj[0] = new OBJ_Stewie(gp);
        gp.obj[0].worldX = gp.tileSize*23;
        gp.obj[0].worldY = gp.tileSize*7;

    }
    public void setNPC(){
        gp.npc[0] = new NPC_Demon(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;
    }
}
