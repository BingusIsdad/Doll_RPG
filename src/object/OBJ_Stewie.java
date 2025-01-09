package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Stewie extends SuperObject{
    GamePanel gp;
    public OBJ_Stewie(GamePanel gp){
        this.gp = gp;
        name = "Stewie";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/stew.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }


    }
}
