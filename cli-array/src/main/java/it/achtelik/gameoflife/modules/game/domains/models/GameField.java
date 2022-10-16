package it.achtelik.gameoflife.modules.game.domains.models;

import java.util.Set;

public class GameField {

    private final int size;
    private boolean[][] cells;

    public GameField(int size, Set<GameCellPosition> cellsAlive) {
        this.size = size;
        cells = new boolean[size][size];
        // All field are at the beginning dead.
        for (int xPos = 0; xPos < size; xPos++) {
            for (int yPos = 0; yPos < size; yPos++) {
                cells[xPos][yPos] = false;
            }
        }
        // Based on the ui input set cells to alive.
        cellsAlive.forEach(gameCellPosition -> cells[gameCellPosition.x()][gameCellPosition.y()] = true);
    }

    public int getSize() {
        return size;
    }

    public void calculateToNewGeneration() {
        var cellsNewGeneration = new boolean[size][size];
        for (int xPos = 0; xPos < size; xPos++) {
            for (int yPos = 0; yPos < size; yPos++) {
                // Per cell count the alive neighbours and den set the new status of the cell inside the new array.
                var aliveNeighboursCount = countAliveNeighbours(xPos, yPos);
                cellsNewGeneration[xPos][yPos] = calculateNewCellStatus(cells[xPos][yPos], aliveNeighboursCount);
            }
        }
        cells = cellsNewGeneration;
    }

    private int countAliveNeighbours(int originXPos, int originYPos) {
        var aliveNeighbours = 0;
        for (int xPosOffset = -1; xPosOffset <= 1; xPosOffset++) {
            for (int yPosOffset = -1; yPosOffset <= 1; yPosOffset++) {
                var xPos = originXPos + xPosOffset;
                var yPos = originYPos + yPosOffset;
                // Watch only for alive neighbours if they are inside the gameField.
                if ((0 != xPosOffset || 0 != yPosOffset) && (xPos >= 0 && xPos < size && yPos >= 0 && yPos < size)) {
                    aliveNeighbours += cells[xPos][yPos] ? 1 : 0;
                }
            }
        }
        return aliveNeighbours;
    }

    private boolean calculateNewCellStatus(boolean isCellAlive, int aliveNeighboursCount) {
        // Check the game of life rules.
        return (!isCellAlive && aliveNeighboursCount == 3) || (isCellAlive && (aliveNeighboursCount == 2 || aliveNeighboursCount == 3));
    }

    public boolean isCellAlive(int xPos, int yPos) {
        return cells[xPos][yPos];
    }
}