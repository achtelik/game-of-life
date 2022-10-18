package it.achtelik.gameoflife.modules.game.entrypoints.cli;

import it.achtelik.gameoflife.modules.game.domains.models.GameCellPosition;
import it.achtelik.gameoflife.modules.game.domains.models.GameProperties;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class GameCliReader {
    private static final PrintStream printStream = System.out;
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    GameProperties readProperties() {
        printStream.print("""
                Welcome to Game of Life
                ***********************
                               
                Please enter your properties.
                Game field size:""");
        var gameFieldSize = Integer.parseInt(scanner.nextLine());

        printStream.print("""
                Do you want to setup the initial state
                * manual (1)
                * random (2)
                Your selection:""");
        var setupType = Integer.parseInt(scanner.nextLine());
        var livingCells = (1 == setupType ? setupLivingCellsManual() : setupLivingCellsRandom(gameFieldSize));

        return new GameProperties(gameFieldSize, livingCells);
    }

    Set<GameCellPosition> setupLivingCellsManual() {
        printStream.println("Setup the initial fields. Input x-y x-y x-y ...");
        var initialCells = scanner.nextLine();

        return Arrays.stream(initialCells.split(" ")).map(cellString -> {
            var cellPositions = cellString.split("-");
            return new GameCellPosition(Integer.parseInt(cellPositions[0]), Integer.parseInt(cellPositions[1]));
        }).collect(Collectors.toSet());
    }

    Set<GameCellPosition> setupLivingCellsRandom(int gameFieldSize) {
        printStream.printf("""
                Living cells will be placed random.
                Number of living cells = %s
                %n""", gameFieldSize);
        var numberOfLivingCells = gameFieldSize * gameFieldSize * 0.2;
        var livingCellPositions = new HashSet<GameCellPosition>();
        for (int i = 0; i < numberOfLivingCells; i++) {
            livingCellPositions.add(new GameCellPosition(random.nextInt(gameFieldSize), random.nextInt(gameFieldSize)));
        }
        return livingCellPositions;
    }
}
