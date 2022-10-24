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
        getPlayerAssets();
    }

    public void setDefaultConfigs() {
        x = 100;
        y = 100;
        speed = 5;
        direction = "down";
    }

    public void getPlayerAssets(){
        try {

            up_left = ImageIO.read((getClass().getResourceAsStream("./player/up_left.gif")));
            up_right = ImageIO.read((getClass().getResourceAsStream("./player/up_right.gif")));
            up = ImageIO.read((getClass().getResourceAsStream("./player/up.gif")));
            down_left = ImageIO.read((getClass().getResourceAsStream("./player/down_left.gif")));
            down_right = ImageIO.read((getClass().getResourceAsStream("./player/down_right.gif")));
            down = ImageIO.read((getClass().getResourceAsStream("./player/down.gif")));
            left = ImageIO.read((getClass().getResourceAsStream("./player/left.gif")));
            right = ImageIO.read((getClass().getResourceAsStream("./player/right.gif")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

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

        //g2d.setColor(Color.BLUE);
        //g2d.drawString("FPS: " + gameBoard.FPS, 10, 25);
        //g2d.fillRect(x, y, gameBoard.tileSize, gameBoard.tileSize);

        BufferedImage image = null;

        switch (direction){
            case "up":
                image = up;
                break;
            case "down":
                image = down;
                break;
            case "left":
                image = left;
                break;
            case "rigth":
                image = right;
                break;
        }
        g2d.drawImage(image, x, y, gameBoard.tileSize, gameBoard.tileSize, null);

    }


}
