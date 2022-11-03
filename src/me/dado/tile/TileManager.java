package me.dado.tile;

import me.dado.gui.GameBoard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    GameBoard gameBoard;
    Tile[] tile;


    public TileManager(GameBoard gameBoard) {
        this.gameBoard = gameBoard;

        tile = new Tile[10];

        getTileImage();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tile.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/watertile.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void draw(Graphics2D g2d){

        g2d.drawImage(tile[1].image, 0, 0, gameBoard.tileSize, gameBoard.tileSize, null);
        g2d.drawImage(tile[1].image, 48, 0, gameBoard.tileSize, gameBoard.tileSize, null);
        g2d.drawImage(tile[1].image, 96, 0, gameBoard.tileSize, gameBoard.tileSize, null);


    }


}
