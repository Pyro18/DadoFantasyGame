package me.dado.entity;

import me.dado.event.KeyHandler;
import me.dado.gui.GameBoard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    public void getPlayerAssets() {
        try {

            up = ImageIO.read((getClass().getResourceAsStream("/player/up.png")));
            up2 = ImageIO.read((getClass().getResourceAsStream("/player/up2.png")));
            down = ImageIO.read((getClass().getResourceAsStream("/player/down.png")));
            down2 = ImageIO.read((getClass().getResourceAsStream("/player/down2.png")));
            left = ImageIO.read((getClass().getResourceAsStream("/player/left.png")));
            left2 = ImageIO.read((getClass().getResourceAsStream("/player/left2.png")));
            right = ImageIO.read((getClass().getResourceAsStream("/player/right.png")));
            right2 = ImageIO.read((getClass().getResourceAsStream("/player/right2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed == true) {

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
            assetCounter++;
            if (assetCounter > 12) {
                if (assetNumber == 1) {
                    assetNumber = 2;

                } else if (assetNumber == 2) {
                    assetNumber = 1;
                }
                assetCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2d) {

        g2d.setColor(Color.BLUE);
        g2d.drawString("[DEBUG] FPS: " + gameBoard.drawFPS, 10, 25);
        //g2d.fillRect(x, y, gameBoard.tileSize, gameBoard.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (assetNumber == 1) {
                    image = up;
                }
                if (assetNumber == 2) {
                    image = up2;
                }

                break;

            case "down":
                if (assetNumber == 1) {
                    image = down;
                }
                if (assetNumber == 2) {
                    image = down2;
                }
                break;

            case "left":
                if (assetNumber == 1) {
                    image = left;
                }
                if (assetNumber == 2) {
                    image = left2;
                }
                break;

            case "right":
                if (assetNumber == 1) {
                    image = right;
                }
                if (assetNumber == 2) {
                    image = right2;
                }
                break;
        }
        g2d.drawImage(image, x, y, gameBoard.tileSize, gameBoard.tileSize, null);

    }


}
