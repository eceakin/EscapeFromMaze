package com.escaping.maze.manager;

import java.util.ArrayList;
import java.util.List;

import com.escaping.maze.model.Agent;
import com.escaping.maze.structures.Queue;

//Manages agent turns and game progression
public class TurnManager {
    private Queue<Agent> agentQueue;
    public int currentRound;
    private Agent winner;
    private List<Agent> allAgents;

    // Initializes a new game with round 1
    public TurnManager() {
        this.agentQueue = new Queue<>();
        this.currentRound = 1;
        this.winner = null ;
        this.allAgents = new ArrayList<>();

    }
 

    // Returns the winner if game has ended
    public Agent getWinner() {
        return winner;
    }
    // Adds a new agent to the game
    public void addAgent(Agent agent) {
    	  if (agent != null) {
              agentQueue.enqueue(agent);
              allAgents.add(agent);
          }

    }
 // Processes the next turn in the queue
    public void advanceTurn() {
        if (!agentQueue.isEmpty()) {
            Agent currentAgent = agentQueue.dequeue();
            
            if (currentAgent.hasReachedGoal()) {
                if (winner == null) {
                    winner = currentAgent;
                }
            } else {
                agentQueue.enqueue(currentAgent);
            }
            
            currentRound++;
        }
    }

 // Returns whose turn it is currently
    public Agent getCurrentAgent() {
        return agentQueue.peek();
    }
 // Checks if all agents finished the maze
    public boolean allAgentsFinished() {
    	  return agentQueue.size() == 0;
    }
 // Prints current agent's position and round
    public void logTurnSummary(Agent agent) {
    	 if (agent != null) {
             System.out.printf("Current Round: %d | Agent ID: %d | Position: (%d, %d)%n",
                              currentRound,
                              agent.getId(),
                              agent.getX(),
                              agent.getY());
         }    }
 // Returns a copy of all registered agents
    public List<Agent> getAllAgents() {
    	return new ArrayList<>(allAgents);
    }
    
 // Generates formatted game statistics report
    public String prepareGameSummary() {
        StringBuilder summaryBuilder = new StringBuilder();
        
        summaryBuilder.append("========== GAME SUMMARY ==========\n")
                     .append("Total Rounds: ").append(currentRound - 1).append("\n\n");
        
        allAgents.forEach(agent -> {
            summaryBuilder.append("Agent ").append(agent.getId()).append(":\n")
                          .append("  • Move Count: ").append(agent.getMoveCount()).append("\n")
                          .append("  • Backtrack Count: ").append(agent.getBacktrackCount()).append("\n")
                          .append("  • Trap Encounters: ").append(agent.getTrapCount()).append("\n")
                          .append("  • Power-Ups Collected: ").append(agent.getPowerUpCount()).append("\n")
                          .append("  • Maximum Stack Depth: ").append(agent.getMaxStackDepth()).append("\n\n");
        });
        
        if (winner != null) {
            summaryBuilder.append("WINNING AGENT: ").append(winner.getId()).append("\n");
        } else {
            summaryBuilder.append("No winner declared.\n");
        }
        
        return summaryBuilder.toString();
    }


}
