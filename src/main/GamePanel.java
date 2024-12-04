package main;
import javax.swing.JPanel;
import java.awt.*;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    //Screen Settings
    final int originalTileSize = 16; //16x16 babyyyy
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48 x 48 tile size
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;  //768 px
    public final int screenHeight = tileSize * maxScreenRow; //576 px
    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    //FPS
    int FPS = 60;
    //SYSTEM
    TileManager tileM = new TileManager(this);
    main.KeyHandler keyH = new main.KeyHandler();
    Sound music = new Sound();
    Sound se = new Sound();
    public main.CollisionChecker cChecker = new main.CollisionChecker(this);
    public main.AssetSetter aSetter = new main.AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;
    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
        playMusic(0);
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
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //DEBUG
        long drawStart = 0;
        drawStart = System.nanoTime();

        //TILE
        tileM.draw(g2);
        //OBJECT
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }
        //PLAYER
        player.draw(g2);
        //UI
        ui.draw(g2);
        long drawEnd = System.nanoTime();
        long passed = drawEnd - drawStart;
        g2.setColor(Color.white);
        g2.drawString("Draw Time: "+passed,10,400);
        System.out.println("Draw Time: "+ passed);
        g2.dispose();
    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}
