import java.util.Random;
import java.util.Scanner;

public class Lib {

    private final Scanner ncScan = new Scanner(System.in);
    private final Random myRandom = new Random();

    private GameContext context;

    public void determinePlayers(GameContext.GameContextBuilder ctxBuilder) {
        System.out.println("Is Player 1 a (H)uman or (R)andomised Computer Player?");
        char p1Type = ncScan.next().charAt(0);
        System.out.println("Is Player 2 a (H)uman or (R)andomised Computer Player?");
        char p2Type = ncScan.next().charAt(0);
        Player player1;
        Player player2;
        if (p1Type == 'H') {
            player1 = new HumanPlayer(1);
        } else {
            player1 = new RandomPlayer(1);
        }
        if (p2Type == 'H') {
            player2 = new HumanPlayer(2);
        } else {
            player2 = new RandomPlayer(2);
        }
        ctxBuilder.player1(player1);
        ctxBuilder.player2(player2);
    }

    private void determineGrid(GameContext.GameContextBuilder ctxBuilder) {
        System.out.print("What size of game do you want to play? e.g. 3,4,5 >>> ");
        int gridSize = ncScan.nextInt();
        Grid myGrid = new Grid(gridSize, '#');
        System.out.println("Here is the grid! A # symbol represents an empty space.");
        myGrid.printGrid();
        ctxBuilder.grid(myGrid);
    }

    private void playGame() {
        while (context.gameStillRunning()) {
            Player currentPlayer;
            if (context.isPlayer1()) {
                currentPlayer = context.getPlayer1();
            } else {
                currentPlayer = context.getPlayer2();
            }

            currentPlayer.printPlayMessage();
            context.switchPlayer();
            context.getGrid().fillGrid(currentPlayer.chooseSpace(context.getGrid()), currentPlayer.playerSymbol);

            GameStatus gameStatus = context.getGrid().checkWin();
            context.getGrid().printGrid();
            if (gameStatus != GameStatus.NO_CHANGE) {
                printWinMessage(gameStatus);
                context.finishGame();
            }
        }
    }

    public void runNoughtsCrosses() {
        GameContext.GameContextBuilder ctxBuilder = new GameContext.GameContextBuilder();
        System.out.println("Welcome to Noughts and Crosses!");
        determinePlayers(ctxBuilder);
        determineGrid(ctxBuilder);
        ctxBuilder.isPlayer1(myRandom.nextBoolean());

        context = ctxBuilder.build();
        printStartMessage(context.isPlayer1());
        playGame();
        System.out.println("Thank you for playing!");
    }

    private void printWinMessage(GameStatus status) {
        if (status == GameStatus.DRAW) {
            System.out.println("Draw!");
            return;
        }

        String winner;
        if (status == GameStatus.X_WIN) winner = context.getPlayer1().getPlayerName();
        else winner = context.getPlayer2().getPlayerName();
        System.out.printf("%s wins!\n", winner);
    }

    private void printStartMessage(boolean player1) {
        String starter;
        if (player1) starter = context.getPlayer1().getPlayerName();
        else starter = context.getPlayer2().getPlayerName();
        System.out.printf("%s to start!\n", starter);
    }
}
