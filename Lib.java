import java.util.Random;
import java.util.Scanner;

public class Lib {
    public void runNoughtsCrosses() {
        Scanner ncScan = new Scanner(System.in);
        Random myRandom = new Random();
        System.out.println("Welcome to Noughts and Crosses!");
        System.out.print("Enter Player 1's name (X) >>> ");
        String p1Name = ncScan.next();
        System.out.print("Enter Player 2's name (O) >>> ");
        String p2Name = ncScan.next();
        System.out.print("What size of game do you want to play? e.g. 3,4,5 >>> ");
        int gridSize = ncScan.nextInt();
        Grid myGrid = new Grid(gridSize, '#');
        System.out.println("Here is the grid! A # symbol represents an empty space.");
        myGrid.printGrid();
        boolean gameNotWon = true;
        boolean player1 = myRandom.nextBoolean();
        printStartMessage(player1, p1Name, p2Name);
        char playerSymbol;
        while (gameNotWon) {
            if (player1) {
                System.out.println(p1Name + ", it's your turn!");
                playerSymbol = 'X';
                player1 = false;
            } else {
                System.out.println(p2Name + ", it's your turn!");
                playerSymbol = 'O';
                player1 = true;
            }
            int[] userCoords = spaceChooser(myGrid.gridArray, ncScan);
            myGrid.fillGrid(userCoords, playerSymbol);
            char[] winner_data = myGrid.checkWin();
            myGrid.printGrid();
            if (winner_data[0] != '0') {
                printWinMessage(winner_data, p1Name, p2Name);
                gameNotWon = false;
            }
        }
        System.out.println("Thank you for playing!");
    }
    private int[] spaceChooser(char[][] grid, Scanner scScan) {
        boolean validSpace = false;
        int[] chosenCoords = new int[2];
        while (!validSpace) {
            System.out.print("Enter the row of the space you would like to fill >>> ");
            chosenCoords[0] = scScan.nextInt() - 1;
            System.out.print("Enter the column of the space you would like to fill >>> ");
            chosenCoords[1] = scScan.nextInt() - 1;
            if (chosenCoords[0] >= grid.length || chosenCoords[1] >= grid.length || chosenCoords[0] < 0 || chosenCoords[1] < 0) {
                System.out.println("Chosen coordinates do not exist. Please choose a different space.");
            } else if (grid[chosenCoords[0]][chosenCoords[1]] != '#') {
                System.out.println("This space is already full. Please choose a different space.");
            } else {
                validSpace = true;
            }
        }
        return chosenCoords;
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
