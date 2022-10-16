package it.achtelik.gameoflife.modules.game.domains.models;

import java.util.Set;

public record GameProperties(int gameFieldSize, Set<GameCellPosition> initialLivingCellPositions) {
}
