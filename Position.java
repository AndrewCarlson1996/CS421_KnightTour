/**
 * 
 * 
 * @author Andrew Carlson
 */
public class Position {
    
    private boolean isValid;
    private int moveNumber;
    private int xCoord;
    private int yCoord;
    private int clockPosition;

    public Position(int x, int y){
        isValid = true;
        moveNumber = 0;
        xCoord = x;
        yCoord = y;
        clockPosition = 0;
    }

    public void setValidity(boolean b){
        isValid = b;
    }

    public boolean getValidity(){
        return isValid;

    }

    public void setMoveNumber(int i){
        moveNumber = i;
    }

    public int getMoveNumber(){
        return moveNumber;
    }

    public int getXCoor(){
        return xCoord;
    }

    public int getYCoor(){
        return yCoord;
    }

    public void setClockPosition(int i){
        clockPosition = i;
    }

    public int getClockPosition(){
        return clockPosition;
    }

    public void resetClockPosition(){
        clockPosition = 0;
    }
}
