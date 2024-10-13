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
    protected abstract String setName();
    public abstract int[] chooseSpace(Grid grid);
    public void printPlayMessage() {
        System.out.println(playerName + ", it's your turn!");
    }
}