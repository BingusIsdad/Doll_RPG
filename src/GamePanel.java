package main;
import javax.swing.JPanel;
import java.awt.*;
import entity.player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    //Screen Settings
    final int originalTileSize = 16; //16x16 babyyyy
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48 x 48 tile size
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;  //768 px
    final int screenHeight = tileSize * maxScreenRow; //576 px
    //FPS
    int FPS = 60;
    TileManager tileM = new TileManager(this);
    main.KeyHandler keyH = new main.KeyHandler();
    Thread gameThread;
    player player = new player(this, keyH);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
   // public void run() {
     //   double drawInterval = 1000000000 / FPS; //0.01666 seconds
       // double nextDrawTime = System.nanoTime() + drawInterval;
        //while (gameThread != null) {
            //System.out.println("Game loop is on");
            //1 UPDATE: update info like character positions
          //  update();
            //2 DRAW: draw the screen w/updated info
            //repaint();
            //double remainingTime = nextDrawTime - System.nanoTime();
            //remainingTime = remainingTime / 1000000;
            //if (remainingTime < 0) {
              //  remainingTime = 0;
            //}
            //try {
              //  Thread.sleep((long) remainingTime);
               // nextDrawTime += drawInterval;
            //} catch (InterruptedException e) {
              //  e.printStackTrace();
           // }
        //}
    //}
    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime- lastTime)/drawInterval;
            timer +=(currentTime-lastTime);
            lastTime = currentTime;
            if (delta >= 1){
                update();
                repaint();
                delta--;
drawCount++;
            }
            if(timer>=1000000000){
System.out.println("FPS: "+drawCount);
drawCount = 0;
timer = 0;
            }
        }
    }
        public void update () {
player.update();
        }
        public void paintComponent (Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            tileM.draw(g2);
            player.draw(g2);
            g2.dispose();
        }
    }
