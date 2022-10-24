package me.dado.gui;

import javax.swing.*;
import java.awt.*;

import me.dado.event.KeyHandler;


public class GameBoard extends JPanel implements Runnable {

    final int originaTileSize = 16; //16x16 assets
    final int scale = 3;

    final int tileSize = originaTileSize * scale; //48x48 assets

    final int maxScreenCollum = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCollum; //768 pixels
    final int screenHeight = tileSize * maxScreenRow; //576 pixels


    // FPS
    int FPS = 144;
    KeyHandler keyHandler = new KeyHandler();

    Thread gameThread;

    // set player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 5;

    public GameBoard() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        //this.setDebugGraphicsOptions(getDebugGraphicsOptions()); DEBUG
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        System.out.println("[DEBUG] [GameBoard]: FPS: " + FPS);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }


    //GAME LOOP the core of the game
    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; //1 sec = 100000000 ns --> 1 sec per frame = 60frame
        double nextDrawTime = System.nanoTime() + drawInterval; // after pass 1sec the game loop start

        while (gameThread != null) {

            // 1 update: update information such as charater position
            update();


            // 2 draw: draw the screen with the updated information
            repaint();


            try {
                double remainingTime = nextDrawTime - System.nanoTime(); // sleep
                remainingTime = remainingTime/100000; // i need ms

                if (remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;



            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }


    }

    public void update() {

        if (keyHandler.upPressed == true) {
            playerY -= playerSpeed;

        } else if (keyHandler.downPressed == true) {
            playerY += playerSpeed;

        } else if (keyHandler.leftPressed == true) {
            playerX -= playerSpeed;

        } else if (keyHandler.rightPressed == true) {
            playerX += playerSpeed;
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g; //cast

        g2d.setColor(Color.BLUE);

        g2d.fillRect(playerX, playerY, tileSize, tileSize);

        g2d.dispose(); // Dispose of this graphics context and relese any sysrem resource that is using

    }
}
