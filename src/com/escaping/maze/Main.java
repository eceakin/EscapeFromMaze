package com.escaping.maze;

import com.escaping.maze.simulation.GameController;

public class Main {
    public static void main(String[] args) {
        int mazeWidth = 6;     // Labirent genişliği
        int mazeHeight = 6;    // Labirentin yüksekliği
        int numAgents = 3;     // Ajan sayısı
        int maxTurns = 100;    // Maksimum tur sayısı

        GameController game = new GameController(mazeWidth, mazeHeight, numAgents, maxTurns);

        game.initializeGame(numAgents);
        game.runSimulation();
    }
}
