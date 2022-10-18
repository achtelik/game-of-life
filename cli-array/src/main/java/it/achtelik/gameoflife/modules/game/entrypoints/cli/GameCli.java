package it.achtelik.gameoflife.modules.game.entrypoints.cli;

import it.achtelik.gameoflife.modules.game.domains.models.GameField;

public class GameCli {

    private final GameCliPrinter gameCliPrinter;
    private final GameCliReader gameCliReader;

    public GameCli() {
        gameCliPrinter = new GameCliPrinter();
        gameCliReader = new GameCliReader();
    }

    public GameCli(GameCliPrinter gameCliPrinter, GameCliReader gameCliReader) {
        this.gameCliPrinter = gameCliPrinter;
        this.gameCliReader = gameCliReader;
    }

    public void run() {
        var gameProperties = gameCliReader.readProperties();

        var gameField = new GameField(gameProperties.gameFieldSize(), gameProperties.initialLivingCellPositions());
        for (int i = 0; i < 100; i++) {
            gameCliPrinter.printGameField(gameField);
            gameField.calculateToNewGeneration();
        }
        gameCliPrinter.printGameField(gameField);
    }
}
