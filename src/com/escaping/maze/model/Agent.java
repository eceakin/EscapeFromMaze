package com.escaping.maze.model;

import com.escaping.maze.structures.Stack;

public class Agent {
    private int id;
    private int currX, currY;
    private Stack<String> moveHistory;
    

 // New counter fields
    private int totalMoves = 0;
    private int backtrackCount = 0;
    private int trapCount = 0;
    private int powerUpCount = 0;
    private int maxStackDepth = 0;
    
 // Status flags
    private boolean hasReachedGoal = false;
    private boolean hasPowerUp = false;
    
    // Constructor initializes agent position and records the first move
    public Agent(int id, int startX, int startY) {
        this.id = id;
        this.currX = startX;
        this.currY = startY;
        this.moveHistory = new Stack<>();
        
        
     // Save the initial position and update the stack depth:
        recordMove(startX, startY);
    }
 // Handles movement in four directions
    public void move(String direction) {
        int newX = currX;
        int newY = currY;
        
        if (direction.equalsIgnoreCase("UP")) {
            newX = currX - 1;
        } 
        else if (direction.equalsIgnoreCase("DOWN")) {
            newX = currX + 1;
        } 
        else if (direction.equalsIgnoreCase("LEFT")) {
            newY = currY - 1;
        } 
        else if (direction.equalsIgnoreCase("RIGHT")) {
            newY = currY + 1;
        } 
        else {
            System.out.println("Opps! Invalid direction!");
            return;
        }
        
     // Move counter = increase by 1
        totalMoves++;
        int oldX = currX, oldY = currY;
        currX = newX;
        currY = newY;
        recordMove(newX, newY);
        
    }
    // Returns the agent to its previous location
    public void backtrack() {
        if (moveHistory.size() < 2) return;
     // Remove current position and go back to the previous one
        moveHistory.pop();
        String[] coords = ((String) moveHistory.peek()).split(",");
        currX = Integer.parseInt(coords[0]);
        currY = Integer.parseInt(coords[1]);
        backtrackCount++;
    }
 // Called when agent hits a trap
    public void triggerTrap() {
        trapCount++;
        backtrack();
    }
 // Collect a power-up and update the counter
    public void collectPowerUp() {
        hasPowerUp = true;
        powerUpCount++;
    }
    // Use a power-up if available
    public void applyPowerUp() {
        if (hasPowerUp) {
            System.out.println("Agent " + id + " used power-up!");
            hasPowerUp = false;
        }
    }

 // Method that records transactions and also updates the max stack depth
    public void recordMove(int x, int y) {
        moveHistory.push(x + "," + y);
        if (moveHistory.size() > maxStackDepth) {
            maxStackDepth = moveHistory.size();
        }
        
        
    }
    
    // Getter methods:
    public int getId() { return id; }
    public int getX() { return currX; }
    public int getY() { return currY; }
    public boolean hasReachedGoal() { return hasReachedGoal; }
    public void setReachedGoal(boolean val) { this.hasReachedGoal = val; }
    public boolean hasPowerUp() { return hasPowerUp; }

    public int getMoveCount() { return totalMoves; }
    public int getBacktrackCount() { return backtrackCount; }
    public int getTrapCount() { return trapCount; }
    public int getPowerUpCount() { return powerUpCount; }
    public int getMaxStackDepth() { return maxStackDepth; }
    
    public void printLast5Moves() {
    	// Get all moves from the history stack
        Object[] moves = moveHistory.getAll();  
        int size = moves.length;
     // Print the header indicating the agent's ID and the last 5 moves
        System.out.print("Agent " + id + " Move Stack (last 5): ");
        
        int startIdx = Math.max(0, size - 5);
     // Print each move in the format (x,y)
        for (int i = startIdx; i < size; i++) {
            String move = (String) moves[i];  // Get each move as a String
            System.out.print("(" + move + ") ");  // Format the move in (x,y) format
        }
        // Print a newline after the last 5 moves
        System.out.println();
    }
 // Displays agent information
    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", pos=(" + currX + "," + currY + ")" +
                '}';
    }
}
















 