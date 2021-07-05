import java.util.List;

public class KnightTour {

    private static int dimens;
    private static int startingX;
    private static int startingY;
    private static KnightBoard board;
    private static int solveMethod;
    private static boolean solutionFound;
    
    public static void mian(String[] args){

        solutionFound = false;

        //check input format
        if(args.length != 4){
            System.err.println("Usage Error: <0/1/2 (no/heuristicI/heuristicII search)> <n> <x> <y>");
            System.exit(0);
        }
        try {

            dimens = Integer.parseInt(args[1]);
            startingX = Integer.parseInt(args[2]);
            startingY = Integer.parseInt(args[3]);
            board = new KnightBoard(dimens, startingX, startingY);
            solveMethod = Integer.parseInt(args[0]);
    
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
            System.out.println("");
        }
        else{ 
            System.out.println("No slution was found!");
            System.exit(0);
        }

    }

    /**
     * 
     * @param x
     * @param y
     * @return
     */
    public static void basicClockwiseCheck(int x, int y){

        Position currentPos = board.getPosition(x, y);
        Position previousPos = board.getPreviousPosition();
        if(currentPos.getClockPosition() == 8){
            currentPos.resetClockPosition();
            basicClockwiseCheck(previousPos.getXCoor(), previousPos.getYCoor());
        }
        else if(board.getPosition(x + 1, y + 2).getValidity() != false && currentPos.getClockPosition() < 1){
            board.addElement(x + 1, y + 2);
            currentPos.setClockPosition(1);
            basicClockwiseCheck(x + 1, y + 2);
        }
        else if(board.getPosition(x + 2, y + 1).getValidity() != false && currentPos.getClockPosition() < 2){
            board.addElement(x + 2, y + 1);
            currentPos.setClockPosition(2);
            basicClockwiseCheck(x + 2, y + 1);
        }
        else if(board.getPosition(x + 2, y - 1).getValidity() != false && currentPos.getClockPosition() < 3){
            board.addElement(x + 2, y - 1);
            currentPos.setClockPosition(3);
            basicClockwiseCheck(x + 2, y - 1);
        }
        else if(board.getPosition(x + 1, y - 2).getValidity() != false && currentPos.getClockPosition() < 4){
            board.addElement(x + 1, y - 2);
            currentPos.setClockPosition(4);
            basicClockwiseCheck(x + 1, y - 2);
        }
        else if(board.getPosition(x - 1, y - 2).getValidity() != false && currentPos.getClockPosition() < 5){
            board.addElement(x - 1, y - 2);
            currentPos.setClockPosition(5);
            basicClockwiseCheck(x - 1, y - 2);
        }
        else if(board.getPosition(x - 2, y - 1).getValidity() != false && currentPos.getClockPosition() < 6){
            board.addElement(x - 2, y - 1);
            currentPos.setClockPosition(6);
            basicClockwiseCheck(x - 2, y - 1);
        }
        else if(board.getPosition(x - 2, y + 1).getValidity() != false && currentPos.getClockPosition() < 7){
            board.addElement(x - 2, y + 1);
            currentPos.setClockPosition(7);
            basicClockwiseCheck(x - 2, y + 1);
        }
        else if(board.getPosition(x - 1, y + 2).getValidity() != false && currentPos.getClockPosition() < 8){
            board.addElement(x - 1, y + 2);
            currentPos.setClockPosition(8);
            basicClockwiseCheck(x - 1, y + 2);
        }
        else{
            currentPos.resetClockPosition();
            basicClockwiseCheck(previousPos.getXCoor(), previousPos.getYCoor());
        }
    }

    /**
     * 
     * @param x
     * @param y
     */
    public static void HeuristicI(int x, int y){
        Position pos = board.getPosition(x, y);

        //Distance of each position to the edge
        int distancePos1 = board.distanceToEdge(board.getPosition(x + 1, y + 2));
        int distancePos2 = board.distanceToEdge(board.getPosition(x + 2, y + 1));
        int distancePos3 = board.distanceToEdge(board.getPosition(x + 2, y - 1));
        int distancePos4 = board.distanceToEdge(board.getPosition(x + 1, y - 2));
        int distancePos5 = board.distanceToEdge(board.getPosition(x - 1, y - 2));
        int distancePos6 = board.distanceToEdge(board.getPosition(x - 2, y - 1));
        int distancePos7 = board.distanceToEdge(board.getPosition(x - 2, y + 1));
        int distancePos8 = board.distanceToEdge(board.getPosition(x - 1, y + 2));

        if(){
            
        }


        if()

    }

    /**
     * 
     * @param x
     * @param y
     */
    public static void HeuristicII(int x, int y){
        if(){

        }
        else if(){

        }
        else if(){

        }
        else if(){

        }
        else if(){

        }
        else if(){

        }
        else if(){

        }
        else if(){

        }
        else{

        }
    
    }

    /**
     * 
     * @param pos
     * @return
     */
    
}
