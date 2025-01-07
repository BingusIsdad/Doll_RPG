package object;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
public class OBJ_Armor extends SuperObject {
    GamePanel gp;
    public OBJ_Armor(GamePanel gp){
        this.gp = gp;
        name = "Armor";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/armorFull.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/armorHalf.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/armorEmpty.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
