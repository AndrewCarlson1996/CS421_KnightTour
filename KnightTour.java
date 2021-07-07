/**
 * The driver class of the KnigthTour program.
 * This program moves a knight around a bourd of user specified size.
 * For a solution to be found, the knight must land on every spot on
 * the board exactly one time.
 * 
 * @author Andrew Carlson
 */
public class KnightTour {

    private static int dimens;
    private static int startingX;
    private static int startingY;
    private static KnightBoard board;
    private static int solveMethod;
    private static boolean solutionFound;
    private static int homeNumberMove;
    private static int homeNumberMoveIndex;
    
    public static void main(String[] args){

        solutionFound = false;

        //Check input format
        if(args.length != 4){
            System.err.println("Usage Error: <0/1/2 (no/heuristicI/heuristicII search)> <n> <x> <y>");
            System.exit(0);
        }
        try {
            if(Integer.parseInt(args[1]) < 3){ //Board must be at least 3x3
                System.err.println("Usage Error: <0/1/2 (no/heuristicI/heuristicII search)> <n> <x> <y>");
                System.exit(0);
            }

            solveMethod = Integer.parseInt(args[0]);
            dimens = Integer.parseInt(args[1]);
            startingX = Integer.parseInt(args[2]);
            startingY = Integer.parseInt(args[3]);
            board = new KnightBoard(dimens, startingX, startingY);
            homeNumberMove = numberOfValidMoves(board.getPosition(startingX, startingY));
            homeNumberMoveIndex = 0;
    
        }
        catch(Exception e)
        {
            System.err.println("An exception occured");
        }

        if(solveMethod == 0){ //If method is 0, basic search method is used
            basicClockwiseCheck(startingX, startingY);
        }
        else if(solveMethod == 1){ //If method = 1, heuristicI method is used
            Heuristic(startingX, startingY, 0);
        }
        else if(solveMethod == 2){ //If method = 2, heuristicII method is used
            Heuristic(startingX, startingY, 1);
        }

        System.out.println("The total number of moves is: " + board.getNumberOfMoves());
        if(solutionFound){ //Print out the number of moves and the board, solution was found
            
            int[][] intBoard = board.loadBoardToArray();
            System.out.println();
            for(int[] row : intBoard) {
                printRow(row);
            }

            System.out.println();
        }
        else{ //No solution is found
            System.out.println("No slution was found!");
            System.exit(0);
        }
    }

    /**
     * Solves the knight tour board by moving the knight to the next possible position
     * in the clockwise direction
     * 
     * @param x
     * @param y
     */
    public static void basicClockwiseCheck(int x, int y){
        Position currentPos = board.getPosition(x, y);

        //If knight is back at the beginning of the board
        if(board.getPosition(startingX, startingY) == currentPos){
            if(homeNumberMoveIndex == homeNumberMove){
                return;
            }
            homeNumberMoveIndex++;
        }
        //If the board is already full of moves
        if(board.getCurrentMoveNumber() == (dimens * dimens)){
            if(solutionFound == false){
                solutionFound = true;
            }
            return;
        }
        if(board.checkBounds(x + 1, y - 2) && board.getPosition(x + 1, y - 2).getValidity() != false){ //Position 1 in clockwise order
            board.addElement(x + 1, y - 2);
            basicClockwiseCheck(x + 1, y - 2);
        }
        if(board.checkBounds(x + 2, y - 1) && board.getPosition(x + 2, y - 1).getValidity() != false){ //Position 2 in clockwise order
            board.addElement(x + 2, y - 1);
            basicClockwiseCheck(x + 2, y - 1);
        }
        if(board.checkBounds(x + 2, y + 1) && board.getPosition(x + 2, y + 1).getValidity() != false){ //Position 3 in clockwise order
            board.addElement(x + 2, y + 1);
            basicClockwiseCheck(x + 2, y + 1);
        }
        if(board.checkBounds(x + 1, y + 2) && board.getPosition(x + 1, y + 2).getValidity() != false){ //Position 4 in clockwise order
            board.addElement(x + 1, y + 2);
            basicClockwiseCheck(x + 1, y + 2);
        }
        if(board.checkBounds(x - 1, y + 2) && board.getPosition(x - 1, y + 2).getValidity() != false){ //Position 5 in clockwise order
            board.addElement(x - 1, y + 2);
            basicClockwiseCheck(x - 1, y + 2);
        }
        if(board.checkBounds(x - 2, y + 1) && board.getPosition(x - 2, y + 1).getValidity() != false){ //Position 6 in clockwise order
            board.addElement(x - 2, y + 1);
            basicClockwiseCheck(x - 2, y + 1);
        }
        if(board.checkBounds(x - 2, y - 1) && board.getPosition(x - 2, y - 1).getValidity() != false){ //Position 7 in clockwise order
            board.addElement(x - 2, y - 1);
            basicClockwiseCheck(x - 2, y - 1);
        }
        if(board.checkBounds(x - 1, y - 2) && board.getPosition(x - 1, y - 2).getValidity() != false){ //Position 8 in clockwise order
            board.addElement(x - 1, y - 2);
            basicClockwiseCheck(x - 1, y - 2);
        }
        if(solutionFound != true){
            board.removeElement(x, y);
        }
        return;
    }

