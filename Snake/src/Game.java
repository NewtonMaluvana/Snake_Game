
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;;

public class Game extends JPanel {
    int GameHeight;
    int GameWidth;


    Game(int width,int height){

        this.GameHeight=height;
        this.GameWidth=width;
        setPreferredSize(new Dimension(GameHeight,GameWidth));
        setBackground(Color.gray);
        }
    
}
