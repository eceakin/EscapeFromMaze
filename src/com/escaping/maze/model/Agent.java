package com.escaping.maze.model;

import com.escaping.maze.structures.Stack;

public class Agent {
    private int id;
    private int currentX, currentY;
    private Stack<String> moveHistory;

    // Yeni sayıcı alanları
    private int totalMoves = 0;
    private int backtrackCount = 0;
    private int trapCount = 0;
    private int powerUpCount = 0;
    private int maxStackDepth = 0;

    private boolean hasReachedGoal = false;
    private boolean hasPowerUp = false;

    public Agent(int id, int startX, int startY) {
        this.id = id;
        this.currentX = startX;
        this.currentY = startY;
        this.moveHistory = new Stack<>();
        // İlk pozisyonu kaydet ve stack derinliğini güncelle:
        recordMove(startX, startY);
    }

    public void move(String direction) {
        int newX = currentX, newY = currentY;
        switch (direction.toUpperCase()) {
            case "UP":    newX--; break;
            case "DOWN":  newX++; break;
            case "LEFT":  newY--; break;
            case "RIGHT": newY++; break;
            default:
                System.out.println("Invalid direction!");
                return;
        }
        // Hamle sayaç = 1 artır
        totalMoves++;
        int oldX = currentX, oldY = currentY;
        currentX = newX;
        currentY = newY;
        recordMove(newX, newY);
    }

    public void backtrack() {
        if (moveHistory.size() < 2) return;
        // geri sar
        moveHistory.pop();
        String[] coords = moveHistory.peek().split(",");
        currentX = Integer.parseInt(coords[0]);
        currentY = Integer.parseInt(coords[1]);
        backtrackCount++;
    }

    public void triggerTrap() {
        trapCount++;
        backtrack();
    }

    public void collectPowerUp() {
        hasPowerUp = true;
        powerUpCount++;
    }

    public void applyPowerUp() {
        if (hasPowerUp) {
            System.out.println("Agent " + id + " used power-up!");
            hasPowerUp = false;
        }
    }

    // Yeni: hareketleri kaydeden, aynı zamanda max stack derinliğini güncelleyen metot
    public void recordMove(int x, int y) {
        moveHistory.push(x + "," + y);
        if (moveHistory.size() > maxStackDepth) {
            maxStackDepth = moveHistory.size();
        }
    }

    // Getter metodları:
    public int getId() { return id; }
    public int getX() { return currentX; }
    public int getY() { return currentY; }
    public boolean hasReachedGoal() { return hasReachedGoal; }
    public void setReachedGoal(boolean val) { this.hasReachedGoal = val; }
    public boolean hasPowerUp() { return hasPowerUp; }

    public int getMoveCount() { return totalMoves; }
    public int getBacktrackCount() { return backtrackCount; }
    public int getTrapCount() { return trapCount; }
    public int getPowerUpCount() { return powerUpCount; }
    public int getMaxStackDepth() { return maxStackDepth; }

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", pos=(" + currentX + "," + currentY + ")" +
                '}';
    }
}














/* package com.escaping.maze.model;

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
    private int maxStackDepth = 0;


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
        if (moveHistory.size() > maxStackDepth) {
            maxStackDepth = moveHistory.size();
        }
    }

    public String getMoveHistoryAsString() {
        return moveHistory.toString();
    }

    public int getMaxStackDepth() {
        return maxStackDepth;
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
 */ 