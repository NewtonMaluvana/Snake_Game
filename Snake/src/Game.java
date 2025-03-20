
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;;

public class Game extends JPanel {



     private class Tile{
        int xPosition;
        int yPosition;

        Tile(int x,int y){
            xPosition=x;
            yPosition=y; 
        }
     }
     Tile snakeHead;
     Tile food;
    int GameHeight;
    int GameWidth;
    int TileSize=25;


    Game(int width,int height){
       Random random =new Random();
        this.GameHeight=height;
        this.GameWidth=width;
        setPreferredSize(new Dimension(GameWidth,GameHeight));
        setBackground(Color.black);

        snakeHead=new Tile(5, 5);
        food =new Tile(random.nextInt(GameWidth),random.nextInt(GameHeight));
        }

        public void paintComponent(Graphics graphic){

              super.paintComponent(graphic);
              draw(graphic);

        }

        public void draw(Graphics graphic){

            for(int i=0;i<GameWidth/TileSize;i++){

                graphic.drawLine(i*TileSize, 0, i*TileSize, GameHeight);
                 graphic.drawLine(0, i*TileSize, GameWidth, i*TileSize);
            }
            graphic.setColor(Color.YELLOW);
            graphic.fillRect(snakeHead.xPosition*TileSize,snakeHead.yPosition*TileSize,TileSize, TileSize);


           


        }
    
}
