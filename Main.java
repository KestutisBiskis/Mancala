import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Mancala game = new Mancala();
        int move;
        // get move from user
        try (Scanner input = new Scanner(System.in)) {
            // main game loop
            do {
                game.printBoard();
                if (game.whosMove()){
                    // usr input loop
                    do {
                        System.out.print("Enter an integer in the range [1,6]: ");
                        move = input.nextInt();
                        // print message if move is invalid
                        if (!game.isValid(move)){
                            System.out.println("Not a valid move please try again.");
                        }
                    } while (!game.isValid(move));
                    // make player move and adjust for player board being second half of array
                    game.makeMove(move + 6);
                }
                else {
                    game.makeMove(game.AIMove());
                }
                // AI move loop
                
            } while (!game.gameOver());
            game.printBoard();
            System.out.println("Game over!");
        }
    }
}