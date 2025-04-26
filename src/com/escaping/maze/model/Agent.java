package com.escaping.maze.model;

import com.escaping.maze.structures.Stack;

public class Agent {
    private int id;
    private int currentX;
    private int currentY;
    private Stack<String> moveHistory;
    private boolean hasReachedGoal;
    private int totalMoves;
    private int backtracks;
    private boolean hasPowerUp;

    public Agent(int id, int startX, int startY) {
        this.id = id;
        this.currentX = startX;
        this.currentY = startY;
        this.moveHistory = new Stack<>();
        this.hasReachedGoal = false;
        this.totalMoves = 0;
        this.backtracks = 0;
        this.hasPowerUp = false;
        recordMove(startX, startY);
    }

    public void move(String direction) {
        int newX = currentX;
        int newY = currentY;

        switch (direction.toUpperCase()) {
            case "UP":    newX--; break;
            case "DOWN":  newX++; break;
            case "LEFT":  newY--; break;
            case "RIGHT": newY++; break;
            default: 
                System.out.println("Invalid direction!");
                return;
        }

        currentX = newX;
        currentY = newY;
        totalMoves++;
        recordMove(newX, newY);
    }

    public void backtrack() {
        if (moveHistory.size() < 2) return;

        moveHistory.pop();
        String[] coords = moveHistory.peek().split(",");
        currentX = Integer.parseInt(coords[0]);
        currentY = Integer.parseInt(coords[1]);
        backtracks++;
    }

    public void applyPowerUp() {
        if (hasPowerUp) {
            System.out.println("Agent " + id + " used power-up!");
            hasPowerUp = false;
        }
    }

    public void recordMove(int x, int y) {
        moveHistory.push(x + "," + y);
    }

    public String getMoveHistoryAsString() {
        return moveHistory.toString();
    }

   
    public int getId() { return id; }
    public int getX() { return currentX; }
    public int getY() { return currentY; }
    public boolean hasReachedGoal() { return hasReachedGoal; }
    public void setReachedGoal(boolean val) { this.hasReachedGoal = val; }
    public void collectPowerUp() { this.hasPowerUp = true; }
    public int getTotalMoves() { return totalMoves; }
    public int getBacktracks() { return backtracks; }
    public boolean hasPowerUp() { return hasPowerUp; }
}
