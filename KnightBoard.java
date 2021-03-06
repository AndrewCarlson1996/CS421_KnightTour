/**
 * The Board class for the KnightTour program.
 * This class manages the 2D array based knight board
 * made up of Positions.
 * 
 * @author Andrew Carlson
 */
public class KnightBoard
{

    //create board attributes
    private int boardDimensions; //The height/width of the square knight board
    private Position[][] board; //The 2d array of Positions that holds the knight board
    private int numMoves; //The current number of moves made on the board
    private int currentPos; //The position of the most recent move that was made
    private int currentMoveNumber; //The number of the moves that are on the board
    private Position[] listOfMoves; //Array that keep track of the moves, in order, that are currently on the board

    /**
     * Constroctor for the KnightBoard class
     * 
     * @param dimensions The height/width of the square knight board
     * @param startingX The value of the starting x position on the knight board
     * @param startingY The value of the starting y position on the knight board
     */
    public KnightBoard(int dimensions, int startingX, int startingY){
        boardDimensions = dimensions;
        numMoves = 0;
        board = new Position[boardDimensions][boardDimensions];
        setSizeOfListOfMoves(dimensions);

        for(int i = 0; i < dimensions; i++){ //Populate the board with positions
            for(int j = 0; j < dimensions; j++){
                Position newPos = new Position(i, j);
                board[i][j] = newPos;
                distanceToEdge(board[i][j]);
            }
        }
        currentPos = 0;
        currentMoveNumber = 0;
        this.addElement(startingX, startingY); //Add the first element to the board based on the input peramiters of the constructor
        
    }

    /**
     * 
     * @return The height/width of the square knight board
     */
    public int getBoardDimensions(){
        return boardDimensions;
    }

    /**
     * Adds an element to the knight board
     * 
     * @param x 
     * @param y 
     */
    public void addElement(int x, int y){
        currentMoveNumber++; //The number that will be placed inside the next filled position on the board
        board[x][y].setMoveNumber(currentMoveNumber); //Setting the move number of that position
        board[x][y].setValidity(false); //Position on board is no longer valid
        numMoves++; //Total number of moves used to solve board
        listOfMoves[currentPos] = (board[x][y]); //The array that stores the list of moves made
        currentPos++; //increment array
    }

    /**
     * "Removes" an element from the knight board.
     * 
     * @param x 
     * @param y 
     */
    public void removeElement(int x, int y){
        board[x][y].setMoveNumber(0);
        currentMoveNumber--;
        board[x][y].setValidity(true);
        listOfMoves[currentPos - 1] = null;
        currentPos--;
    }

    /**
     * 
     * @param x 
     * @param y 
     * @return The element at the specified position
     */
    public Position getElement(int x, int y){
        return board[x][y];
    }

    /**
     * 
     * @return Current position
     */
    public Position getCurrentPosition(){
        return listOfMoves[currentPos - 1];
    }

    /**
     * 
     * @return Previous position
     */
    public Position getPreviousPosition(){
        if(currentPos >= 2){
            return listOfMoves[currentPos - 2];
        }
        return null;
    }

    /**
     * 
     * @return Number of moves
     */
    public int getNumberOfMoves(){
        return numMoves;
    }


    /**
     * 
     * @param x 
     * @param y
     * @return Position on knight board.
     */
    public Position getPosition(int x, int y){
        if(checkBounds(x, y)){
            return board[x][y];
        }
        return null;
    }

    /**
     * Set the size of the array that holds the list of moves on the board.
     * 
     * @param i
     */
    public void setSizeOfListOfMoves(int i){
        listOfMoves = new Position[i * i];
    }

    public int getCurrentMoveNumber(){
        return currentMoveNumber;
    }

    /**
     * Calculates the distance a position is from the edge of the board.
     * 
     * @param pos 
     * @return The distance from the edge.
     */
    public void distanceToEdge(Position pos){
        int totalDistance = 0;
        int x = pos.getXCoor();
        int y = pos.getYCoor();

        if(boardDimensions % 2 != 0){ //If the dimensions of the board are odd.
            if(x <= boardDimensions / 2){
                totalDistance = x;
            }
            else{
                totalDistance = boardDimensions - (x + 1);
            }
    
            if(y < boardDimensions / 2){
                totalDistance += y;
            }
            else{
                totalDistance += (boardDimensions - (y + 1));
            }
    
            pos.setDistanceToEdge(totalDistance);
        }
        else{ //If the dimensions of the board are even.
            if(x < (boardDimensions / 2)){
                totalDistance = x;
            }
            else{
                totalDistance = (boardDimensions - 1) - x;
            }
    
            if(y < boardDimensions / 2){
                totalDistance += y;
            }
            else{
                totalDistance += ((boardDimensions - 1) - y);
            }
    
            pos.setDistanceToEdge(totalDistance);
        }
    }

    /**
     * Checks to see if a perticular cooridinate is in bounds.
     * 
     * @param x
     * @param y
     * @return true if the cooridinate is in bounds, false if it is not.
     */
    public boolean checkBounds(int x, int y){
        if(x >= 0 && x < boardDimensions){
            if(y >= 0 && y < boardDimensions){
                return true;
            }
        }
        return false;
    }


    /**
     * Loads the move numbers number into an 2D array of integers.
     * This is so it is easier to print in the main method of the driver class.
     * 
     * @return
     */
    public int[][] loadBoardToArray(){
        int[][] intBoard = new int[boardDimensions][boardDimensions];

        for(int c = 0; c < boardDimensions; c++)
        {
            for(int r = 0; r < boardDimensions; r++){
                intBoard[c][r] = getPosition(r, c).getMoveNumber();
            }
        }
        return intBoard;
    }
}
