package myCheckers;

import static myCheckers.Board.*;

public class TryMove
{
    public static MoveState tryMove(Piece piece, int newX, int newY) {

        // don't allow move to occupied tile, or to invalid tile
        if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0) {
            return new MoveState(MoveType.NONE);
        }

        int x0 = toBoard(piece.getOldX());
        int y0 = toBoard(piece.getOldY());

        // allow move to valid tile, and in correct direction
        if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().moveDir) {
            return new MoveState(MoveType.NORMAL);

            // already checked for invalid tile above, so begin jump (KILL) move logic
        } else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().moveDir * 2) {

            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;

            if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                return new MoveState(MoveType.KILL, board[x1][y1].getPiece());
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
