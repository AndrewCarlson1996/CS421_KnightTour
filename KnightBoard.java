
public class KnightBoard
{

    //create board attributes
    private int boardDimensions;
    private Position[][] board;
    private int numMoves;
    private int currentPos;
    private int currentMoveNumber;
    private Position[] listOfMoves;
    

    /**
     * 
     * @param dimensions
     * @param startingX
     * @param startingY
     */
    public KnightBoard(int dimensions, int startingX, int startingY){
        boardDimensions = dimensions;
        numMoves = 0;
        board = new Position[boardDimensions][boardDimensions];
        this.setSizeOfListOfMoves(dimensions);

        for(int i = 0; i < dimensions; i++){
            for(int j = 0; j < dimensions; j++){
                Position newPos = new Position(i, j);
                board[i][j] = newPos;
            }
        }
        currentPos = 0;
        currentMoveNumber = 1;
        this.addElement(startingX, startingY);
        
    }

    /**
     * 
     * @return
     */
    public int getBoardDimensions(){
        return boardDimensions;
    }

    /**
     * 
     * @param move
     * @param x
     * @param y
     */
    public void addElement(int x, int y){
        board[x][y].setMoveNumber(currentMoveNumber); //Setting the move number of that position
        currentMoveNumber++; //The number that will be placed inside the next filled position on the board
        board[x][y].setValidity(false); //Position on board is no longer valid
        numMoves++; //Total number of moves used to solve board
        listOfMoves[currentPos] = (board[x][y]); //The array that stores the list of moves made
        currentPos++; //increment array
    }

    /**
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
     * @return
     */
    public Position getElement(int x, int y){
        return board[x][y];
    }

    /**
     * 
     * @return
     */
    public Position getCurrentPosition(){
        return listOfMoves[currentPos - 1];
    }

    /**
     * 
     * @return
     */
    public Position getPreviousPosition(){
        return listOfMoves[currentPos - 2];
    }

    /**
     * 
     * @return
     */
    public int getNumberOfMoves(){
        return numMoves;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @return
     */
    public Position getPosition(int x, int y){
        return board[x][y];
    }

    /**
     * 
     * @param i
     */
    public void setSizeOfListOfMoves(int i){
        listOfMoves = new Position[i * i];
    }

    public int distanceToEdge(Position pos){
        int totalDistance = 0;
        int x = pos.getXCoor();
        int y = pos.getYCoor();

        if(boardDimensions % 2 == 0){
            if(x < boardDimensions / 2){
                totalDistance = x;
            }
            else{
                totalDistance = boardDimensions - x;
            }
    
            if(y < boardDimensions / 2){
                totalDistance += y;
            }
            else{
                totalDistance += (boardDimensions - y);
            }
    
            return totalDistance;
        }
        else{
            if(x <= boardDimensions / 2){
                totalDistance = x;
            }
            else{
                totalDistance = boardDimensions - x;
            }
    
            if(y <= boardDimensions / 2){
                totalDistance += y;
            }
            else{
                totalDistance += (boardDimensions - y);
            }
    
            return totalDistance;
        }
    }
}
