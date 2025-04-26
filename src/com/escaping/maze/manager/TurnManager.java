package com.escaping.maze.manager;

import java.util.ArrayList;
import java.util.List;

import com.escaping.maze.model.Agent;
import com.escaping.maze.structures.Queue;

public class TurnManager {
    private Queue<Agent> agentQueue;
    public int currentRound;
    private Agent winner;

    public TurnManager() {
        this.agentQueue = new Queue<>();
        this.currentRound = 1;
        this.winner = null ;
    }
 

    // Kazananı döndüren metot
    public Agent getWinner() {
        return winner;
    }

    public void addAgent(Agent a) {
        agentQueue.enqueue(a);
    }

    public void advanceTurn() {
        Agent a = agentQueue.dequeue();
        if (!a.hasReachedGoal()) {
            agentQueue.enqueue(a);
        }
        else if(winner == null) {
        	winner =a;
        }
        currentRound++;
    }

    public Agent getCurrentAgent() {
        return agentQueue.peek();
    }

    public boolean allAgentsFinished() {
        return agentQueue.isEmpty();
    }

    public void logTurnSummary(Agent a) {
        System.out.println("Round: " + currentRound + ", Agent " + a.getId() + " at position (" + a.getX() + "," + a.getY() + ")");
    }
    public List<Agent> getAllAgents() {
        return agentQueue.getElements();
    }
    public String prepareGameSummary() {
        List<Agent> agents = getAllAgents();
        StringBuilder sb = new StringBuilder();
        sb.append("===== GAME STATISTICS =====\n");
        sb.append("Total Turns Executed: ").append(currentRound - 1).append("\n");

        for (Agent agent : agents) {
            sb.append("\nAgent ").append(agent.getId()).append(" Statistics:\n");
            sb.append("- Moves Made: ").append(agent.getMoveCount()).append("\n");
            sb.append("- Backtracks: ").append(agent.getBacktrackCount()).append("\n");
            sb.append("- Traps Triggered: ").append(agent.getTrapCount()).append("\n");
            sb.append("- Power-Ups Used: ").append(agent.getPowerUpCount()).append("\n");
            sb.append("- Max Stack Depth: ").append(agent.getMaxStackDepth()).append("\n");
        }

        if (winner != null) {
            sb.append("\nWinner: Agent ").append(winner.getId()).append("\n");
        } else {
            sb.append("\nNo agent reached the goal.\n");
        }
        return sb.toString();
    }


}
