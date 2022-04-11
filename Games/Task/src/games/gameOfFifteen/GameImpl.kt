package games.gameOfFifteen

import board.Direction
import board.GameBoard
import board.GameBoardImpl
import board.createGameBoard
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
    GameImpl(initializer)

class GameImpl(private val initializer: GameOfFifteenInitializer) : Game {
    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        val permutation = initializer.initialPermutation
        board.getAllCells().forEachIndexed { index, cell ->
            board[cell] = permutation.getOrNull(index)
        }
    }

    override fun canMove(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasWon(): Boolean {
        TODO("Not yet implemented")
    }

    override fun processMove(direction: Direction) {
        TODO("Not yet implemented")
    }

    override fun get(i: Int, j: Int): Int? {
        TODO("Not yet implemented")
    }

}