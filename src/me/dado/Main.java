package me.dado;

import me.dado.gui.GameBoard;


import javax.swing.*;

public class Main {
    private static JFrame window;
    private static GameBoard gameBoard;
    public static void main(String[] args) {
        System.out.println("[DEBUG] [Main]: Starting...");
        createWindow();
        createBoard();

    }

    private static void createWindow() {
        System.out.println("[DEBUG] [Main]: Creazione Finestra");
        window = new JFrame("DadoFantasyGame");

    }
    private static void createBoard(){
        System.out.println("[DEBUG] [Main]: Creazione Finestra di Gioco HUD");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        gameBoard = new GameBoard();
        window.add(gameBoard);

        window.setLocationRelativeTo(null); // position of window
        window.pack();

        window.setVisible(true);

        gameBoard.startGameThread();




    }

}