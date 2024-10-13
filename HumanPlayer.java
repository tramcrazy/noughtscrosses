import java.util.Scanner;

public class HumanPlayer extends Player {
    Scanner humanPlayerScanner = new Scanner(System.in);

    public HumanPlayer(int playerNum) {
        super(playerNum);
        playerName = setName();
    }

    @Override
    protected String setName() {
        System.out.print("Enter Player " + playerNum + "'s name (" + playerSymbol + ") >>> ");
        return humanPlayerScanner.next();
    }

    @Override
    public int[] chooseSpace(Grid grid) {
        boolean validSpace = false;
        int[] chosenCoords = new int[2];
        while (!validSpace) {
            System.out.print("Enter the row of the space you would like to fill >>> ");
            chosenCoords[0] = humanPlayerScanner.nextInt() - 1;
            System.out.print("Enter the column of the space you would like to fill >>> ");
            chosenCoords[1] = humanPlayerScanner.nextInt() - 1;
            if (chosenCoords[0] >= grid.gridArray.length || chosenCoords[1] >= grid.gridArray.length || chosenCoords[0] < 0 || chosenCoords[1] < 0) {
                System.out.println("Chosen coordinates do not exist. Please choose a different space.");
            } else if (grid.gridArray[chosenCoords[0]][chosenCoords[1]] != '#') {
                System.out.println("This space is already full. Please choose a different space.");
            } else {
                validSpace = true;
            }
        }
        return chosenCoords;
    }
}
