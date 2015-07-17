import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

/**
 * A window that visually represents the Mancala board, and all of its pieces.
 * Also draws the buttons for the game.
 *
 * @author Victory Chang, John Sun, Justin Tu
 * @version 7 May 2015
 */

public class MancalaWindow extends JPanel implements ChangeListener
{
	private static final long serialVersionUID = 1L;
	private final int windowWidth = 800;
    private final int windowHeight = 400;

    private final MancalaBoard game;

    private JButton undoButton;
    private JButton endTurnButton;
    
    
    /**
     Creates a MancalaWindow with a MancalaBoard drawn in the center of the
     window and all the buttons, such as End Turn button and Undo move button,
     * drawn on the top of the window. Other game pieces like Pits and Stones are
     * drawn within the MancalaBoard in the center of the window.
     @param gameBoard MancalaBoard object
     */
    public MancalaWindow(MancalaBoard gameBoard)
    {
        super(new BorderLayout());
        game = gameBoard;
        setSize(windowWidth, windowHeight);

        
        
        undoButton = new JButton("Undo");
        undoButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent a)
            {
                game.undoMove();
                //repaint();
            }
        });
        endTurnButton = new JButton("End Turn");
        endTurnButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent a)
            {
                game.endTurn();
            }
        });
        JPanel buttonPanel = new JPanel();

        buttonPanel.setSize(windowWidth, windowHeight/6);
        
        JLabel currentPlayerLabel = game.createPlayerLabel();
        
        buttonPanel.add(undoButton);
        buttonPanel.add(endTurnButton);
        buttonPanel.add(currentPlayerLabel);
        this.add(buttonPanel, BorderLayout.NORTH);
        this.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                Point p = e.getPoint();
                game.checkClickedStone(p);
                //repaint();
            }
        });
game.attach(this);
    }

    /**
     Paints the game board.
     @param g the graphics to paint
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        game.draw(g2);
    }

    /**
     * Automatically redraws the game window when the underlying MancalaBoard
     * model changes or mutates.
     * @param e the ChangeEvent that caused MancalaBoard to change
     */
    public void stateChanged(ChangeEvent e)
    {
        repaint();
    }
}



