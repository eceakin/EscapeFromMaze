package com.escaping.maze.simulation;

import com.escaping.maze.manager.MazeManager;
import com.escaping.maze.manager.TurnManager;
import com.escaping.maze.model.Agent;
import com.escaping.maze.model.MazeTile;
import com.escaping.maze.structures.Queue;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class GameController {
    private MazeManager maze;
    private TurnManager turns;
    private int maxTurns;
    private int turnCount;
    private int totalTrapsTriggered;
    private int totalPowerUpsCollected;
    private Agent winner;
    private Queue<Agent> agentQueue;

    public GameController(int width, int height, int numAgents, int maxTurns) {
        this.maze = new MazeManager(width, height, numAgents);
        this.turns = new TurnManager();
        this.maxTurns = maxTurns;
        this.agentQueue = new Queue<>();
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

        printStatistics();
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
                if (winner == null) { // ilk goal'u bulanı kaydet
                    winner = agent;
                }
            }
        } else {
            // If invalid move, maybe wait or try another random move in future improvements
        }
    }

    public void checkTileEffect(Agent agent, MazeTile tile) {
        if (tile.getType() == 'T') {
            System.out.println("Agent " + agent.getId() + " triggered a trap!");
            agent.backtrack();
            totalTrapsTriggered++; 
        } else if (tile.getType() == 'P') {
            System.out.println("Agent " + agent.getId() + " collected a power-up!");
            agent.collectPowerUp();
            totalPowerUpsCollected++;
        }
    }

    public void printStatistics() {
    	 List<Agent> agents = turns.getAllAgents(); //
        StringBuilder stats = new StringBuilder();
        stats.append("Total Turns: ").append(turns.currentRound - 1).append("\n");

     // Print agents statistics
        for (Agent agent :agents) {
            stats.append("Agent ").append(agent.getId()).append(" Statistics:\n")
                 .append("Moves: ").append(agent.getMoveCount()).append("\n")
                 .append("Backtracks: ").append(agent.getBacktrackCount()).append("\n")
                 .append("Traps Triggered: ").append(agent.getTrapCount()).append("\n")
                 .append("Power-ups Used: ").append(agent.getPowerUpCount()).append("\n")
                 .append("Max Stack Depth: ").append(agent.getMaxStackDepth()).append("\n");
        }

     // Print the winner
        if (winner != null) {
            stats.append("Winner: Agent ").append(winner.getId()).append("\n");
        }

      //Print summary screen
        System.out.println(stats.toString());
    }
    
    



    public void logGameSummaryToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Game Summary:\n");
            writer.write("Total Turns: " + (turns.currentRound - 1) + "\n");
            
            writer.write("-------------------------------------");
            writer.write("\n");

         // Print the winner
            if (winner != null) {
            	
                writer.write( " Winner: Agent " + winner.getId() + "\n");
            }
            
            List<Agent> agents = turns.getAllAgents();
            int totalTrapsTriggered = 0;
            int totalPowerUpCount = 0;
            int totalMoves = 0;
            Agent mostMovesAgent = null;
            int maxMoves = 0;
            
            List<Agent> agents1 = turns.getAllAgents();
            for (Agent agent :agents1) {
                writer.write("Agent " + agent.getId() + " Statistics:\n");
                writer.write("Moves: " + agent.getMoveCount() + "\n");
                writer.write("Backtracks: " + agent.getBacktrackCount() + "\n");
                writer.write("Traps Triggered: " + agent.getTrapCount() + "\n");
                writer.write("Power-ups Used: " + agent.getPowerUpCount() + "\n");
                writer.write("Max Stack Depth: " + agent.getMaxStackDepth() + "\n");
                
                totalTrapsTriggered += agent.getTrapCount();
                totalPowerUpCount += agent.getPowerUpCount();
                totalMoves += agent.getMoveCount();
                if (agent.getMoveCount() > maxMoves) {
                    maxMoves = agent.getMoveCount();
                    mostMovesAgent = agent;
                }
            }
            writer.write("-------------------------------------");
            
         //Print General Statistics 
            writer.write("\n=== GENERAL STATISTICS ===\n");
            writer.write("Total Traps Triggered: " + totalTrapsTriggered + "\n");
            writer.write("Total Power-ups Used: " + totalPowerUpCount  + "\n");
            writer.write("Average Moves Per Agent: " + (totalMoves / (double) agents1.size()) + "\n");
            if (mostMovesAgent != null) {
                writer.write("Agent with Most Moves: Agent " + mostMovesAgent.getId() + "\n");
            }
            
            writer.write("----------------------------------------------------");
         // Print Final Maze Map
            writer.write("\nFinal Maze State:\n");

            for (int i = 0; i < maze.height; i++) {
                for (int j = 0; j < maze.width; j++) {
                    writer.write(maze.getTile(i, j).toString() + " ");
                }
                writer.write("\n");
            }

            writer.write("----------------------------------------");

           
            System.out.println("Game summary written to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

}
