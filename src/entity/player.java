package entity;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class player extends  entity{
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX=gp.screenWidth/2;
        screenY=gp.screenHeight/2;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize*23;
        worldY= gp.tileSize*21;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/platter/pixil-frame-2.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/platter/pixil-frame-3.png"));
        down1 = ImageIO.read(getClass().getResourceAsStream("/platter/pixil-frame-0.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/platter/pixil-frame-1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/platter/pixil-frame-6.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/platter/pixil-frame-7.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/platter/pixil-frame-4.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/platter/pixil-frame-5.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";
                worldY -= speed;
            } else if (keyH.downPressed == true) {
                direction = "down";
                worldY += speed;
            } else if (keyH.leftPressed == true) {
                direction = "left";
                worldX -= speed;
            } else if (keyH.rightPressed == true) {
                direction = "right";
                worldX += speed;
            }
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2){
//g2.setColor(Color.white);
//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        switch(direction){
            case "up":
                if(spriteNum ==1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum ==1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
               if(spriteNum ==1){
                   image = left1;
               }
               if(spriteNum == 2){
                   image = left2;
               }
                break;
            case "right":
                if(spriteNum ==1){
                    image = right1;
               }
                if(spriteNum == 2){
                   image = right2;
                }
                break;
        }
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }
}
