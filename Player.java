public abstract class Player {
    int playerNum;
    String playerName;
    char playerSymbol;
    public Player(int playerNum) {
        this.playerNum = playerNum;
        if (playerNum == 1) {
            playerSymbol = 'X';
        } else {
            playerSymbol = 'O';
        }
    }
}