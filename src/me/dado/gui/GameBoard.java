package me.dado.gui;

import javax.swing.*;
import java.awt.*;

import me.dado.entity.Player;
import me.dado.event.KeyHandler;
import me.dado.tile.TileManager;


public class GameBoard extends JPanel implements Runnable {

    final int originaTileSize = 16; //16x16 assets
    final int scale = 3;

    public int tileSize = originaTileSize * scale; //48x48 assets

    final int maxScreenCollum = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCollum; //768 pixels
    final int screenHeight = tileSize * maxScreenRow; //576 pixels


    // FPS
    public int FPS = 60;

    TileManager tileManager = new TileManager(this);
    public int drawFPS = 0;
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    Player player = new Player(this, keyHandler);

    // set player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 7;

    public GameBoard() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        //this.setDebugGraphicsOptions(getDebugGraphicsOptions()); DEBUG
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }




    @Override
    public void run(){
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long currentTime;
        long lastTime = System.nanoTime();
        long timer = 0;

        //System.out.println(System.nanoTime());

        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval; //how much time is it pass
            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if (delta >= 1){

                // 1 update: update information such as charater position
                update();

                // 2 draw: draw the screen with the updated information
                repaint();
                delta--;
                drawFPS++;
            }

            if (timer >= 1000000000){
                System.out.println("[DEBUG] [GameBoard]: FPS: " + drawFPS);
                drawFPS = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();  //
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g; //cast
        tileManager.draw(g2d);
        player.draw(g2d);


        g2d.dispose(); // Dispose of this graphics context and relese any sysrem resource that is using

    }
}
