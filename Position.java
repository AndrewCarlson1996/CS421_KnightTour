/**
 * Position class for the KnightTour program.
 * This class keeps track of and menipulates the attributes of each position on a
 * a "knight board".
 * 
 * @author Andrew Carlson
 */
public class Position{
    
    private boolean isValid;
    private int moveNumber;
    private int xCoord;
    private int yCoord;
    private int distanceToEdge;

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
     * @param i The value used for the move number.
     */
    public void setMoveNumber(int i){
        moveNumber = i;
    }

    /**
     * 
     * @return The move number at the position.
     */
    public int getMoveNumber(){
        return moveNumber;
    }

    /**
     * 
     * @return X cooradinate of the position.
     */
    public int getXCoor(){
        return xCoord;
    }

    /**
     * 
     * @return Y cooradinate of the position.
     */
    public int getYCoor(){
        return yCoord;
    }

    /**
     * Prints the move number to the console.
     */
    public void moveNumToString(){
        System.out.print(moveNumber);
    }

    /**
     * Returns the integer value of the number of positions from the edge a perticular position is.
     */
    public int getDistanceToEdge(){
        return distanceToEdge;
    }

    /**
     * sets the distance that a position is to the edge of the knight board.
     * @param i
     */
    public void setDistanceToEdge(int i){
        distanceToEdge = i;
    }
}
