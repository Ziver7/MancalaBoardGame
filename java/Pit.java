import java.awt.*;
import java.awt.geom.*;

/**
 * This class represents a pit for the board game Mancala. The Pit holds a
 * number of Stones and manages them. A Pit also knows if it is a Mancala pit
 * or a regular pit. Each Pit has a method for drawing itself, and uses a
 * MancalaBoardLayout to determine how it looks.
 *
 *
 * @author Justin Tu
 * @version 2015, April 7
 */
public class Pit
{
    private MancalaBoardLayout style;//The graphics style of this Pit
    private int stones;//The number of stones
    private Stone drawnStone;
    private boolean isMancalaPit;//Whether or not this Pit is a Mancala Pit
    private double x;//The x-coordinate of this pit's top left corner
    private double y;//The y-coordinate of this pit's top right corner
    
    /**
     * Creates a Pit prefilled with Stones. This Pit uses the given
     * MancalaBoardLayout, x-coordinate, and y-coordinate. Whether or not this
     * Pit is a Mancala pit depends on the value of the provided boolean
     * isMancala.
     *
     *
     * @param appearance the MancalaBoardLayout this Pit will use
     * @param isMancala whether or not this pit is a Mancala pit
     * @param numStones the initial number of Stones in this Pit
     * @param xCoord the x-coordinate of this Pit's top left corner
     * @param yCoord the y-coordinate of this Pit's top left corner
     */
    public Pit(MancalaBoardLayout appearance, boolean isMancala, int numStones, double xCoord, double yCoord)
    {
        style = appearance;
        isMancalaPit = isMancala;
        stones = numStones;
        drawnStone = null;
        x = xCoord;
        y = yCoord;
    }
    
    /**
     * Draws this Pit and the number of Stones in this Pit. It draws a single
     * Stone and a String underneath that shows how many Stones are in this Pit.
     *
     * @param g2 the Graphics2D that will draw this Pit
     */
    public void draw(Graphics2D g2)
    {
        Color initial = g2.getColor();
        Rectangle2D pitBound;
        Shape s;
        if(isMancalaPit) {
            g2.setColor(style.getMancalaPitColor());
            s = style.createMancalaPitShape(x, y);
        } else {
            g2.setColor(style.getPitColor());
            s = style.createPitShape(x, y);
        }
        pitBound = s.getBounds2D();
        g2.draw(s);
        int amount = stones;
        
        Stone tmp2 = new Stone(style);
        drawnStone = tmp2;
        double centerX = pitBound.getCenterX() - tmp2.getWidth()/2;
        double centerY = pitBound.getCenterY() - tmp2.getHeight()/2;
        tmp2.draw(g2, centerX, centerY);
        
        double stoneHeight = tmp2.getHeight();
        //double stoneWidth = tmp2.getWidth();
        String text = "x" + String.valueOf(amount);
        g2.setColor(Color.BLACK);
        g2.drawString(text, (int) pitBound.getCenterX(), (int) (pitBound.getCenterY() + stoneHeight));
        
        g2.setColor(initial);
    }
    /**
     * Returns whether or not this Pit is a Mancala Pit.
     * @return true if this Pit is a Mancala Pit, false if this Pit is a regular
     * Pit
     */
    public boolean isMancalaPit()
    {
        return isMancalaPit;
    }
    /**
     * Checks whether the point is within the Stone of this Pit.
     * @param p the point to compare with the Stone's location
     * @return true if the Point is within the Stone, false otherwise
     */
    public boolean checkWithinStone(Point p)
    {
        if(drawnStone == null) {
            return false;
        }
        return drawnStone.contains(p);
    }
    
    /**
     * Removes all the Stones in this pit.
     * @return the number of Stones removed from this Pit
     */
    public int removeAll()
    {
        int count = stones;
        stones = 0;
        return count;
    }
    
    /**
     * Creates a shallow copy of this Pit. The MancalaBoardLayout of the copy
     * is the same as the MancalaBoardLayout of the original.
     * @return a shallow copy of this Pit
     */
    public Pit createCopy()
    {
        Pit copy = new Pit(style, isMancalaPit, stones, x, y);
        return copy;
    }
    
    /**
     * Adds a Stones to this Pit
     * @param number the number of Stones to add to this Pit
     */
    public void addStone(int number) {stones += number;}
    
    /**
     * Returns the number of Stones in this Pit
     * @return the number of Stones in this Pit
     */
    public int countStones() {return stones;}
    
    /**
     * Sets the x-coordinates of this Pit's top left corner.
     * @param xCoord the new x-coordinate of this Pit's top left corner
     */
    public void setXCoord(double xCoord) {x = xCoord;}

    /**
     * Returns the x-coordinate of this Pit's top left corner
     * @return the x-coordinate of this Pit's top left corner
     */
    public double getXCoord() {return x;}

    /**
     * Sets the y-coordinates of this Pit's top left corner.
     * @param yCoord the new y-coordinate of this Pit's top left corner
     */
    public void setYCoord(double yCoord){y = yCoord;}

    /**
     * Returns the y-coordinate of this Pit's top left corner
     * @return the y-coordinate of this Pit's top left corner
     */
    public double getYCoord() {return y;}
}


