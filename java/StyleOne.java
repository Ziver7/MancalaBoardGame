import java.awt.Color;
import java.awt.Shape;     
import java.awt.geom.*;

/**
   Copyright (C) Victory Chang. ALL RIGHTS RESERVED.
   A class that represents one style for the mancala game.
   @author Victory Chang, John Sun, Justin Tu
   @version 2.0, 30 April 2015
 */

public class StyleOne implements MancalaBoardLayout
{
    private Color mancalaColor;
    private Shape mancalaShape;

    private Color stoneColor;
    private Shape stoneShape;

    private Color pitColor;
    private Shape pitShape;

    private double pitWidth;
    private double pitHeight;

    private double mancalaWidth;
    private double mancalaHeight;

    private double stoneWidth;
    private double stoneHeight;

    /**
         Constructor that initializes all of the instance variables with a
         @param newMancalaColor the mancala pit's color
         @param newMancalaShape the mancala pit's shape
         @param newStoneColor the stone's color
         @param newStoneShape the stone's shape
         @param newPitColor the pit's color
         @param newPitShape the pits shape
     */

    /**
         Default No-argument constructor that initializes a default style for StyleOne
     */
    public StyleOne()
    {  
        pitWidth = 72;
        pitHeight = 72;

        mancalaWidth = 80;
        mancalaHeight = 160;

        stoneWidth = 20;
        stoneHeight = 20;
        
        mancalaColor = Color.GREEN;
        mancalaShape = new Ellipse2D.Double(0, 0, mancalaWidth, mancalaWidth);

        stoneColor = Color.BLUE;
        stoneShape = new Ellipse2D.Double(0, 0, stoneWidth, stoneHeight);

        pitColor = Color.RED;
        pitShape = new Ellipse2D.Double(0, 0, pitWidth, pitHeight);
    }

    /**
         Return this Style's Stone color
         @return the stone color
     */
    public Color getStoneColor()
    {
        return stoneColor;
    }

    /**
         Mutate this Style's Stone color
         @param newStoneColor the new stone's color
     */
    public void setStoneColor(Color newStoneColor)
    {
        stoneColor = newStoneColor;
    }

    /**
         Return the shape of this Style's Stone
         @return the stone's shape
     */
    public Shape getStoneShape()
    {
        return stoneShape;
    }

    /**
       Set the shape of this Style's Stone
       @param newStoneShape the new stone's shape
     */
    public void setStoneShape(Shape newStoneShape)
    {
        stoneShape = newStoneShape;
    }

    /**
         Return the color of this Style's Mancala board 
         @return the manacala's color
     */
    public Color getMancalaColor()
    {
        return mancalaColor;
    }

    /**
       Set the color of this Style's Mancala board
       @param newMancalaColor the mancala's color
     */
    public void setMancalaColor(Color newMancalaColor)
    {
        mancalaColor = newMancalaColor;
    }

    /**
       Return the shape of this Style's mancala
       @return the mancala pit's shape
     */
    public Shape getMancalaShape()
    {
        return mancalaShape;
    }

    /**
          Set the shape of this Style's mancala pit
          @param newMancalaPitShape the new mancala pit's shape
     */    
    public void setMancalaPitShape(Shape newMancalaPitShape)
    {
        mancalaShape = newMancalaPitShape;
    }

    /**
         Return the color of this Style's pit
         @return the pit color
     */
    public Color getPitColor()
    {
        return pitColor;
    }

    /**
         Set the color of this Style's pit
         @param newPitColor the new pit color
     */
    public void setPitColor(Color newPitColor)
    {
        pitColor = newPitColor;
    }

    /**
         Return the Shape of this Style's Pit
         @return the pit's shape
     */
    public Shape getPitShape()
    {
        return pitShape;
    }

    /**
         Set the Shape of this Style's Pit
         @param newPitShape the new pit's shape
     */
    public void setPitShape(Shape newPitShape)
    {
        pitShape = newPitShape;
    }


    /**
     Creates a new Stone at specified x, y coordinates
     @param x x coordinate of new stone
     @param y y coordinate of new stone
     */
    public Shape createStoneShape(double x, double y)
    {
        Ellipse2D.Double stoneShape = new Ellipse2D.Double(x, y, stoneWidth, stoneHeight);
        return stoneShape;
    }


    /**
     Creates a new Mancala Pit at specified x, y coordinates
     @param x x coordinate of new mancala
     @param y y coordinate of new mancala
     */
    public Shape createMancalaPitShape(double x, double y)
    {
        Ellipse2D.Double mancalaPitShape = new Ellipse2D.Double(x, y, mancalaWidth, mancalaHeight);
        return mancalaPitShape;
    }


    /**
     Creates a new Pit at specified x, y coordinates
     @param x x coordinate of new pit
     @param y y coordinate of new pit
     */
    public Shape createPitShape(double x, double y)
    {
        Ellipse2D.Double pitShape = new Ellipse2D.Double(x, y, pitWidth, pitHeight);
        return pitShape;
    }

    /**
     Accessor for the Mancala Pit color
     @return returns the color of the current pit
     */
    public Color getMancalaPitColor()
    {
        return pitColor;
    }
}




