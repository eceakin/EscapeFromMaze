# 🏃‍♂️ Escape from the Maze: A Turn-Based Simulation Game

![Java](https://img.shields.io/badge/Java-17%2B-red?style=for-the-badge&logo=java)
![Status](https://img.shields.io/badge/Status-Completed-brightgreen?style=for-the-badge)
![License](https://img.shields.io/badge/License-Educational-lightgrey?style=for-the-badge)



## 📚 Project Description  

This project was developed as part of İzmir Kâtip Çelebi University's CENG202 - Data Structures course.
The goal is to implement a multi-agent, turn-based maze escape simulation using classical data structures.

In this simulation:
- ✔️ Agents move in random directions.  
- ✔️ They can fall into traps or collect power-ups.  
- ✔️ The maze structure constantly changes due to rotating corridors.  
- ✔️ Agent performance statistics are recorded at the end of the game.  



## 💠 Technologies and Data Structures Used

- 🚀 Java 17  
- 🧱 Stack → Agent movement history  
- 🚦 Queue → Agent turn order  
- 🔗 Singly Linked List → Agent listing   
- 🔄 Circular Linked List → Rotating corridor structure  
- 🗘️ 2D Array → Maze grid  



## 📆 Project Structure  

```plaintext
com/
└── escaping/
    └── maze/
        ├── Main.java                // Programı başlatır
        ├── simulation/
        │    └── GameController.java  // Simülasyonu yönetir
        ├── manager/
        │    ├── MazeManager.java     // Labirenti oluşturur
        │    └── TurnManager.java     // Ajan sıralamasını yönetir
        ├── model/
        │    ├── Agent.java           // Ajan hareketleri ve istatistikleri
        │    └── MazeTile.java        // Labirent hücre yapısı
        └── structures/
             ├── Stack.java
             ├── Queue.java
             ├── SinglyLinkedList.java
             └── CircularLinkedList.java
```



## 🚀 Installation and Execution

### ✨ Requirements

- 📌 Java Development Kit (JDK) 17 or higher   
- 📌 A Java IDE (IntelliJ IDEA, Eclipse, etc.) or terminal

### 🚀 Execution Steps

1️⃣  Place all .java files in the com.escaping.maze package structure.  
2️⃣  Compile from the command line:

```bash
javac com/escaping/maze/**/*.java
```

3️⃣ Run the program:

```bash
java com.escaping.maze.Main
```

---

### Main.java content:

```java
public class Main {
    public static void main(String[] args) {
        int mazeWidth = 9;
        int mazeHeight = 9;
        int numAgents = 5;
        int maxTurns = 50;

        GameController game = new GameController(mazeWidth, mazeHeight, numAgents, maxTurns);
        game.initializeGame(numAgents);
        game.runSimulation();
    }
}
```



## 🎮 Game Mechanics

- 🔹 Maze is randomly generated (walls, traps, power-ups, exit).   
- 🔹 Agents make one move per turn (up, down, left, right).  
- 🔹 One corridor rotates each turn (circular linked list).    
- 🔹 If a trap is triggered, the agent moves back (backtracking via stack).  
- 🔹 Collected power-ups can be used.   
- 🔹 The game ends when all agents reach the exit or the maximum turn count is reached.  



## 🎉 Example Maze Snapshot Output

```
E W E P T E E W G
T E W E T E P W E
P E E W E T E E T
E P T W E P T W E
G W E T E P E E W
T P W E T E W E P
E E T P W E T G E
W E P T E W E P T
E T W E P T E E W
```

- `E` = Empty  
- `W` = Wall  
- `T` = Trap  
- `P` = Power-up  
- `G` = Goal  
- `A` = Agent (ajan varsa)
  

## 🎊 Example Output

![Maze Example](https://github.com/user-attachments/assets/1b68077a-a2ce-4d4b-a22f-d11127fd05be)



## 🌟 Features

-✅ Dynamic corridor rotation
-✅ Backtracking via stack
-✅ Power-up collection and usage
-✅ Simulation logging and statistics generation
-✅ Hand-implemented data structures
-✅ Modular and object-oriented (OOP) code structure


## 📝 License

This project was developed solely for educational purposes.



## 👩‍💻 Authors

- 😍 Ece Akın  
- 😎 Betül Sarı  
- 🌻 Zehra Sıla Özdizlekli  




 
 
 
 
 
 
 
 
 
