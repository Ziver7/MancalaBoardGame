import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

/**
 The heart of the Mancala game. This class handles underlying data structure for holding data about what's
 in the pits, whose turn it is, how many times a player has undone a move and so on.
 @author Victory Chang, John Sun, Justin Tu
 @version 9.0, 6 May 2015
 */
public class MancalaBoard extends JFrame
{
	private static final long serialVersionUID = 1L;

	private boolean addedToMancala;
	private int numUndo;
	private int playerAFinalScore;
	private int playerBFinalScore;

	private final int playerARow = 1;
	private final int playerAMancalaColumn = 6;

	private final int playerBRow = 0;
	private final int playerBMancalaColumn = 0;

	private static final double MANCALA_BOARD_WIDTH = 760;
	private static final double MANCALA_BOARD_HEIGHT = 300;

	private static final double TOP_CORNER_X = 10;
	private static final double TOP_CORNER_Y = 40;

	private boolean isPlayerATurn;
	private boolean hasMoved;

	private Pit pits[][];
	private Pit savedPits[][];

	private MancalaBoardLayout style;
	private final JLabel currentPlayerLabel = new JLabel();
	private ArrayList<ChangeListener> listeners;

	/**
     Constructs a Mancala board and initializes instance variables
     @param newStyle style of the board chosen by the player.
     @param startingStones number of starting stones per pit chosen by the player.
	 */
	public MancalaBoard(MancalaBoardLayout newStyle, int startingStones)
	{
		listeners = new ArrayList<ChangeListener>();
		playerAFinalScore = 0;
		playerBFinalScore = 0;
		addedToMancala = false;
		isPlayerATurn = true;
		numUndo = 0;
		hasMoved = false;
		pits = new Pit[2][7];
		savedPits = new Pit[2][7];
		style = newStyle;
		currentPlayerLabel.setText("Current Player: A");

		// Initialize Mancala B
		pits[playerBRow][playerBMancalaColumn] = new Pit(style, true, 0, TOP_CORNER_X + (MANCALA_BOARD_WIDTH/19.0), TOP_CORNER_Y + (MANCALA_BOARD_HEIGHT/5));

		// Initialize Row B
		int j = 0;
		for (int i = 1; i < 7; i++)
		{
			pits[playerBRow][i] = new Pit(style, false, startingStones, TOP_CORNER_X + ((3.5 + 2*j) * (MANCALA_BOARD_WIDTH/19.0)), TOP_CORNER_Y + (MANCALA_BOARD_HEIGHT/5));
			j++;
		}

		// Initialize Mancala A
		pits[playerARow][playerAMancalaColumn] = new Pit(style, true, 0, TOP_CORNER_X + (16*(MANCALA_BOARD_WIDTH/19.0)), TOP_CORNER_Y + (MANCALA_BOARD_HEIGHT/5));


		// Initialize Row A
		for (int i = 0; i < 6; i++)
		{
			pits[playerARow][i] = new Pit(style, false, startingStones, TOP_CORNER_X + (3.5 + 2*i) * (MANCALA_BOARD_WIDTH/19), TOP_CORNER_Y + 3*(MANCALA_BOARD_HEIGHT/5));
		}       

		this.add(currentPlayerLabel);
	}
        /**
         * Moves all stones into the mancala
         */
        /*
        public void moveAllStones()
        {
            int countA = 0;
            int countB = 0;
            
            for(int i = 0; i < playerAMancalaColumn; i++)
            {
                countA += pits[playerARow][i].removeAll();
            }
            pits[playerARow][playerAMancalaColumn].addStone(countA);
            
            
            for(int i = playerBMancalaColumn + 1; i < pits[playerBRow].length; i++)
            {
                countB += pits[playerBRow][i].removeAll();
            }
            pits[playerBRow][playerBMancalaColumn].addStone(countB);
            endTurn();
        }
        */

	/**
     Attaches a view to the MancalaBoard. This view has to implement ChangeListener
     @param m an instance of MancalaWindow
	 */
	public void attach(ChangeListener m)
	{
		listeners.add(m);
	}

	/**
	 * Tells all views, or ChangeListeners, that the model has updated.
	 */
	public void update()
	{
		for(ChangeListener c: listeners)
		{
			c.stateChanged(new ChangeEvent(this));
		}
	}

