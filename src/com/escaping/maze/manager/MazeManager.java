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
    private SinglyLinkedList<Agent> agents;  // Singly Linked List kullanıyoruz
    private int[] rotatingRows; // koridorlar

    public MazeManager(int width, int height, int agentCount) {
        this.width = width;
        this.height = height;
        this.grid = new MazeTile[width][height];
        this.agents = new SinglyLinkedList<>();
        this.rotatingRows = new int[width];  // her satır bir koridor gibi düşünülebilir
        for (int i = 0; i < width; i++) {
            rotatingRows[i] = i;
        }
    }

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
        // Random goal koy
        int gx = random.nextInt(width);
        int gy = random.nextInt(height);
        grid[gx][gy] = new MazeTile(gx, gy, 'G');
    }

    public void rotateCorridor(int rowId) {
        if (rowId < 0 || rowId >= width) return;

        MazeTile first = grid[rowId][0];
        for (int i = 0; i < height - 1; i++) {
            grid[rowId][i] = grid[rowId][i + 1];
        }
        grid[rowId][height - 1] = first;
    }

    public boolean isValidMove(int fromX, int fromY, String direction) {
        int newX = fromX;
        int newY = fromY;

        switch (direction.toUpperCase()) {
            case "UP": newX--; break;
            case "DOWN": newX++; break;
            case "LEFT": newY--; break;
            case "RIGHT": newY++; break;
            default: return false;
        }

        if (newX < 0 || newX >= width || newY < 0 || newY >= height) return false;

        MazeTile tile = grid[newX][newY];
        return tile.isTraversable();
    }

    public MazeTile getTile(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return grid[x][y];
        }
        return null;
    }

    public void updateAgentLocation(Agent a, int oldX, int oldY) {
        grid[oldX][oldY].setHasAgent(false);
        grid[a.getX()][a.getY()].setHasAgent(true);
    }

    public void printMazeSnapshot() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (grid[i][j].hasAgent()) {
                    System.out.print("A ");
                } else {
                    System.out.print(grid[i][j].getType() + " ");
                }
            }
            
            System.out.println();
        }
    }
 
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

	
    
}
