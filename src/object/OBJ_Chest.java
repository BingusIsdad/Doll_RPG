package object;
import main.GamePanel;

import java.io.IOException;
import javax.imageio.ImageIO;
public class OBJ_Chest extends SuperObject{
    GamePanel gp;
    public OBJ_Chest(GamePanel gp){
        this.gp = gp;
        name = "Chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Chest?.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
