public class Mancala {
    //margerie taylor simpson
    // first half is computers side second half is players side
    @SuppressWarnings("FieldMayBeFinal")
    private byte[] board;
    private int comp_score;
    private int play_score;
    private int num_move;
    

    public Mancala(){
        board = new byte[]{4,4,4,4,4,4,0,4,4,4,4,4,9,0};
        comp_score = board[6];
        play_score = board[13];
        num_move = 1;
    }

    /**
     * prints board with correct formatting
     */
    public void printBoard(){
        clearTerminal();

        System.out.println();

        System.out.printf("%40s\n\n", "MANCALA");
        System.out.printf("%31s", " ");
        for (int i = 5; i >= 0; i--) {
            System.out.printf("%d ",  board[i]);
        }

        System.out.println();

        System.out.printf("%29s", " ");
        System.out.printf("%d%13s%d", comp_score, " ", play_score);

        System.out.println();

        System.out.printf("%31s", " ");
        for (int i = 7; i <= 12; i++) {
            System.out.printf("%d ",  board[i]);
        }

        System.out.println();
        System.out.println();

        System.out.printf("%13s", " ");
        System.out.printf("Computer score: %d%12sPlayer score: %d", comp_score, " ", play_score);
        System.out.println();
        System.out.println();
    }

    public void clearTerminal(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * 
     * @param index index of the players move
     * @return true if its a valid move
     * cant if its a store or well is empty
     */
    public boolean isValid(int index){
        index += 6;
        return !((index < 7 || index > 12) || (board[index] == 0));
    }

    public boolean AIValid(int index){
        return !((index < 0 || index > 5) || (board[index] == 0));
    }

    /**
     * 
     * @param index index of players move
     * @return altered board after move was performed
     */
    public byte[] makeMove(int index){
        final int start = index + 1;
        final int beads = board[index];
        board[index] = 0;
        final int mancala;
        final int skip;
        if (whosMove()){
            mancala = 13;
            skip = 6;
        }
        else {
            mancala = 6;
            skip = 13;
        }
        int end = 0;
        int stop = start+beads;
        for (int i = start; i < stop; i++) {
            // use j so editing it will still run the desired amount of times
            int j = i;
            // return to begin of the array if the index exceeds the length
            if (i > 13) {
                j = i % 14;
            }
            if (j == skip) {
                stop++;
                continue;
            }
            // drop a bead in the well
            board[j] += 1;
            end = j;
        }
        // capture
        if (end >= 7 && end <= 12 && board[end] == 1){
            board[13] += board[6-(end-6)] + 1;
            board[6-(end-6)] = 0;
            board[end] = 0;
        }

        num_move++;
        // extra move
        if (end == mancala) {
            // player gets another turn
            num_move--;
        }

        play_score = board[13];
        return board;
    }

    /**
     * 
     * @return index of computer generated move
     */
    public int AIMove(){
        comp_score = board[6];
        return 0;
    }
    
    /**
     * 
     * @return boolean, false if no one has won, true if someone won.
     */
    public boolean gameOver(){
        boolean first = true;
        boolean second = true;
        for (int i = 0; i < 6; i++) {
            if (board[i] != 0) {
                first = false;
            }
        }
        for (int i = 7; i < 13; i++) {
            if (board[i] != 0) {
                second = false;
            }
        }
        return first || second;
    }

    /**
     * 
     * @return boolean. True if its the players move, false if its the computers move.
     */
    public boolean whosMove() {
        return (num_move % 2) == 1;
    }

    public void setNum_move(int num_move) {
        this.num_move = num_move;
    }

    public int getNum_move() {
        return this.num_move;
    }

    public void endGame() {
        int start = (board[0] == 0) ? 7 : 0;
        int total = 0;
        for (int i = start; i < start + 6; i++) {
            total += board[i];
            board[i] = 0;
        }

        if (start == 0) {
            comp_score += total;
        }
        else {
            play_score += total;
        }
    }
}