package com.escaping.maze.manager;
import com.escaping.maze.model.Agent;
import com.escaping.maze.model.MazeTile;
import com.escaping.maze.structures.SinglyLinkedList;

import java.util.List;
import java.util.Random;

public class MazeManager {
    private MazeTile[][] grid;
    public int width;
	public int height;
    private SinglyLinkedList<Agent> agents;  
    private int[] rotatingRows; // corridors
    
 // Constructor to initialize maze dimensions and agent list
    public MazeManager(int width, int height, int agentCount) {
        this.width = width;
        this.height = height;
        this.grid = new MazeTile[width][height];
        this.agents = new SinglyLinkedList<>();
        
     // Initialize corridor rows
        this.rotatingRows = new int[width];  // Each row can be thought of as a corridor
        for (int i = 0; i < width; i++) {
            rotatingRows[i] = i;
        }
    }
 // Randomly generate maze tiles with different types
    public void generateMaze() {
        Random random = new Random();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                char type;
                int rand = random.nextInt(100);
                if (rand < 20) {
                    type = 'W'; // Wall %20
                } else if (rand < 30) {
                    type = 'T'; // Trap %10
                } else if (rand < 40) {
                    type = 'P'; // Power-up %10
                } else {
                    type = 'E'; // Empty
                }
                grid[i][j] = new MazeTile(i, j, type);
            } 
        }
     // Place a random Goal tile somewhere on the grid
        int gx = random.nextInt(width);
        int gy = random.nextInt(height);
        grid[gx][gy] = new MazeTile(gx, gy, 'G');
    }
 // Rotate a row by shifting tiles left
    public void rotateCorridor(int rowId) {
        if (rowId < 0 || rowId >= width) 
        	return;

        MazeTile first = grid[rowId][0];
        
        for (int col = 0; col < height - 1; col++) {
            grid[rowId][col] = grid[rowId][col + 1];
        }
        grid[rowId][height - 1] = first;
    }
   // Check if a move in a given direction is valid
    public boolean isValidMove(int fromX, int fromY, String direction) {
        int newX = fromX;
        int newY = fromY;

        if (direction.equalsIgnoreCase("UP")) {
            newX = fromX - 1;
        } 
        else if (direction.equalsIgnoreCase("DOWN")) {
            newX = fromX + 1;
        } 
        else if (direction.equalsIgnoreCase("LEFT")) {
            newY = fromY - 1;
        } 
        else if (direction.equalsIgnoreCase("RIGHT")) {
            newY = fromY + 1;
        }
        else {
            return false;
        }

        // Check bounds
        if (newX < 0 || newX >= width) {
            return false;
        }
        if (newY < 0 || newY >= height) {
            return false;
        }

        // Check if the tile is traversable
        MazeTile tile = grid[newX][newY];
        return tile.isTraversable();
    }
    public MazeTile getTile(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return grid[x][y];
        }
        return null;
    }
 // Update the agent's location in the grid
    public void updateAgentLocation(Agent a, int oldX, int oldY) {
        grid[oldX][oldY].setHasAgent(false);
        grid[a.getX()][a.getY()].setHasAgent(true);
    }
 // Print current snapshot of the maze grid
    public void printMazeSnapshot() {
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                if (grid[row][col].hasAgent()) {
                    System.out.print("A ");
                } else {
                    System.out.print(grid[row][col].getType() + " ");
                }
            }
            // Move to the next line after each row
            System.out.println();
        }
    }
 // Getters for maze dimensions
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

	
    
}
