package board

import board.Direction.*

open class SquareBoardImpl(private val board: MutableList<MutableList<Cell>>) : SquareBoard {
    override val width = board.size

    override fun getCellOrNull(i: Int, j: Int): Cell? =
        board.getOrNull(i - 1)?.getOrNull(j - 1)

    override fun getCell(i: Int, j: Int): Cell = board[i - 1][j - 1]

    override fun getAllCells(): Collection<Cell> = board.flatten()

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> =
        board[i - 1].toMutableList()
            .apply { removeIf { it.j !in jRange } }
            .apply { if (jRange.first > jRange.last) reverse() }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> =
        board.flatten().toMutableList()
            .apply { removeIf { it.i !in iRange || it.j != j } }
            .apply { if (iRange.first > iRange.last) reverse() }


    override fun Cell.getNeighbour(direction: Direction): Cell? =
        when (direction) {
            UP -> getCellOrNull(i - 1, j)
            DOWN -> getCellOrNull(i + 1, j)
            RIGHT -> getCellOrNull(i, j + 1)
            LEFT -> getCellOrNull(i, j - 1)
        }

}

class GameBoardImpl<T>(board: MutableList<MutableList<Cell>>) : SquareBoardImpl(board), GameBoard<T> {
    private val values: MutableMap<Cell, T?> = board.flatten().associateWith { null }.toMutableMap()

    override fun get(cell: Cell): T? = values[cell]

    override fun set(cell: Cell, value: T?) = values.set(cell, value)

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> = values.filter { predicate(it.value) }.keys

    override fun find(predicate: (T?) -> Boolean): Cell? = values.filter { predicate(it.value) }.keys.firstOrNull()

    override fun any(predicate: (T?) -> Boolean): Boolean = values.any { predicate(it.value) }

    override fun all(predicate: (T?) -> Boolean): Boolean = values.all { predicate(it.value) }


}

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(
    MutableList(width) { i ->
        MutableList(width) { j ->
            Cell(i + 1, j + 1)
        }
    }
)

fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl(
    MutableList(width) { i ->
        MutableList(width) { j ->
            Cell(i + 1, j + 1)
        }
    }
)

