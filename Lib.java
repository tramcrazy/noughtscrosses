import java.util.Arrays;
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
        char[][] myGrid = createGrid();
        System.out.println("Here is the grid! A # symbol represents an empty space.");
        printGrid(myGrid);
        boolean gameNotWon = true;
        boolean player1 = myRandom.nextBoolean();
        printStartMessage(player1, p1Name, p2Name);
        char playerSymbol = '#';
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
            int[] userCoords = spaceChooser(myGrid, ncScan);
            myGrid = fillGrid(myGrid, userCoords, playerSymbol);
            char[] winner_data = checkWin(myGrid);
            printGrid(myGrid);
            if (winner_data[0] != '0') {
                printWinMessage(winner_data, p1Name, p2Name);
                gameNotWon = false;
            }
        }
        System.out.println("Thank you for playing!");
    }
    private char[][] createGrid() {
        char[][] grid = new char[3][3];
        for (char[] row : grid) {
            Arrays.fill(row, '#');
        }
        return grid;
    }
    private void printGrid(char[][] grid) {
        int[] firstRow = {1, 2, 3};
        System.out.print("   ");
        for (int i : firstRow) {
            System.out.print(i + "  ");
        }
        System.out.println();
        for (int i = 0; i < grid.length; i++) {
            System.out.print(i + 1 + " ");
            System.out.println(Arrays.toString(grid[i]));
        }
    }
    private int[] spaceChooser(char[][] grid, Scanner scScan) {
        boolean validSpace = false;
        int[] chosenCoords = new int[2];
        while (!validSpace) {
            System.out.print("Enter the row of the space you would like to fill >>> ");
            chosenCoords[0] = scScan.nextInt() - 1;
            System.out.print("Enter the column of the space you would like to fill >>> ");
            chosenCoords[1] = scScan.nextInt() - 1;
            if (grid[chosenCoords[0]][chosenCoords[1]] != '#') {
                System.out.println("This space is already full. Please choose a different space.");
            } else {
                validSpace = true;
            }
        }
        return chosenCoords;
    }
    private char[][] fillGrid(char[][] originalGrid, int[] chosenCoords, char playerSymbol) {
        originalGrid[chosenCoords[0]][chosenCoords[1]] = playerSymbol;
        return originalGrid;
    }
    private char[] checkWin(char[][] grid) {
        for (char[] row : grid) {
            if (isAllEqual(row) && (row[0] != '#')) {
                return new char[]{'1', row[0]};
            }
        }
        char[] iterArray = new char[grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                iterArray[j] = grid[j][i];
            }
            if (isAllEqual(iterArray) && iterArray[0] != '#') {
                return new char[]{'1', iterArray[0]};
            }
        }
        for (int i = 0; i < grid.length; i++) {
            iterArray[i] = grid[i][i];
        }
        if (isAllEqual(iterArray) && iterArray[0] != '#') {
            return new char[]{'1', iterArray[0]};
        }
        for (int i = 0; i < grid.length; i++) {
            iterArray[i] = grid[i][grid.length - i];
        }
        if (isAllEqual(iterArray) && iterArray[0] != '#') {
            return new char[]{'1', iterArray[0]};
        }
        char[] fullGrid = new char[grid.length * grid.length];
        int fullIter = 0;
        for (char[] row : grid) {
            for (int j = 0; j < grid.length; j++) {
                fullGrid[fullIter] = row[j];
            }
            fullIter++;
        }
        if (!noHashes(fullGrid)) {
            return new char[]{'2', '#'};
        }
        return new char[]{'0', '#'};
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
    private boolean isAllEqual(char[] checkArray) {
        if (checkArray == null || checkArray.length == 0) {
            return false;
        }
        for (char c : checkArray) {
            if (c != checkArray[0]) {
                return false;
            }
        }
        return true;
    }
    private boolean noHashes(char[] checkArray) {
        if (checkArray == null) {
            return true;
        }
        for (char c : checkArray) {
            if (c == '#') {
                return false;
            }
        }
        return true;
    }
}
