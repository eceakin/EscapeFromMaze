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
import java.util.concurrent.ThreadLocalRandom;

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

        int agentsPlaced = 0;
        while (agentsPlaced < numAgents) {
            // Rastgele bir konum seç
            int x = random.nextInt(maze.getWidth());
            int y = random.nextInt(maze.getHeight());
            
            MazeTile tile = maze.getTile(x, y);
            
            // Eğer bu tile uygunsa agent yerleştir
            if (tile.isTraversable() && !tile.hasAgent()) {
                Agent agent = new Agent(agentsPlaced + 1, x, y);
                tile.setHasAgent(true);
                turns.addAgent(agent);
                agentsPlaced++;
            }
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

//    public void processAgentAction(Agent agent) {
//        if (agent.hasReachedGoal()) return;
//
//        Random random = new Random();
//        String[] directions = {"UP", "DOWN", "LEFT", "RIGHT"};
//        String direction = directions[random.nextInt(4)];
//
//        if (maze.isValidMove(agent.getX(), agent.getY(), direction)) {
//            int oldX = agent.getX();
//            int oldY = agent.getY();
//
//            agent.move(direction);
//            maze.updateAgentLocation(agent, oldX, oldY);
//
//            MazeTile tile = maze.getTile(agent.getX(), agent.getY());
//            checkTileEffect(agent, tile);
//
//            if (tile.getType() == 'G') {
//                agent.setReachedGoal(true);
//                System.out.println("Agent " + agent.getId() + " has reached the goal!");
//                if (winner == null) { // ilk goal'u bulanı kaydet
//                    winner = agent;
//                }
//            }
//        } else {
//            // If invalid move, maybe wait or try another random move in future improvements
//        }
//    }
 

    private void processAgentAction(Agent agent) {
    	 String[] directions = {"UP", "DOWN", "LEFT", "RIGHT"};
    	    String _direction = directions[new Random().nextInt(4)];
    	    
    	    if (!maze.isValidMove(agent.getX(), agent.getY(), _direction)) return;
    	    
    	    int oldX = agent.getX();
    	    int oldY = agent.getY();
    	    agent.move(_direction);
    	    maze.updateAgentLocation(agent, oldX, oldY);
    	    
    	    MazeTile currentTile = maze.getTile(agent.getX(), agent.getY());
    	    checkTileEffect(agent, currentTile);
    	    
    	    if (currentTile.getType() == 'G') {
    	        handleGoalReached(agent);
    	    }
    }

    private void checkTileEffect(Agent agent, MazeTile tile) {
        switch (tile.getType()) {
            case 'T':
                handleTrapTriggered(agent);
                break;
            case 'P':
                handlePowerUpCollected(agent);
                break;
        }
    }

    private void handleTrapTriggered(Agent agent) {
        System.out.printf("Agent %d triggered a trap!%n", agent.getId());
        agent.backtrack();
        totalTrapsTriggered++;
    }

    private void handlePowerUpCollected(Agent agent) {
        System.out.printf("Agent %d collected a power-up!%n", agent.getId());
        agent.collectPowerUp();
        totalPowerUpsCollected++;
    }

    private void handleGoalReached(Agent agent) {
        agent.setReachedGoal(true);
        System.out.printf("Agent %d has reached the goal!%n", agent.getId());
        if (winner == null) {
            winner = agent;
        }
    }


    public void printStatistics() {
        List<Agent> agents = turns.getAllAgents();
        StringBuilder stats = new StringBuilder();
        stats.append("Total Turns: ").append(turns.currentRound - 1).append("\n");

        agents.forEach(agent -> stats.append(buildAgentStats(agent)));
        
        if (winner != null) {
            stats.append("Winner: Agent ").append(winner.getId()).append("\n");
        }

        System.out.println(stats);
    }

    private String buildAgentStats(Agent agent) {
        return String.format(
            "Agent %d Statistics:%n" +
            "Moves: %d%n" +
            "Backtracks: %d%n" +
            "Traps Triggered: %d%n" +
            "Power-ups Used: %d%n" +
            "Max Stack Depth: %d%n",
            agent.getId(),
            agent.getMoveCount(),
            agent.getBacktrackCount(),
            agent.getTrapCount(),
            agent.getPowerUpCount(),
            agent.getMaxStackDepth()
        );
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
