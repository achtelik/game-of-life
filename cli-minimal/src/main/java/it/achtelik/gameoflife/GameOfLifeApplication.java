package it.achtelik.gameoflife;

import it.achtelik.gameoflife.modules.game.domains.models.GameField;
import it.achtelik.gameoflife.modules.game.entrypoints.cli.GameCliPrinter;
import it.achtelik.gameoflife.modules.game.entrypoints.cli.GameCliReader;

public class GameOfLifeApplication {

    public static void main(String[] args) {
        mainCli();
    }

    /**
     * This method contains the game logic process.
     * It also connects the ui with the game logic.
     */
    private static void mainCli() {
        var gameProperties = new GameCliReader().readProperties();

        var cliPrinter = new GameCliPrinter();
        var gameField = new GameField(gameProperties.gameFieldSize(), gameProperties.initialLivingCellPositions());
        for (int i = 0; i < 100; i++) {
            cliPrinter.printGameField(gameField);
            gameField.calculateToNewGeneration();
        }
        cliPrinter.printGameField(gameField);
    }
}
