package com.escaping.maze;

import com.escaping.maze.simulation.GameController;

public class Main {
    public static void main(String[] args) {
        int mazeWidth = 9;     // Labirent genişliği
        int mazeHeight = 9;    // Labirentin yüksekliği
        int numAgents = 2;     // Ajan sayısı
        int maxTurns = 50;    // Maksimum tur sayısı

        GameController game = new GameController(mazeWidth, mazeHeight, numAgents, maxTurns);

        game.initializeGame(numAgents);
        game.runSimulation(); 
        // DENEME
    }
}
