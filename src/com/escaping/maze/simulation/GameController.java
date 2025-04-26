package com.escaping.maze.simulation;

import com.escaping.maze.manager.MazeManager;
import com.escaping.maze.manager.TurnManager;
import com.escaping.maze.model.Agent;
import com.escaping.maze.model.MazeTile;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GameController {
    private MazeManager maze;
    private TurnManager turns;
    private int maxTurns;
    private int turnCount;

    public GameController(int width, int height, int numAgents, int maxTurns) {
        this.maze = new MazeManager(width, height, numAgents);
        this.turns = new TurnManager();
        this.maxTurns = maxTurns;
        this.turnCount = 0;
    }

    public void initializeGame(int numAgents) {
        maze.generateMaze();
        Random random = new Random();

        for (int i = 0; i < numAgents; i++) {
          
            int x, y;
            do {
                x = random.nextInt(maze.getWidth());
                y = random.nextInt(maze.getHeight());
            } while (!maze.getTile(x, y).isTraversable() || maze.getTile(x, y).hasAgent());

            Agent agent = new Agent(i + 1, x, y);
            maze.getTile(x, y).setHasAgent(true);
            turns.addAgent(agent);
        }

        System.out.println("Game initialized with " + numAgents + " agents.");
    }

    public void runSimulation() {
        while (turnCount < maxTurns && !turns.allAgentsFinished()) {
            Agent currentAgent = turns.getCurrentAgent();
            processAgentAction(currentAgent);
            turns.logTurnSummary(currentAgent);

            // Rotate a random corridor each turn
            Random random = new Random();
            int rowToRotate = random.nextInt(maze.getWidth());
            maze.rotateCorridor(rowToRotate);

            maze.printMazeSnapshot();
            turnCount++;
            turns.advanceTurn();
        }

        printFinalStatistics();
        logGameSummaryToFile("game_summary.txt");
    }

    public void processAgentAction(Agent agent) {
        if (agent.hasReachedGoal()) return;

        Random random = new Random();
        String[] directions = {"UP", "DOWN", "LEFT", "RIGHT"};
        String direction = directions[random.nextInt(4)];

        if (maze.isValidMove(agent.getX(), agent.getY(), direction)) {
            int oldX = agent.getX();
            int oldY = agent.getY();

            agent.move(direction);
            maze.updateAgentLocation(agent, oldX, oldY);

            MazeTile tile = maze.getTile(agent.getX(), agent.getY());
            checkTileEffect(agent, tile);

            if (tile.getType() == 'G') {
                agent.setReachedGoal(true);
                System.out.println("Agent " + agent.getId() + " has reached the goal!");
            }
        } else {
            // If invalid move, maybe wait or try another random move in future improvements
        }
    }

    public void checkTileEffect(Agent agent, MazeTile tile) {
        if (tile.getType() == 'T') {
            System.out.println("Agent " + agent.getId() + " triggered a trap!");
            agent.backtrack();
        } else if (tile.getType() == 'P') {
            System.out.println("Agent " + agent.getId() + " collected a power-up!");
            agent.collectPowerUp();
        }
    }

    public void printFinalStatistics() {
        System.out.println("\nFinal Statistics:");
        System.out.println("Total Turns: " + turnCount);
    }

    public void logGameSummaryToFile(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write("Game Summary:\n");
            writer.write("Total Turns: " + turnCount + "\n");
            writer.close();
            System.out.println("Game summary written to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
