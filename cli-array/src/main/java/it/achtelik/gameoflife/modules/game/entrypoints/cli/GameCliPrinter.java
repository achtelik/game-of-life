package it.achtelik.gameoflife.modules.game.entrypoints.cli;

import it.achtelik.gameoflife.modules.game.domains.models.GameField;
import org.apache.commons.lang3.StringUtils;

import java.io.PrintStream;

public class GameCliPrinter {
    private static final PrintStream printStream = System.out;

    private static final String CHAR_NEW_LINE = System.lineSeparator();
    private static final String CHAR_BORDER_CORNER = "+";
    private static final String CHAR_BORDER_HORIZONTAL = "-";
    private static final String CHAR_BORDER_VERTICAL = "|";
    private static final String CHAR_CELL_DEAD = "·"; // For manuel testing use ·
    private static final String CHAR_CELL_ALIVE = "O";

    private String printOld = "";

    void printGameField(GameField gameField) {
        StringBuilder buffer = new StringBuilder();

        printHorizontalBorder(gameField, buffer);
        printGameFieldRows(gameField, buffer);
        printHorizontalBorder(gameField, buffer);

        var printNew = buffer.toString();
        if (!printNew.equals(printOld)) {
            printOld = printNew;
            printStream.println(buffer);
        }
    }

    void printGameFieldRows(GameField gameField, StringBuilder buffer) {
        for (int rowIndex = 0; rowIndex < gameField.getSize(); rowIndex++) {
            buffer.append(CHAR_BORDER_VERTICAL);
            for (int columnIndex = 0; columnIndex < gameField.getSize(); columnIndex++) {
                buffer.append(gameField.isCellAlive(columnIndex, rowIndex) ? CHAR_CELL_ALIVE : CHAR_CELL_DEAD);
            }
            buffer.append(CHAR_BORDER_VERTICAL);
            buffer.append(CHAR_NEW_LINE);
        }
    }

    void printHorizontalBorder(GameField gameField, StringBuilder buffer) {
        buffer.append(CHAR_BORDER_CORNER);
        buffer.append(StringUtils.repeat(CHAR_BORDER_HORIZONTAL, gameField.getSize()));
        buffer.append(CHAR_BORDER_CORNER);
        buffer.append(CHAR_NEW_LINE);
    }
}
