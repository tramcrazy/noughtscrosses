import java.util.Random;

public class RandomPlayer extends Player {
    Random randomPlayerRandom = new Random();

    public RandomPlayer(int playerNum) {
        super(playerNum);
        playerName = setName();
    }

    @Override
    protected String setName() {
        return "Randomised Computer " + playerNum;
    }

    @Override
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

    @Override
    public void printPlayMessage() {
        System.out.println(playerName + " is playing...");
    }
}
