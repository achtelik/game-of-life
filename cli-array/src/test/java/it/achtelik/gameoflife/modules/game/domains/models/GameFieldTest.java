package it.achtelik.gameoflife.modules.game.domains.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

class GameFieldTest {

    @Test
    void calculateToNewGeneration() {
        var size = 3;
        var initialAliveCellPositions = new HashSet<GameCellPosition>();
        initialAliveCellPositions.add(new GameCellPosition(0, 1));
        initialAliveCellPositions.add(new GameCellPosition(1, 1));
        initialAliveCellPositions.add(new GameCellPosition(2, 1));

        var gameField = new GameField(size, initialAliveCellPositions);

        gameField.calculateToNewGeneration();

        Assertions.assertEquals(3, gameField.getSize());
        Assertions.assertFalse(gameField.isCellAlive(0, 0));
        Assertions.assertTrue(gameField.isCellAlive(1, 0));
        Assertions.assertFalse(gameField.isCellAlive(2, 0));
        Assertions.assertFalse(gameField.isCellAlive(0, 1));
        Assertions.assertTrue(gameField.isCellAlive(1, 1));
        Assertions.assertFalse(gameField.isCellAlive(2, 1));
        Assertions.assertFalse(gameField.isCellAlive(0, 2));
        Assertions.assertTrue(gameField.isCellAlive(1, 2));
        Assertions.assertFalse(gameField.isCellAlive(2, 2));
    }
}