	/**
     Moves stones from one pit into the other pits. Stones will be dropped one
     * by one into each proceeding pit until no more stones are left to be moved.
     @param r row of the selected pit
     @param c column of the selected pit
	 */
	public void moveStone(int r, int c)
	{
		if(pits[r][c].isMancalaPit())
		{
                    JOptionPane.showMessageDialog(null, "You cannot take stones from a Mancala Pit", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
		}

		// Save the current state of the board for undo purposes
		saveState();

		//make note of how many stones are in selected pit and remove all stone
		int numStones = pits[r][c].removeAll();

		//redistribute stones to other pits until there are no more stones to redistribute
		int i = r;
		int j = c;
		int z;

		if (isPlayerATurn)
		{
			z = 1;
		}
		else
		{
			z = -1;
		}

		j = c + z;
		hasMoved = true;

		//iterate through row 2
		for (int a = numStones; a > 0; a--)
		{
			//normal move
			if(!pits[i][j].isMancalaPit())
			{
				pits[i][j].addStone(1);
				addedToMancala = false;
				j = j + z;
			}
			else if (j == playerAMancalaColumn && i == playerARow && !addedToMancala)
			{
				//add stone to pit and move to row 2                
				if (isPlayerATurn)
				{
					//add stone to Mancala, move to row 1
					pits[playerARow][playerAMancalaColumn].addStone(1);
					addedToMancala = true;

					if (a - 1 == 0)
					{
						hasMoved = false;
						JOptionPane.showMessageDialog (null, "You can move again!", "Message", JOptionPane.INFORMATION_MESSAGE);
						addedToMancala = false;
					}

					i = 0;
					z = -1;
				}
			}
			else if (j == playerBMancalaColumn && i == playerBRow && !addedToMancala)
			{
				//add stone to Mancala and move to row 2
				if (!isPlayerATurn)
				{
					pits[playerBRow][playerBMancalaColumn].addStone(1);
					addedToMancala = true;

					if (a - 1  == 0)
					{
						hasMoved = false;
						JOptionPane.showMessageDialog (null, "You can move again!", "Message", JOptionPane.INFORMATION_MESSAGE);
						addedToMancala = false;
					}

					i = 1;
					z = 1;
				}
			}
		}
		update();
	}

	/**
     Undos a move made in the current turn.
	 */
	public void undoMove()
	{
		if (numUndo < 3 && hasMoved == true)
		{

			//undo move by copying all the old values back into the original array
			undoArray();

			//ser hasMoved to false so player cannot spam undo button without making a move
			hasMoved = false;
			numUndo++;
			JOptionPane.showMessageDialog (null, "Move is undone. You have " + (3-numUndo) + " undos left!", "Undo Button", JOptionPane.INFORMATION_MESSAGE);
			update();
		}
		else if (numUndo >= 3)
		{
			//there are no more undos available this turn
			// DO GUI!!!
			JOptionPane.showMessageDialog (null, "You are out of undos.", "Undo Button", JOptionPane.INFORMATION_MESSAGE);
		}
		else //(hasMoved == false)
		{
			//do not do undo because they have not moved
			// DO GUI!!!
			JOptionPane.showMessageDialog (null, "You have not moved.", "Undo Button", JOptionPane.INFORMATION_MESSAGE);

		}

	}

	/**
         * Allows a player to end his turn. Aslo checks if the game has reached 
         * an end of game state. If the end of the game has been reached,
         * this method also checks who the winning player is and tallies up the
         * final score. 
         * 
         * The end of the game is reached when at least one player's row of pits
         * is empty, except for their Mancala pit.
	 */
	public void endTurn()
	{
		//check victory condition
                int a = checkWinCondition();
		if (hasMoved == true || a != -1)
		{
			if (a == -1)
			{
				// Game is not over, switch players and reset
				// GUI, PLAYER CHANGE
				isPlayerATurn = !isPlayerATurn;

				char turn;
				if (isPlayerATurn == true)
				{
					turn = 'A';
					currentPlayerLabel.setText("Current Player: A");
				}
				else
				{
					turn = 'B';
					currentPlayerLabel.setText("Current Player: B");
				}
                                update();
				JOptionPane.showMessageDialog (null, "It is player " + turn + "'s turn", "End Turn Button", JOptionPane.INFORMATION_MESSAGE);
			}
			else if (a == 1)
			{
				// Player one has won
				// GUI, PLAYER A WINS!
				// DISPLAY STONE COUNT
				JOptionPane.showMessageDialog (null, "Player A has won! [A: " + playerAFinalScore + " B: " + playerBFinalScore + "]", "End Turn Button", JOptionPane.INFORMATION_MESSAGE);
			}
			else if (a == 2)
			{
				// Player two has won
				// GUI, PLAYER B WINS!
				// DISPLAY STONE COUNT
				JOptionPane.showMessageDialog (null, "Player B has won! [A: " + playerAFinalScore + " B: " + playerBFinalScore + "]", "End Turn Button", JOptionPane.INFORMATION_MESSAGE);
			}
			else //(checkWinCondition() == 3)
			{
				// Players have tied
				// GUI, THERE IS A TIE GAME!
				// DISPLAY STONE COUNT
				JOptionPane.showMessageDialog (null, "There is a tie game! [A: " + playerAFinalScore + " B: " + playerBFinalScore + "]", "End Turn Button", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog (null, "Make a move!", "End Turn Button", JOptionPane.INFORMATION_MESSAGE);
		}
		hasMoved = false;
	}

	/**
     Checks if board has an empty row, if a row of pits is empty, add all the remaining stones on the other row to the
     Mancala of the player who owns that row and count all the stones in both Mancala the player with the most Mancala wins the game.
     @return returns 1 if player 1 has won and 2 if player 2 has won and 3 if tie and -1 if game is not over yet
	 */
	public int checkWinCondition()
	{
            int remainingStonesPlayer1 = 0;
            int remainingStonesPlayer2 = 0;

            // Check emptiness of row B (first row) minus the Mancala pit [0][0]
            for (int i = 1; i < pits[1].length; i++) {
                remainingStonesPlayer2 = remainingStonesPlayer2 + pits[playerBRow][i].countStones();
            }
            //System.out.println("player B has: " + (remainingStonesPlayer2 + pits[playerARow][playerAMancalaColumn].countStones()));

            // Check emptiness of row A (second row) minus the Mancala pit [1][6]
            for (int i = 0; i < pits[0].length - 1; i++) {
                remainingStonesPlayer1 = remainingStonesPlayer1 + pits[playerARow][i].countStones();
            }
            //System.out.println("player A has: " + (remainingStonesPlayer1 + pits[playerBRow][playerBMancalaColumn].countStones()));

            if (remainingStonesPlayer1 == 0 || remainingStonesPlayer2 == 0) {
                playerAFinalScore = pits[playerARow][playerAMancalaColumn].countStones() + remainingStonesPlayer1;
                playerBFinalScore = pits[playerBRow][playerBMancalaColumn].countStones() + remainingStonesPlayer2;

                if (playerAFinalScore == playerBFinalScore) {
                    return 3;
                } else if (playerAFinalScore > playerBFinalScore) {
                    return 1;
                } else {
                    return 2;
                }
            } else {
                // We are still playing
                System.out.println("None of the rows are empty");
                return -1;
            }
	}

	/**
     Draws the Mancala board
     @param g2 Graphics2D object
	 */
	public void draw(Graphics2D g2)
	{
		Rectangle2D.Double mancalaBoard = new Rectangle2D.Double(TOP_CORNER_X, TOP_CORNER_Y, MANCALA_BOARD_WIDTH, MANCALA_BOARD_HEIGHT);
		g2.draw(mancalaBoard);

		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				pits[i][j].draw(g2);
			}
		}
	}

        /**
         * Returns a JLabel containing the current player who's turn it is.
         * @return a JLabel that states whose turn it is.
         */
	public JLabel createPlayerLabel()
	{
		return currentPlayerLabel;
	}
        
	/**
     Checks if the current player has made a move this turn.
     @return returns true if player has made a move and false if player has not
	 */
	public boolean checkMove()
	{
		return hasMoved;
	}

	/*
     Saves the state of the board into a temporary Array.
	 */
	private void saveState()
	{
		//saves the current state of the board to a second array incase the player wants to undo
		//System.arraycopy(pits, 0, savedPits, 0, pits.length);
		for (int r = 0; r < pits.length && r < savedPits.length; r++) {
			for (int c = 0; c < pits[r].length && r < savedPits[r].length; c++) {
				savedPits[r][c] = pits[r][c].createCopy();
			}
		}
	}

	/**
	 * Copies the values from savedPits into the values of pits. Essentially
	 * undoing the most recent move.
	 *
	 */
	private void undoArray()
	{
		for(int r = 0; r < pits.length && r < savedPits.length; r++)
		{
			for(int c = 0; c < pits[r].length && r < savedPits[r].length;c++)
			{
				pits[r][c] = savedPits[r][c].createCopy();
			}
		}
	}

	/**
         * Checks which, if any, of the stones has been clicked. If a stone has 
         * been clicked on, then the method calls moveStone() on the
     * clicked stone
     @param p the point where the user clicked
	 */
	public void checkClickedStone(Point p)
	{
		if(hasMoved){
                    JOptionPane.showMessageDialog(null, "You cannot make another move", "Clicked Stone", JOptionPane.INFORMATION_MESSAGE);
                    return;
		}

		for (int i = 0; i < 2; i++)
		{
                    for (int j = 0; j < 7; j++) {
                        if (isPlayerATurn == true && p.getY() > (TOP_CORNER_Y + (MANCALA_BOARD_HEIGHT / 2))) {
                            if (pits[i][j].checkWithinStone(p) && !pits[i][j].isMancalaPit()) {
                                //System.out.println(pits[i][j].countStones());
                                moveStone(i, j);
                            }

                        } else if (isPlayerATurn == false && p.getY() < (TOP_CORNER_Y + (MANCALA_BOARD_HEIGHT / 2))) {
                            if (pits[i][j].checkWithinStone(p) && !pits[i][j].isMancalaPit()) {
                                //System.out.println(pits[i][j].countStones());
                                moveStone(i, j);
                            }
                        }
                    }
		}
	}
}
