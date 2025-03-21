import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int GameWidth=1200;
        int GameHeight=800;

        JFrame frame =new JFrame("Retro Snake");
        frame.setVisible(true);
        frame.setSize(GameWidth,GameHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Game game=new Game(GameWidth, GameHeight);
        frame.add(game);
          frame.pack();
          game.requestFocus();
    }
}