    /**
     * Solves the knight tour board by moving the knight to the open positions
     * closest to the edge of the board.
     * 
     * @param x
     * @param y
     */
    public static void Heuristic(int x, int y, int method){

        Position currentPos = board.getPosition(x, y);
        Position[] moves = new Position[numberOfValidMoves(currentPos)];
        int currentLocalMoveNumber = 0;
        
        //If the board is already full of moves
        if(board.getCurrentMoveNumber() == (dimens * dimens)){
            if(solutionFound == false){
                solutionFound = true;
            }
            return;
        }
        //If there are no possible moves from the current position
        if(moves.length == 0){
            if(board.getCurrentMoveNumber() == (dimens * dimens)){
                if(solutionFound == false){
                    solutionFound = true;
                }
            }
            board.removeElement(x, y);
            return;
        }
        //If knight is back at the beginning of the board
        if(board.getPosition(startingX, startingY) == currentPos){
            if(homeNumberMoveIndex == homeNumberMove){
                return; //No solution is possible
            }
            homeNumberMoveIndex++;
        }
        Position pos1 = board.getPosition(x + 1, y - 2); //Position 1 in clockwise order
        if(pos1 != null && pos1.getValidity() && board.checkBounds(x + 1, y - 2)){
            moves[currentLocalMoveNumber] = pos1;
            currentLocalMoveNumber++;
        }
        Position pos2 = board.getPosition(x + 2, y - 1); //Position 2 in clockwise order
        if(pos2 != null && pos2.getValidity() && board.checkBounds(x + 2, y - 1)){
            moves[currentLocalMoveNumber] = pos2;
            currentLocalMoveNumber++;
        }
        Position pos3 = board.getPosition(x + 2, y + 1); //Position 3 in clockwise order
        if(pos3 != null && pos3.getValidity() && board.checkBounds(x + 2, y + 1)){
            moves[currentLocalMoveNumber] = pos3;
            currentLocalMoveNumber++;
        }
        Position pos4 = board.getPosition(x + 1, y + 2); //Position 4 in clockwise order
        if(pos4 != null && pos4.getValidity() && board.checkBounds(x + 1, y + 2)){
            moves[currentLocalMoveNumber] = pos4;
            currentLocalMoveNumber++;
        }
        Position pos5 = board.getPosition(x - 1, y + 2); //Position 5 in clockwise order
        if(pos5 != null && pos5.getValidity() && board.checkBounds(x - 1, y + 2)){
            moves[currentLocalMoveNumber] = pos5;
            currentLocalMoveNumber++;
        }
        Position pos6 = board.getPosition(x - 2, y + 1); //Position 6 in clockwise order
        if(pos6 != null && pos6.getValidity() && board.checkBounds(x - 2, y + 1)){
            moves[currentLocalMoveNumber] = pos6;
            currentLocalMoveNumber++;
        }
        Position pos7 = board.getPosition(x - 2, y - 1); //Position 7 in clockwise order
        if(pos7 != null && pos7.getValidity() && board.checkBounds(x - 2, y - 1)){
            moves[currentLocalMoveNumber] = pos7;
            currentLocalMoveNumber++;
        }
        Position pos8 = board.getPosition(x - 1, y - 2); //Position 8 in clockwise order
        if(pos8 != null && pos8.getValidity() && board.checkBounds(x - 1, y - 2)){
            moves[currentLocalMoveNumber] = pos8;
            currentLocalMoveNumber++;
        }
        
        if(method == 0){ //If the user requests the HeurisicI method
            sortEdge(moves);
            for (Position position : moves) {
                int xVal = position.getXCoor();
                int yVal = position.getYCoor();
                if(solutionFound != true && position.getValidity() && board.checkBounds(xVal, yVal)){
                    board.addElement(xVal, yVal);
                    Heuristic(xVal, yVal, method);
                }
    
            }
        }
        else if(method == 1){ //If the user requests the HeurisicII method
            sortMoves(moves);
            for (Position position : moves) {
                int xVal = position.getXCoor();
                int yVal = position.getYCoor();
                if(solutionFound != true && position.getValidity() && board.checkBounds(xVal, yVal)){
                    board.addElement(xVal, yVal);
                    Heuristic(xVal, yVal, method);
                }

            }
        }
        if(solutionFound != true){
            board.removeElement(x, y);
            return;
        }
    }

