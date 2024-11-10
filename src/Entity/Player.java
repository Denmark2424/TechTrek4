package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;
    
    public Player(GamePanel gp, KeyHandler keyH){
        
        this.gp = gp;
        this.keyH = keyH;
        
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        try{
            
            Up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Up1.png"));
            Up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Up2.png"));
            Down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Down1.png"));
            Down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Down2.png"));
            Left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Left1.png"));
            Left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Left2.png"));
            Right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Right1.png"));
            Right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Right2.png"));
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            
        if(keyH.upPressed == true){
            direction = "up";
            y -= speed;
        }
        else if(keyH.downPressed == true){
            direction = "down";
            y += speed;
        }
        else if(keyH.leftPressed == true){
            direction = "left";
            x -= speed;
        }
        else if(keyH.rightPressed == true){
            direction = "right";
            x += speed;
        }
        
        spriteCounter++;
        if(spriteCounter > 12){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        }
    }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
            
          BufferedImage image = null;
          
          switch(direction){
              case "up":
                  if(spriteNum == 1){
                      image = Up1;
                  }
                  if(spriteNum == 2){
                      image = Up2;
                  }
                  break;
              case "down":
                  if(spriteNum == 1){
                     image = Down1; 
                  }
                  if(spriteNum == 2){
                      image = Down2;
                  }
                  break;
              case"left":
                  if(spriteNum == 1){
                      image = Left1;
                  }
                  if(spriteNum == 2){
                      image = Left2;
                  }
                  break;
              case"right":
                  if(spriteNum == 1){
                      image = Right1;
                  }
                  if(spriteNum == 2){
                      image = Right2;
                  }
                  break;
          }
          g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
}
}
    














