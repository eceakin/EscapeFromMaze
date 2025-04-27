 🏃‍♂️ Escape from the Maze: A Turn-Based Simulation Game

![Java](https://img.shields.io/badge/Java-17%2B-red?style=for-the-badge&logo=java)
![Status](https://img.shields.io/badge/Status-Completed-brightgreen?style=for-the-badge)
![License](https://img.shields.io/badge/License-Educational-lightgrey?style=for-the-badge)



📚 Proje Açıklaması

Bu proje, **İzmir Kâtip Çelebi Üniversitesi CENG202 - Veri Yapıları** dersi kapsamında geliştirilmiştir.  
Amaç, klasik veri yapıları kullanarak çok ajanlı ve sıra tabanlı bir labirent kaçış simülasyonu gerçekleştirmektir.

Bu simülasyonda:
✔️ Ajanlar rastgele yönlere hareket eder,
✔️ Tuzaklara düşebilir veya güçlendirici toplayabilir,
✔️ Dönen koridorlar nedeniyle labirent yapısı sürekli değişir,
✔️ Oyun sonunda ajanların performans istatistikleri kaydedilir.



 🛠️ Kullanılan Teknolojiler ve Veri Yapıları

 🚀 Java 17
 🧱 Stack (Yığın) → Ajanların hareket geçmişi
 🚦 Queue (Kuyruk) → Ajanların tur sıralaması
 🔗 Singly Linked List → Ajanların listelenmesi
 🔄 Circular Linked List → Dönen koridor yapısı
 🗺️ 2D Array → Labirent ızgarası



 📦 Proje Yapısı 

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



 🚀 Kurulum ve Çalıştırma
   ✨Gereksinimler
      📌Java Development Kit (JDK) 17 veya üzeri
      📌Bir Java IDE'si (IntelliJ IDEA, Eclipse, vs.) veya terminal

 🚀 Çalıştırma Adımları
  1️⃣ Tüm .java dosyalarını com.escaping.maze paket yapısına uygun yerleştirin.
  2️⃣ Komut satırından derleyin:
      javac com/escaping/maze/**/*.java
  3️⃣ Programı çalıştırın 
      java com.escaping.maze.Main

    Main.java içeriği:
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

🎮 Oyun Mekanikleri
  🔹 Labirent rastgele oluşturulur (duvarlar, tuzaklar, güçlendiriciler, çıkış).
  🔹 Ajanlar her tur bir hamle yapar (yukarı, aşağı, sola, sağa).
  🔹 Bir koridor her turda döner (circular linked list).
  🔹 Tuzak tetiklenirse ajan geri hareket eder (stack üzerinden backtrack).
  🔹 Güçlendirici toplandığında kullanılabilir.
  🔹 Oyun ya tüm ajanlar çıkışa ulaşınca ya da maksimum tur sayısına erişince biter.
  
🎉 Örnek Mazeshot Çıktısı
      E W E P T E E W G
      T E W E T E P W E
      P E E W E T E E T
      E P T W E P T W E
      G W E T E P E E W
      T P W E T E W E P
      E E T P W E T G E
      W E P T E W E P T
      E T W E P T E E W

  E = Empty
  W = Wall
  T = Trap
  P = Power-up
  G = Goal
  A = Agent (ajan varsa)

🎊 Örnek Output  
  ![image](https://github.com/user-attachments/assets/1b68077a-a2ce-4d4b-a22f-d11127fd05be)

🎯 Özellikler
✅ Dinamik koridor rotasyonu
✅ Stack üzerinden geri hareket (backtracking)
✅ Güçlendirici toplama ve kullanımı
✅ Simülasyon loglama ve istatistik üretimi
✅ El ile yazılmış veri yapıları kullanımı
✅ Modüler ve nesne yönelimli (OOP) kod yapısı

📝 Lisans
  Bu proje sadece eğitim amaçlı geliştirilmiştir. 

👩‍💻 Yazarlar
  😍Ece Akın
  😎Betül Sarı
  🌻Zehra Sıla Özdizlekli