    /**
     * Counts the number of valid moves a knight can make
     * 
     * @param pos The position that is counted from
     * @return The number of valid moves a knight can make
     */
    public static int numberOfValidMoves(Position pos){
        int x = pos.getXCoor(); //The x coordinate of the position
        int y = pos.getYCoor(); //The y coordinate of the position
        int numValidMoves = 0; //counter for the number of valid moves

        //All 8 posible positions
        Position pos1 = board.getPosition(x + 1, y - 2);
        Position pos2 = board.getPosition(x + 2, y - 1);
        Position pos3 = board.getPosition(x + 2, y + 1);
        Position pos4 = board.getPosition(x + 1, y + 2);
        Position pos5 = board.getPosition(x - 1, y + 2);
        Position pos6 = board.getPosition(x - 2, y + 1);
        Position pos7 = board.getPosition(x - 2, y - 1);
        Position pos8 = board.getPosition(x - 1, y - 2);

        if(pos1 != null){
            if(pos1.getValidity()){
                numValidMoves++;
            }
        }
        if(pos2 != null){
            if(pos2.getValidity()){
                numValidMoves++;
            }
        }
        if(pos3 != null){
            if(pos3.getValidity()){
                numValidMoves++;
            }
        }
        if(pos4 != null){
            if(pos4.getValidity()){
                numValidMoves++;
            }
        }
        if(pos5 != null){
            if(pos5.getValidity()){
                numValidMoves++;
            }
        }
        if(pos6 != null){
            if(pos6.getValidity()){
                numValidMoves++;
            }
        }
        if(pos7 != null){
            if(pos7.getValidity()){
                numValidMoves++;
            }
        }
        if(pos8 != null){
            if(pos8.getValidity()){
                numValidMoves++;
            }
        }
        return numValidMoves;
    }

    /**
     * Prints a row of integers that represents the move numbers of the knight on that row.
     * @param row
     */
    public static void printRow(int[] row) {
        for (int i : row) {
            System.out.print(i);
            System.out.print("\t");
        }
        System.out.println();
    }

    /**
     * Sorts the valid positions that knight can move to in order of
     * how close they are to the edge of the board.
     * If there is a tie, the positions are sorted in clockwise order.
     * 
     * @param array The array of positions a knight can move to.
     * @return The sorted array of positions
     */
    public static Position[] sortEdge(Position[] array){
       
        if(array.length == 0 || array.length == 1){
            return array;
        }
        for(int i = 0; i < array.length; i++){
            for(int index = 0; index < array.length - 1; index++){
                int d1 = array[index].getDistanceToEdge();
                int d2 = array[index + 1].getDistanceToEdge();
                if(d1 > d2){
                    Position tempPos = array[index];
                    array[index] = array[index + 1];
                    array[index + 1] = tempPos;
                }
            }
        } 
        return array;
    }

    /**
     * Sorts the valid positions that knight can move to in order of
     * how many onward moves the have.
     * If there is a tie, the positions are sorted in clockwise order.
     * 
     * @param array The array of positions a knight can move to.
     * @return The sorted array of positions
     */
    public static Position[] sortMoves(Position[] array){
       
        if(array.length == 0 || array.length == 1){
            return array;
        }
        for(int i = 0; i < array.length; i++){
            for(int index = 0; index < array.length - 1; index++){
                int d1 = numberOfValidMoves(array[index]);
                int d2 = numberOfValidMoves(array[index + 1]);
                if(d1 > d2){
                    Position tempPos = array[index];
                    array[index] = array[index + 1];
                    array[index + 1] = tempPos;
                }
            }
        } 
        return array;
    }
}
