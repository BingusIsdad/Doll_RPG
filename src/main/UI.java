package main;
import object.OBJ_Armor;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font earthb, earthbig;
    BufferedImage armorFull, armorHalf, armorEmpty;
    //BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; //0: the first screen, 1: the second screen
    public UI(GamePanel gp) {
        this.gp = gp;
        earthb = new Font("Earthbound Dialogue", Font.PLAIN, 40);
        earthbig = new Font("Earthbound Dialogue", Font.PLAIN, 80);
        //Create HUD Object
        SuperObject armor = new OBJ_Armor(gp);
        armorFull = armor.image;
        armorHalf = armor.image2;
        armorEmpty = armor.image3;

    }


    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(earthb);
        g2.setColor(Color.white);
        //Title state
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //Play State
        if(gp.gameState == gp.playState){
            drawPlayerLife();

        }
        //Pause State
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        //Dialogue State
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }
    }
    public void drawPlayerLife(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        while(i<gp.player.maxArmor/2){
            g2.drawImage(armorEmpty,x,y,null);
            i++;
            x+=gp.tileSize;
        }
    }
    public void drawTitleScreen(){
        if(titleScreenState == 0){
            g2.setColor(new Color(24,0,56));
            g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
            //Title Name
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
            String text = "Doll RPG";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;
            //Shadow
            g2.setColor(new Color(200,200,112));
            g2.drawString(text, x+5,y+5);
            //Main Color
            g2.setColor(new Color(200,96,112));
            g2.drawString(text,x,y);
            //Doll Image
            x = gp.screenWidth/2 - (gp.tileSize*2)/2;
            y += gp.tileSize*2;
            g2.drawImage(gp.player.down1,x,y,gp.tileSize*2,gp.tileSize*2,null);
            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize*4;
            g2.drawString(text,x,y);
            if(commandNum == 0){
                g2.drawString(">", x-gp.tileSize,y);
            }
            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text,x,y);
            if(commandNum == 1){
                g2.drawString(">", x-gp.tileSize,y);
            }
            text = "GOODBYE";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text,x,y);
            if(commandNum == 2){
                g2.drawString(">", x-gp.tileSize,y);
            }
        }
        else if(titleScreenState == 1){
            //CLASS SELECTION SCREEN
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));
            String text = "Select your class";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;
            g2.drawString(text,x,y);
            text = "Brawler Doll";
            x = getXforCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text,x,y);
            if(commandNum == 0){
                g2.drawString(">",x-gp.tileSize,y);
            }
            text = "Sneaky Doll";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text,x,y);
            if(commandNum == 1){
                g2.drawString(">",x-gp.tileSize,y);
            }
            text = "Magical Doll";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text,x,y);
            if(commandNum == 2){
                g2.drawString(">",x-gp.tileSize,y);
            }
            text = "Go Back";
            x = getXforCenteredText(text);
            y += gp.tileSize*2;
            if(commandNum == 3){
                g2.drawString(">",x-gp.tileSize,y);
            }
        }

    }
    public void drawPauseScreen(){
        String text = "PAUSED";
        int x = getXforCenteredText(text);

        int y = gp.screenHeight/2;
        g2.drawString(text,x,y);
    }
    public void drawDialogueScreen(){
        //WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x,y,width,height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
        x += gp.tileSize;
        y += gp.tileSize;
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y+= 40;
        }
    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height, 35, 35);
        c = new Color(136,144,144);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10,height-10,25,25);
        c = new Color(240,240,240);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x+7,y+7,width-15,height-15,15,15);
    }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}