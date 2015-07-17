import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 Creates a window which prompts the user for initial game conditions. The user
 * will select the Mancala board game's visual style and the initial number of
 * stones.
 * @author Victory Chang, John Sun, Justin Tu
 @version 2.0 30 April 2015
 */
public class Setup extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	final JTextField status1 = new JTextField("");
    final JTextField status2 = new JTextField("");
    
    private StyleOne style1;
    private StyleTwo style2;

    private int stones;
    private int style;

    /**
     * Default Constructor for the Setup class. Creates a JPanel with several
     * JComponents to prompt the user for the Mancala game's initial conditions.
     */
    public Setup()
    {
        stones = 3;
        style = 1;
        
        style1 = new StyleOne();
        style2 = new StyleTwo();

        setLayout(new GridLayout(5, 4));
        
        add(new JLabel(""));add(new JLabel(""));add(new JLabel(""));add(new JLabel(""));
        
        add(new JLabel("Style"));
        JButton styleOne = new JButton("style one");
        styleOne.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                style = 1;
                status1.setText("style one");
            }

        });
        add(styleOne);
        
        JButton styleTwo = new JButton("style two");
        styleTwo.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                style = 2;
                status1.setText("style two");
            }
        });
        add(styleTwo);
        
        add(status1);
        
        add(new JLabel("Stones"));
        JButton threeStones = new JButton("3");
        threeStones.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                stones = 3;
                status2.setText("3");
            }

        });
        add(threeStones);

        JButton fourStones = new JButton("4");
        fourStones.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                stones = 4;
                status2.setText("4");
            }

        });
        add(fourStones);

        add(status2);        
        
        add(new JLabel(""));
        JButton done = new JButton("Done");
        done.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFrame frame = new JFrame();
                MancalaWindow game = new MancalaWindow(new MancalaBoard(style1, stones));    
                
                if (style == 1)
                {
                    game = new MancalaWindow(new MancalaBoard(style1, stones));    
                }
                else if (style == 2)
                {
                    game = new MancalaWindow(new MancalaBoard(style2, stones));    
                }
                
                frame.setSize(800, 400);
                frame.add(game);
                frame.setVisible(true);
                JOptionPane.showMessageDialog (null, "It is player A's turn", "End Turn Button", JOptionPane.INFORMATION_MESSAGE);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        add(done);

        JButton undo = new JButton("Undo All");
        undo.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                stones = 0;
                style = 0;
                status1.setText("");
                status2.setText("");
            }

        });
        add(undo);
        
        add(new JLabel("")); add(new JLabel(""));
    }
}




