
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
            return;
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

        }
        else if(solveMethod == 2){ //if method = 2, heuristicII method is used

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

        if(board.getPosition(x + 1, y + 2).getValidity() != false){
            board.addElement(x + 1, y + 2);
            basicClockwiseCheck(x + 1, y + 2);
        }
        else if(board.getPosition(x + 2, y + 1).getValidity() != false){
            board.addElement(x + 2, y + 1);
            basicClockwiseCheck(x + 2, y + 1);
        }
        else if(board.getPosition(x + 2, y - 1).getValidity() != false){
            board.addElement(x + 2, y - 1);
            basicClockwiseCheck(x + 2, y - 1);
        }
        else if(board.getPosition(x + 1, y - 2).getValidity() != false){
            board.addElement(x + 1, y - 2);
            basicClockwiseCheck(x + 1, y - 2);
        }
        else if(board.getPosition(x - 1, y - 2).getValidity() != false){
            board.addElement(x - 1, y - 2);
            basicClockwiseCheck(x - 1, y - 2);
        }
        else if(board.getPosition(x - 2, y - 1).getValidity() != false){
            board.addElement(x - 2, y - 1);
            basicClockwiseCheck(x - 2, y - 1);
        }
        else if(board.getPosition(x - 2, y + 1).getValidity() != false){
            board.addElement(x - 2, y + 1);
            basicClockwiseCheck(x - 2, y + 1);
        }
        else if(board.getPosition(x - 1, y + 2).getValidity() != false){
            board.addElement(x - 1, y + 2);
            basicClockwiseCheck(x - 1, y + 2);
        }
        else{
            basicClockwiseCheck(board.getPreviousPosition().getXCoor(), board.getPreviousPosition().getYCoor());
        }
    }


    /**
     * 
     * @param x
     * @param y
     */
    public static void HeuristicISearch(int x, int y){
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


    public int distanceToEdge(int x, int y){
        int distance = 0;

        if(x < board.getBoardDimensions() / 2){
            distance = x;
        }
        else{
            
        }


    }

}
