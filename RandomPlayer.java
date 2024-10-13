import java.util.Random;

public class RandomPlayer extends Player {
    Random randomPlayerRandom = new Random();
    public RandomPlayer(char playerSymbol) {
        super(playerSymbol);
        playerName = setName();
    }
    private String setName() {
        return "Randomised Computer";
    }
    public int[] chooseSpace(Grid grid) {
        boolean validSpace = false;
        int[] chosenCoords = new int[2];
        while (!validSpace) {
            chosenCoords[0] = randomPlayerRandom.nextInt(grid.gridArray.length);
            chosenCoords[1] = randomPlayerRandom.nextInt(grid.gridArray.length);
            if (grid.gridArray[chosenCoords[0]][chosenCoords[1]] == '#') {
                validSpace = true;
            }
        }
        return chosenCoords;
    }
}
