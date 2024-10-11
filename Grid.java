import java.util.Arrays;

public class Grid {
    public Grid(int gridSize, char emptyChar) {
        char[][] gridArray = new char[gridSize][gridSize];
        for (char[] row : gridArray) {
            Arrays.fill(row, emptyChar);
        }
    }
}
