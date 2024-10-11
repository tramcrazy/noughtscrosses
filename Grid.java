import java.util.Arrays;

public class Grid {
    public char[][] gridArray;
    private final char emptyChar;
    public Grid(int gridSize, char emptyChar) {
        gridArray = new char[gridSize][gridSize];
        for (char[] row : gridArray) {
            Arrays.fill(row, emptyChar);
        }
        this.emptyChar = emptyChar;
    }
    public void printGrid() {
        int[] firstRow = new int[gridArray.length];
        for (int i = 0; i < gridArray.length; i++) {
            firstRow[i] = i + 1;
        }
        System.out.print("   ");
        for (int i : firstRow) {
            System.out.print(i + "  ");
        }
        System.out.println();
        for (int i = 0; i < gridArray.length; i++) {
            System.out.print(i + 1 + " ");
            System.out.println(Arrays.toString(gridArray[i]));
        }
    }
    public void fillGrid(int[] chosenCoords, char playerSymbol) {
        gridArray[chosenCoords[0]][chosenCoords[1]] = playerSymbol;
    }
    public char[] checkWin() {
        // Row check
        for (char[] row : gridArray) {
            if (isAllEqual(row) && (row[0] != emptyChar)) {
                return new char[]{'1', row[0]};
            }
        }
        // Column check
        char[] iterArray = new char[gridArray.length];
        for (int i = 0; i < gridArray.length; i++) {
            for (int j = 0; j < gridArray.length; j++) {
                iterArray[j] = gridArray[j][i];
            }
            if (isAllEqual(iterArray) && iterArray[0] != emptyChar) {
                return new char[]{'1', iterArray[0]};
            }
        }
        // Left diagonal
        for (int i = 0; i < gridArray.length; i++) {
            iterArray[i] = gridArray[i][i];
        }
        if (isAllEqual(iterArray) && iterArray[0] != emptyChar) {
            return new char[]{'1', iterArray[0]};
        }
        // Right diagonal
        for (int i = 0; i < gridArray.length; i++) {
            iterArray[i] = gridArray[i][gridArray.length - 1 - i];
        }
        if (isAllEqual(iterArray) && iterArray[0] != emptyChar) {
            return new char[]{'1', iterArray[0]};
        }
        if (gridFull()) {
            return new char[]{'2', emptyChar};
        }
        return new char[]{'0', emptyChar};
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
    private boolean gridFull() {
        for (char[] row : gridArray) {
            for (char c : row) {
                if (c == emptyChar) {
                    return false;
                }
            }
        }
        return true;
    }
}
