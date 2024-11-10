package Main;

import Entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    final int MaxScreenCol = 16;
    final int MaxScreenRow = 12;
    final int screenWidth = tileSize * MaxScreenCol; // 768 pixels
    final int screenHeight = tileSize * MaxScreenRow; // 576 pixels
    
    // FPS
    int FPS = 60;
    
    KeyHandler keyH = new KeyHandler();
    Thread gameThread; //Keeps program to run
    Player player = new Player(this,keyH);
    
    // Set character's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    
    public GamePanel (){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
       
        double drawInterval = 1000000000/FPS; // 0.16666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        
        while(gameThread != null){
            
              // Updates the characters information
              update();
              
              // Draw the screen with updated information
              repaint();
              
              try {
                  double remainingTime = nextDrawTime - System.nanoTime();
                  remainingTime = remainingTime/1000000;
                  
                  if(remainingTime < 0){
                      remainingTime = 0;
                  }
                  
                  Thread.sleep((long) remainingTime);

                  nextDrawTime += drawInterval;
                  
              } catch (InterruptedException e){
                  
                  e.printStackTrace();
              }
        }
    }
    public void update(){
        
       player.update();
    }
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
         
        player.draw(g2);
        
        g2.dispose();
    }
}
