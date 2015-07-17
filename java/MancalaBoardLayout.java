import java.awt.Color;
import java.awt.Shape;     

/**
   Copyright (C) Victory Chang, John Sun, Justin Tu. ALL RIGHTS RESERVED.
   An interface that represents a layout for the Mancala board.
    @author Victory Chang, John Sun, Justin Tu
     @version 1.0, 7 April 2015
 */

public interface MancalaBoardLayout
{
    /**
         Return the color of the stone
         @return the stone color
     */
    public Color getStoneColor();

    /**
         Mutate the stone color
         @param newStoneColor the new stone's color
     */
    public void setStoneColor(Color newStoneColor);

    /**
         Return the shape of the stone
         @return the stone's shape
     */
    public Shape getStoneShape();

    /**
     * Returns a Shape for a Stone. The shape’s top left corner is at the given
     * x and y coordinates.
     * @param x the x-coordinate of the Shape’s top left corner
     * @param y the y-coordinate of the Shape’s top left corner
     * @return a Shape who’s top left corner is at the given x and y coordinates
     */
    public Shape createStoneShape(double x, double y);

    /**
       Set the shape of the stone
       @param newStoneShape the new stone's shape
     */
    public void setStoneShape(Shape newStoneShape);

    /**
         Return the color of the mancala pit
         @return the manacala's color
     */
    public Color getMancalaColor();

    /**
       Set the Mancala color
       @param newMancalaColor the mancala's color
     */
    public void setMancalaColor(Color newMancalaColor);

    /**
       Return the shape of the mancala
       @return the mancala pit's shape
     */
    public Shape getMancalaShape();

    /**
     * Returns a Shape for a MancalaPit. The shape’s top left corner is at the given
     * x and y coordinates.
     * @param x the x-coordinate of the Shape’s top left corner
     * @param y the y-coordinate of the Shape’s top left corner
     * @return a Shape who’s top left corner is at the given x and y coordinates
     */
    public Shape createMancalaPitShape(double x, double y);

    /**
          Set the shape of the mancala pit
          @param newMancalaPitShape the new mancala pit's shape
     */    
    public void setMancalaPitShape(Shape newMancalaPitShape);

    /**
         Return the color of the pit
         @return the pit color
     */
    public Color getPitColor();

    /**
         Set the color of the pit
         @param newPitColor the new pit color
     */
    public void setPitColor(Color newPitColor);

    /**
         Return the shape of the pit
         @return the pit's shape
     */
    public Shape getPitShape();

    /**
         Set the shape of the pit
         @param newPitShape the new pit's shape
     */
    public void setPitShape(Shape newPitShape);

    /**
     Creates a pit Shape at x, y
     @param x x coordinate of pit
     @param y y coordinate of pit
     @return A Shape for a Pit, located at the x and y coordinates.
     */
    public Shape createPitShape(double x, double y);

    /**
     Accessor for mancalaColor
     @return color of the Mancala pit
     */
    public Color getMancalaPitColor();
}


