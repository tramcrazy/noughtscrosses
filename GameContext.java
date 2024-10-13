public class GameContext {

    private final Player player1;
    private final Player player2;
    private final Grid grid;

    private boolean isPlayer1;
    private boolean gameNotWon = true;

    public GameContext(GameContextBuilder ctxBuilder) {
        this.player1 = ctxBuilder.player1;
        this.player2 = ctxBuilder.player2;
        this.grid = ctxBuilder.grid;
        this.isPlayer1 = ctxBuilder.isPlayer1;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Grid getGrid() {
        return grid;
    }

    public boolean isPlayer1() {
        return isPlayer1;
    }

    public void switchPlayer() {
        this.isPlayer1 = !isPlayer1;
    }

    public boolean gameStillRunning() {
        return gameNotWon;
    }

    public void finishGame() {
        this.gameNotWon = false;
    }

    public static final class GameContextBuilder {
        private Player player1;
        private Player player2;
        private Grid grid;
        private boolean isPlayer1;

        public GameContextBuilder player1(Player player) {
            this.player1 = player;
            return this;
        }

        public GameContextBuilder player2(Player player) {
            this.player2 = player;
            return this;
        }

        public GameContextBuilder grid(Grid grid) {
            this.grid = grid;
            return this;
        }

        public GameContextBuilder isPlayer1(boolean isPlayer1) {
            this.isPlayer1 = isPlayer1;
            return this;
        }

        public GameContext build() {
            return new GameContext(this);
        }
    }
}
