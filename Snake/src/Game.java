
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;;

public class Game extends JPanel implements ActionListener,KeyListener {



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
     Timer gameLooper;
     int time=300;

     
    int GameHeight;
    int GameWidth;
    int TileSize=25;
     ArrayList<Integer> Number;
     ArrayList<Integer> Number2;
     ArrayList<Integer> Number3;
    
    boolean gameOver=false;
    Random random =new Random();
    int velocityX;
      int velocityY;

      ArrayList<Tile> snakeBody;
      ArrayList<Tile> stageWall;


    Game(int width,int height){
      
        this.GameHeight=height;
        this.GameWidth=width;
        setPreferredSize(new Dimension(GameWidth,GameHeight));
        setBackground(Color.black);

        snakeHead=new Tile(5, 5);
        addKeyListener(this);
        setFocusable(true);
        food =new Tile(4,4);
          
        Number=new ArrayList<Integer>();
        Number2=new ArrayList<Integer>();
        Number3=new ArrayList<Integer>();
        snakeBody=new ArrayList<Tile>();
        stageWall=new ArrayList<Tile>();
        placeFood();
         velocityX=0;
         velocityY=0;
        gameLooper=new Timer(100, this);
        gameLooper.start();


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

            //placing the snake in the stage
            graphic.setColor(Color.GREEN);
            graphic.fillRect(snakeHead.xPosition*TileSize,snakeHead.yPosition*TileSize,TileSize, TileSize);
              

            for(int i=0;i<snakeBody.size();i++){

               Tile snake=snakeBody.get(i);
               graphic.fillRect(snake.xPosition*TileSize, snake.yPosition*TileSize, TileSize, TileSize);
            }
            


            //placing the food
            graphic.setColor(Color.YELLOW);           
            graphic.fillRect(food.xPosition*TileSize,food.yPosition*TileSize,TileSize, TileSize);

              

            //placing the walls
            int num1=(GameHeight)*1/4;
             for(int i=0;i<GameHeight/2;i++){
                  
                Number.add(num1/TileSize);
                stageWall.add(new Tile( (GameWidth/TileSize)*1/6,Number.get(i)));
                num1++;
             }
            int num2=(GameHeight)*1/4;
             for(int i=0;i<GameHeight/2;i++){
                  
                Number.add(num2/TileSize);
                stageWall.add(new Tile( (GameWidth/TileSize)*5/6,Number.get(i)));
                num2++;
             }
            

             for(int i=0;i<stageWall.size();i++){
               Tile wall=stageWall.get(i);
               graphic.setColor(Color.RED);
               graphic.fillRect(wall.xPosition*TileSize, wall.yPosition*TileSize, TileSize, TileSize);
            }
           

            //game score

            graphic.setFont(new Font("Arial",Font.BOLD,26));
      if(gameOver){
         graphic.setColor(Color.RED);
         graphic.drawString("Game over , Score: "+ String.valueOf(snakeBody.size()) ,25,25);
      }else{
         graphic.setColor(Color.RED);
         graphic.drawString("Score: "+ String.valueOf(snakeBody.size()) ,25,25);
      }

        }
        
        //checking if the snake and then food has collided
        public boolean Collision(Tile tile1,Tile tile2){

             return tile1.xPosition==tile2.xPosition&&tile1.yPosition==tile2.yPosition;
        }

       public void move()
        {
                
          //if thesnake and the food has collided
          if(Collision(snakeHead, food)){
             snakeBody.add(new Tile(food.xPosition, food.yPosition));
             placeFood();            
          }

          
          
                 
                  

                  for(int i=snakeBody.size()-1;i>=0;i--){
                      Tile snake=snakeBody.get(i);
                      if(i==0){

                        snake.xPosition=snakeHead.xPosition;
                        snake.yPosition=snakeHead.yPosition;
                      }else{

                        Tile PrevPart=snakeBody.get(i-1);
                        snake.xPosition=PrevPart.xPosition;
                        snake.yPosition=PrevPart.yPosition;
                      }
                  }
            snakeHead.xPosition+=velocityX;
           
            snakeHead.yPosition+=velocityY;
            for(int i=0;i<stageWall.size();i++){
               Tile wall=stageWall.get(i);
               if(Collision(snakeHead,wall)){
                    gameOver=true;
               }
             }

            // game over
            for(int i=0;i<snakeBody.size();i++){

               Tile snakePart=snakeBody.get(i);
               if(Collision(snakePart, snakeHead)){
                  gameOver=true;
               }

            }
             if(snakeHead.xPosition*TileSize<=0||snakeHead.xPosition*TileSize>=GameWidth||snakeHead.yPosition*TileSize<=0||snakeHead.yPosition*TileSize>=GameHeight){
               gameOver=true; 

            }
        }

        public void placeFood(){
        
            
                  food.xPosition=random.nextInt(GameWidth/TileSize);
                   food.yPosition=random.nextInt(GameHeight/TileSize); 
                  

        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            move();
          repaint();

          if(gameOver){
            gameLooper.stop();
          }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub
             if(e.getKeyChar()=='w'&&velocityY!=1){
                
                velocityX=0;
                velocityY=-1;
             }
             if(e.getKeyChar()=='a'&&velocityX!=1){
                velocityX=-1;
                velocityY=0;
             }
             if(e.getKeyChar()=='s'&&velocityY!=-1){
                velocityX=0;
                velocityY=1;
             }
             if(e.getKeyChar()=='d'&&velocityX!=-1){
                velocityX=1;
                velocityY=0;
             }
        }


        @Override
        public void keyReleased(KeyEvent e) {
           
        }

        @Override
        public void keyTyped(KeyEvent e) {
           
        }
    
}
