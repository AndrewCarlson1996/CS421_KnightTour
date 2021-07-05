/**
 * Position class for the KnightTour program.
 * This class keeps track of and menipulates the attributes of each position on a
 * a "knight board".
 * 
 * @author Andrew Carlson
 */
public class Position {
    
    private boolean isValid;
    private int moveNumber;
    private int xCoord;
    private int yCoord;
    private int clockPosition;

    /**
     * Constructor for the Position class
     * 
     * @param x
     * @param y
     */
    public Position(int x, int y){
        isValid = true;
        moveNumber = 0;
        xCoord = x;
        yCoord = y;
        clockPosition = 0;
    }

    /**
     * Sets the validity of the position
     * 
     * @param b
     */
    public void setValidity(boolean b){
        isValid = b;
    }

    /**
     * 
     * @return Validity of the position
     */
    public boolean getValidity(){
        return isValid;
    }

    /**
     * Sets the move number of the position
     * 
     * @param i The value used for the move number
     */
    public void setMoveNumber(int i){
        moveNumber = i;
    }

    /**
     * 
     * @return The move number at the position
     */
    public int getMoveNumber(){
        return moveNumber;
    }

    /**
     * 
     * @return X cooradinate of the position
     */
    public int getXCoor(){
        return xCoord;
    }

    /**
     * 
     * @return Y cooradinate of the position
     */
    public int getYCoor(){
        return yCoord;
    }

    /**
     * Sets the clock hand position of the position
     * This keeps track of what "next move" the position last looked at
     * 
     * @param i value of 1-8 based on 8 possible next positions a knight can move to
     */
    public void setClockPosition(int i){
        clockPosition = i;
    }

    /**
     * 
     * 
     * @return The position of the clock hand, this is the last "next move" the knight at the current position "looked at"
     */
    public int getClockPosition(){
        return clockPosition;
    }

    /**
     * Reset the clock hand back to 0, the default position of the clock hand
     */
    public void resetClockPosition(){
        clockPosition = 0;
    }

    public void moveNumToString(){
        System.out.print(moveNumber + " ");
    }
}
