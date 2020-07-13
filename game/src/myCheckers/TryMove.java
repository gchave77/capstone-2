package myCheckers;

import static myCheckers.Board.*;

public class TryMove
{
    public static MoveState tryMove(Piece piece, int newX, int newY) {

        // don't allow move to an occupied tile, or to an invalid even (light-colored) tile on the board
        if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0) {
            return new MoveState(MoveType.NONE);
        }

        // track potential move coordinates to test if legal
        int x0 = toBoard(piece.getOldX());
        int y0 = toBoard(piece.getOldY());

        // Check absolute value to allow move to valid tile, and in correct direction
        if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().moveDir) {
            return new MoveState(MoveType.NORMAL);

            // Check to allow for two step move (* 2) and in correct direction
        } else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().moveDir * 2) {

            // get coordinates on the tile that was jumped
            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;

            // Check if the jumped tile has an opponent's piece
            if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                return new MoveState(MoveType.CAPTURE, board[x1][y1].getPiece());
            }
        }

        // default is no move
        return new MoveState(MoveType.NONE);
    }

    public static int toBoard(double pixel) {
        // keep pieces centered in tile
        return (int)(pixel + TILE_SIZE / 2) / TILE_SIZE;
    }

}
