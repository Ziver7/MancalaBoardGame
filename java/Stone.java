import java.awt.*;

/**
 * This class represents a stone for the board game Mancala. Each Stone
 * is responsible for drawing itself using a Graphics2D object and uses a
 * specific MancalaBoardLayout style to do so.
 *
 *
 * @author Justin Tu
 * @version 2015, April 7
 */
public class Stone
{
    private MancalaBoardLayout style;
    private double x;
    private double y;
    private Shape shape;
    private Color color;
    
    /**
     * Creates a Stone that uses the given MancalaBoardLayout.
     * @param appearance the MancalaBoardLayout that this Stone will use
     */
    public Stone(MancalaBoardLayout appearance)
    {
        style = appearance;
        shape = style.getStoneShape();
        color = style.getStoneColor();
        x = -1;
        y = -1;
    }
    
    /**
     * Draws this Stone at a specific x and y coordinates, with the given
     * Graphics2D, and according to the Stone's MancalaBoardLayout
     *
     * @param g2 the Graphics2D that will draw this Stone
     * @param x the x-coordinate for the Stone's top left corner
     * @param y the y-coordinate for the Stone's top left corner
     */
    public void draw(Graphics2D g2, double x, double y)
    {
        Shape tmp = style.createStoneShape(x,y);
        shape = tmp;
        this.x = x;
        this.y = y;
        Color initial = g2.getColor();
        g2.setColor(color);
        g2.fill(tmp);
        g2.setColor(initial);
    }
    /**
     * Returns the width of this Stone
     * @return the width of this Stone
     */
    public double getWidth()
    {
        return shape.getBounds2D().getWidth();
    }
    
    /**
     * Returns the height of this Stone
     * @return the height of this Stone
     */
    public double getHeight()
    {
        return shape.getBounds2D().getHeight();        
    }
    
    public boolean contains(Point p)
    {
        if(x == -1 || y == -1) {
            return false;
        }
        return (x <= p.getX() && p.getX() <= (x + getWidth())) &&
               (y <= p.getY() && p.getY() <= (y + getHeight()));
    }
    
        /**
     * POSSIBLY NOT USEFUL
     *
     * Creates a Stone that uses the given MancalaBoardLayout. The stone's top
     * left corner sits at the given x and y coordinates.
     *
     * @param appearance the MancalaBoardLayout that this Stone will use
     * @param xCoord this Stone's x coordinate
     * @param yCoord this Stone's y coordinate
     */
    /*
     public Stone(MancalaBoardLayout appearance, double xCoord, double yCoord)
     {
     style = appearance;
     x = xCoord;
     y = yCoord;
     s = style.createStoneShape(x, y);
     c = style.getStoneColor();
     }
     */
    
    /**
     * Draws this Stone at its assigned x and y coordinates, using the given
     * Graphics2D and according to its MancalaBoardLayout.
     *
     * @param g2 the Graphics2D that will draw this Stone
     */
    /*
     public void draw(Graphics2D g2)
     {
     Color initial = g2.getColor();
     g2.setColor(color);
     g2.draw(shape);
     g2.setColor(initial);
     }
     */
    
    /**
     * Sets the x-coordinates of this Stone's top left corner.
     *
     * @param xCoord the new x-coordinate of this Stone's top left corner
     */
    /*
     public void setXCoord(double xCoord)
     {
     x = xCoord;
     }
     */
    /**
     * Returns the x-coordinate of this Stone's top left corner
     *
     * @return the x-coordinate of this Stone's top left corner
     */
    /*
     public double getXCoord()
     {
     return x;
     }
     */
    /**
     * Sets the y-coordinates of this Stone's top left corner.
     *
     * @param yCoord the new y-coordinate of this Stone's top left corner
     */
    /*
     public void setYCoord(double yCoord)
     {
     y = yCoord;
     }
     */
    /**
     * Returns the y-coordinate of this Stone's top left corner
     *
     * @return the y-coordinate of this Stone's top left corner
     */
    /*
     public double getYCoord()
     {
     return y;
     }
     */
}



