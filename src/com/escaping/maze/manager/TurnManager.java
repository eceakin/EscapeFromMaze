package com.escaping.maze.manager;

import com.escaping.maze.model.Agent;
import com.escaping.maze.structures.Queue;

public class TurnManager {
    private Queue<Agent> agentQueue;
    private int currentRound;

    public TurnManager() {
        this.agentQueue = new Queue<>();
        this.currentRound = 1;
    }

    public void addAgent(Agent a) {
        agentQueue.enqueue(a);
    }

    public void advanceTurn() {
        Agent a = agentQueue.dequeue();
        if (!a.hasReachedGoal()) {
            agentQueue.enqueue(a);
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
}
