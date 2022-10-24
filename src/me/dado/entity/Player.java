package me.dado.entity;

import me.dado.event.KeyHandler;
import me.dado.gui.GameBoard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GameBoard gameBoard;
    KeyHandler keyHandler;

    public Player(GameBoard gameBoard, KeyHandler keyHandler) {
        this.gameBoard = gameBoard;
        this.keyHandler = keyHandler;

        setDefaultConfigs();
        //getPlayerAssets();
    }

    public void setDefaultConfigs() {
        x = 100;
        y = 100;
        speed = 5;
        direction = "up";
    }

    /*public void getPlayerAssets(){
        try {

            pl = ImageIO.read((getClass().getResourceAsStream("player.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }*/

    public void update() {
        if (keyHandler.upPressed == true) {
            direction = "up";
            y -= speed;

        } else if (keyHandler.downPressed == true) {
            direction = "down";
            y += speed;

        } else if (keyHandler.leftPressed == true) {
            direction = "left";
            x -= speed;

        } else if (keyHandler.rightPressed == true) {
            direction = "right";
            x += speed;
        }
    }

    public void draw(Graphics2D g2d) {

        g2d.setColor(Color.BLUE);
        g2d.drawString("FPS: " + gameBoard.FPS, 10, 25);
        g2d.fillRect(x, y, gameBoard.tileSize, gameBoard.tileSize);

        /*BufferedImage image = null;

        switch (direction){
            case "up":
                image = pl;
                break;
            case "down":
                image = pl;
                break;
            case "left":
                image = pl;
                break;
            case "rigth":
                image = pl;
                break;
        }
        g2d.drawImage(image, x, y, gameBoard.tileSize, gameBoard.tileSize, null);*/

    }


}
