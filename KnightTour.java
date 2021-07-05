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
    
    public static void main(String[] args){

        solutionFound = false;

        //check input format
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
            board.addElement(startingX, startingY);
    
        }
        catch(Exception e)
        {
            System.err.println("An exception occured");
        }

        if(solveMethod == 0){ //if method is 0, basic search method is used
            basicClockwiseCheck(startingX, startingY);
        }
        else if(solveMethod == 1){ //if method = 1, heuristicI method is used
            HeuristicI(startingX, startingY);
        }
        else if(solveMethod == 2){ //if method = 2, heuristicII method is used
            HeuristicII(startingX, startingY);
        }

        if(solutionFound){ //print out the number of moves and the board
            System.out.println("The total number of moves is " + board.getNumberOfMoves());

            for(int r = 0; r < dimens; r++){
                for(int c = 0; c < dimens; c++){
                    board.getPosition(r, c).moveNumToString();
                }
                System.out.println();
            }
            System.out.println();
        }
        else{ 
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
        Position previousPos = board.getPreviousPosition();

        if(currentPos.getClockPosition() == 8){
            currentPos.resetClockPosition();
            board.removeElement(x, y);
            basicClockwiseCheck(previousPos.getXCoor(), previousPos.getYCoor());
        }
        else if(board.checkBounds(x + 1, y - 2) && board.getPosition(x + 1, y - 2).getValidity() != false && currentPos.getClockPosition() < 1){
            board.addElement(x + 1, y - 2);
            currentPos.setClockPosition(1);
            basicClockwiseCheck(x + 1, y - 2);
        }
        else if(board.checkBounds(x + 2, y - 1) && board.getPosition(x + 2, y - 1).getValidity() != false && currentPos.getClockPosition() < 2){
            board.addElement(x + 2, y - 1);
            currentPos.setClockPosition(2);
            basicClockwiseCheck(x + 2, y - 1);
        }
        else if(board.checkBounds(x + 2, y + 1) && board.getPosition(x + 2, y + 1).getValidity() != false && currentPos.getClockPosition() < 3){
            board.addElement(x + 2, y + 1);
            currentPos.setClockPosition(3);
            basicClockwiseCheck(x + 2, y + 1);
        }
        else if(board.checkBounds(x + 1, y + 2) && board.getPosition(x + 1, y + 2).getValidity() != false && currentPos.getClockPosition() < 4){
            board.addElement(x + 1, y + 2);
            currentPos.setClockPosition(4);
            basicClockwiseCheck(x + 1, y + 2);
        }
        else if(board.checkBounds(x - 1, y + 2) && board.getPosition(x - 1, y + 2).getValidity() != false && currentPos.getClockPosition() < 5){
            board.addElement(x - 1, y + 2);
            currentPos.setClockPosition(5);
            basicClockwiseCheck(x - 1, y + 2);
        }
        else if(board.checkBounds(x - 2, y + 1) && board.getPosition(x - 2, y + 1).getValidity() != false && currentPos.getClockPosition() < 6){
            board.addElement(x - 2, y + 1);
            currentPos.setClockPosition(6);
            basicClockwiseCheck(x - 2, y + 1);
        }
        else if(board.checkBounds(x - 2, y - 1) && board.getPosition(x - 2, y - 1).getValidity() != false && currentPos.getClockPosition() < 7){
            board.addElement(x - 2, y - 1);
            currentPos.setClockPosition(7);
            basicClockwiseCheck(x - 2, y - 1);
        }
        else if(board.checkBounds(x - 1, y - 2) && board.getPosition(x - 1, y - 2).getValidity() != false && currentPos.getClockPosition() < 8){
            board.addElement(x - 1, y - 2);
            currentPos.setClockPosition(8);
            basicClockwiseCheck(x - 1, y - 2);
        }
        else{
            currentPos.resetClockPosition();
            board.removeElement(x, y);
            if(board.getNumberOfMoves() <= 0){
                solutionFound = false;
                System.out.println("failed");
                return;
            }
            basicClockwiseCheck(previousPos.getXCoor(), previousPos.getYCoor());
        }
    }

    /**
     * Solves the knight tour board by moving the knight to the open positions
     * closest to the edge of the board.
     * 
     * @param x
     * @param y
     */
    public static void HeuristicI(int x, int y){
        Position currentPos = board.getPosition(x, y); //the position that the knight is currently on
        Position previousPos = board.getPreviousPosition(); //the position that the knight just came from
        Position posToGo = null; //the next move to be made
        int closestDistance = -1; //the current distance from the edge of the positions closest to the edge
        int newClockPosition = 0; //temp storage value for next clock hand position

        //retrieve each position in clockwise order
        Position pos1 = board.getPosition(x + 1, y - 2);
        Position pos2 = board.getPosition(x + 2, y - 1);
        Position pos3 = board.getPosition(x + 2, y + 1);
        Position pos4 = board.getPosition(x + 1, y + 2);
        Position pos5 = board.getPosition(x - 1, y + 2);
        Position pos6 = board.getPosition(x - 2, y + 1);
        Position pos7 = board.getPosition(x - 2, y - 1);
        Position pos8 = board.getPosition(x - 1, y - 2);

        //work counter-clockwise around the board till the closest valid position to noon is found 
        //that has not already been tried before in this instance
        if(currentPos.getClockPosition() == 8){ //if clock hand is already on 8th position, reset current position of clock hand and back-track
            currentPos.resetClockPosition();
            board.removeElement(x, y);
            HeuristicI(previousPos.getXCoor(), previousPos.getYCoor());
        }
        if(pos8.getValidity() && board.distanceToEdge(pos8) <= closestDistance && currentPos.getClockPosition() < 8){
            newClockPosition = 8;
            posToGo = pos8;
        }
        if(pos7.getValidity() && board.distanceToEdge(pos7) <= closestDistance && currentPos.getClockPosition() < 7){
            newClockPosition = 7;
            posToGo = pos7;
        }
        if(pos6.getValidity() && board.distanceToEdge(pos6) <= closestDistance && currentPos.getClockPosition() < 6){
            newClockPosition = 6;
            posToGo = pos6;
        }
        if(pos5.getValidity() && board.distanceToEdge(pos5) <= closestDistance && currentPos.getClockPosition() < 5){
            newClockPosition = 5;
            posToGo = pos5;
        }
        if(pos4.getValidity() && board.distanceToEdge(pos4) <= closestDistance && currentPos.getClockPosition() < 4){
            newClockPosition = 4;
            posToGo = pos4;
        }
        if(pos3.getValidity() && board.distanceToEdge(pos3) <= closestDistance && currentPos.getClockPosition() < 3){
            newClockPosition = 3;
            posToGo = pos3;
        }
        if(pos2.getValidity() && board.distanceToEdge(pos2) <= closestDistance && currentPos.getClockPosition() < 2){
            newClockPosition = 2;
            posToGo = pos2;
        }
        if(pos1.getValidity() && board.distanceToEdge(pos1) <= closestDistance && currentPos.getClockPosition() < 1){
            newClockPosition = 1;
            posToGo = pos1;
        }
        
        board.addElement(posToGo.getXCoor(), posToGo.getYCoor());
        currentPos.setClockPosition(newClockPosition);
        HeuristicI(posToGo.getXCoor(), posToGo.getYCoor());

        
        if(currentPos.getClockPosition() == 0){ //if no good position was found, reset current position and back-track
            currentPos.resetClockPosition();
            board.removeElement(x, y);
            HeuristicI(previousPos.getXCoor(), previousPos.getYCoor());
        }
    }

    /**
     * Solves the knight tour board by moving the knight to open positions with
     * the fewest amount of next possible moves
     * 
     * @param x x coordinate of the position being checked
     * @param y y coordinate of the position being checked
     */
    public static void HeuristicII(int x, int y){
        Position currentPos = board.getPosition(x, y); //the position that the knight is currently on
        Position previousPos = board.getPreviousPosition(); //the position that the knight just came from
        Position posToGo = null; //the next move to be made
        int numValidMoves = -1; //number of moves that can be made from each next-possible position
        int newClockPosition = 0; //the position that the clock hand is currently at

        //retrieve each position in clockwise order
        Position pos1 = board.getPosition(x + 1, y - 2);
        Position pos2 = board.getPosition(x + 2, y - 1);
        Position pos3 = board.getPosition(x + 2, y - 1);
        Position pos4 = board.getPosition(x + 1, y - 2);
        Position pos5 = board.getPosition(x - 1, y - 2);
        Position pos6 = board.getPosition(x - 2, y - 1);
        Position pos7 = board.getPosition(x - 2, y + 1);
        Position pos8 = board.getPosition(x - 1, y + 2);

        //work counter-clockwise around the board till the closest valid position to noon is found 
        //that has not already been tried before in this instance
        if(currentPos.getClockPosition() == 8){ //if clock hand is already on 8th position, reset current position of clock hand and back-track
            currentPos.resetClockPosition();
            board.removeElement(x, y);
            HeuristicII(previousPos.getXCoor(), previousPos.getYCoor());
        }
        if(pos8.getValidity() && (numberOfValidMoves(pos8) == -1) && currentPos.getClockPosition() < 8){
            numValidMoves = numberOfValidMoves(pos8);
            newClockPosition = 8;
            posToGo = pos8;
        }
        if(pos7.getValidity() && (numberOfValidMoves(pos7) == -1 || numberOfValidMoves(pos7) <= numValidMoves) && currentPos.getClockPosition() < 7){
            numValidMoves = numberOfValidMoves(pos7);
            newClockPosition = 7;
            posToGo = pos7;
        }
        if(pos6.getValidity() && (numberOfValidMoves(pos6) == -1 || numberOfValidMoves(pos6) <= numValidMoves) && currentPos.getClockPosition() < 6){
            numValidMoves = numberOfValidMoves(pos6);
            newClockPosition = 6;
            posToGo = pos6;
        }
        if(pos5.getValidity() && (numberOfValidMoves(pos5) == -1 || numberOfValidMoves(pos5) <= numValidMoves) && currentPos.getClockPosition() < 5){
            numValidMoves = numberOfValidMoves(pos5);
            newClockPosition = 5;
            posToGo = pos5;
        }
        if(pos4.getValidity() && (numberOfValidMoves(pos4) == -1 || numberOfValidMoves(pos4) <= numValidMoves) && currentPos.getClockPosition() < 4){
            numValidMoves = numberOfValidMoves(pos4);
            newClockPosition = 4;
            posToGo = pos4;
        }
        if(pos3.getValidity() && (numberOfValidMoves(pos3) == -1 || numberOfValidMoves(pos3) <= numValidMoves) && currentPos.getClockPosition() < 3){
            numValidMoves = numberOfValidMoves(pos3);
            newClockPosition = 3;
            posToGo = pos3;
        }
        if(pos2.getValidity() && (numberOfValidMoves(pos2) == -1 || numberOfValidMoves(pos2) <= numValidMoves) && currentPos.getClockPosition() < 2){
            numValidMoves = numberOfValidMoves(pos2);
            newClockPosition = 2;
            posToGo = pos2;
        }
        if(pos1.getValidity() && (numberOfValidMoves(pos1) == -1 || numberOfValidMoves(pos1) <= numValidMoves) && currentPos.getClockPosition() < 1){
            numValidMoves = numberOfValidMoves(pos1);
            newClockPosition = 1;
            posToGo = pos1;
        }
        
        board.addElement(posToGo.getXCoor(), posToGo.getYCoor());
        currentPos.setClockPosition(newClockPosition);
        HeuristicI(posToGo.getXCoor(), posToGo.getYCoor());

        
        if(currentPos.getClockPosition() == 0){ //if no good position was found, reset current position and back-track
            currentPos.resetClockPosition();
            board.removeElement(x, y);
            HeuristicI(previousPos.getXCoor(), previousPos.getYCoor());
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
        Position pos3 = board.getPosition(x + 2, y - 1);
        Position pos4 = board.getPosition(x + 1, y - 2);
        Position pos5 = board.getPosition(x - 1, y - 2);
        Position pos6 = board.getPosition(x - 2, y - 1);
        Position pos7 = board.getPosition(x - 2, y + 1);
        Position pos8 = board.getPosition(x - 1, y + 2);

        if(pos1.getValidity()){
            numValidMoves++;
        }
        if(pos2.getValidity()){
            numValidMoves++;
        }
        if(pos3.getValidity()){
            numValidMoves++;
        }
        if(pos4.getValidity()){
            numValidMoves++;
        }
        if(pos5.getValidity()){
            numValidMoves++;
        }
        if(pos5.getValidity()){
            numValidMoves++;
        }
        if(pos6.getValidity()){
            numValidMoves++;
        }
        if(pos7.getValidity()){
            numValidMoves++;
        }
        if(pos8.getValidity()){
            numValidMoves++;
        }
        return numValidMoves;

    }
}
