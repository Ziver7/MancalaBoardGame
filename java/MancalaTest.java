import javax.swing.JFrame;

/**
 The program that runs the entire game.
 @author Victory Chang, John Sun, Justin Tu
 @version 2.0 30 April 2015
 */

public class MancalaTest
{
    public static void main(String[] args)
    {
        //MancalaBoard test1 = new MancalaBoard(new StyleOne(), 3);
        //test1.moveAllStones();
        
        Setup setup = new Setup();
        
        JFrame frame = new JFrame();
        frame.setSize(400, 200);
        frame.add(setup);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        frame.setVisible(true);
        
    }
}




