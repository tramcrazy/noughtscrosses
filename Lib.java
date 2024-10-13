import java.util.Random;
import java.util.Scanner;

public class Lib {
    public void runNoughtsCrosses() {
        Scanner ncScan = new Scanner(System.in);
        Random myRandom = new Random();
        System.out.println("Welcome to Noughts and Crosses!");
        System.out.println("Is Player 1 a (H)uman or (R)andomised Computer Player?");
        char p1Type = ncScan.next().charAt(0);
        System.out.println("Is Player 2 a (H)uman or (R)andomised Computer Player?");
        char p2Type = ncScan.next().charAt(0);
        if (p1Type == 'H') {
            HumanPlayer player1 = new HumanPlayer(1);
        } else {
            RandomPlayer player1 = new RandomPlayer(1);
        }
        if (p2Type == 'H') {
            HumanPlayer player2 = new HumanPlayer(2);
        } else {
            RandomPlayer player2 = new RandomPlayer(2);
        }
        System.out.print("What size of game do you want to play? e.g. 3,4,5 >>> ");
        int gridSize = ncScan.nextInt();
        Grid myGrid = new Grid(gridSize, '#');
        System.out.println("Here is the grid! A # symbol represents an empty space.");
        myGrid.printGrid();
        boolean gameNotWon = true;
        boolean isPlayer1 = myRandom.nextBoolean();
        printStartMessage(isPlayer1, player1.playerName, player2.playerName);
        while (gameNotWon) {
            if (isPlayer1) {
                player1.printPlayMessage();
                isPlayer1 = false;
                myGrid.fillGrid(player1.chooseSpace(myGrid), player1.playerSymbol);
            } else {
                player2.printPlayMessage();
                isPlayer1 = true;
                myGrid.fillGrid(player2.chooseSpace(myGrid), player2.playerSymbol);
            }
            char[] winner_data = myGrid.checkWin();
            myGrid.printGrid();
            if (winner_data[0] != '0') {
                printWinMessage(winner_data, player1.playerName, player2.playerName);
                gameNotWon = false;
            }
        }
        System.out.println("Thank you for playing!");
    }
    private void printWinMessage(char[] winner_data, String player1Name, String player2Name) {
        if (winner_data[0] == '2') {
            System.out.println("Draw!");
        }
        else if (winner_data[1] == 'X') {
            System.out.println(player1Name + " wins!");
        }
        else if (winner_data[1] == 'O') {
            System.out.println(player2Name + " wins!");
        }
    }
    private void printStartMessage(boolean player1, String p1Name, String p2Name) {
        if (player1) {
            System.out.println(p1Name + " to start!");
        } else {
            System.out.println(p2Name + " to start!");
        }
    }
}
