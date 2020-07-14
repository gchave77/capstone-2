package myCheckers;

import static myCheckers.Board.board;
import static myCheckers.Board.pieceGroup;
import static myCheckers.Move.toBoard;
import static myCheckers.Move.tryMove;

public interface MoveBehavior
{
    public static Piece makePiece(PieceType type, int x, int y)
    {
        Piece piece = new Piece(type, x, y);

        // Lambda method to get new layout and determine how to handle piece
        piece.setOnMouseReleased(e -> {
            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());

            MoveState result;

            if (newX < 0 || newY < 0 || newX >= Board.WIDTH || newY >= Board.HEIGHT) {
                // piece not moved so result is no move
                result = new MoveState(MoveType.NONE);
            } else {
                // piece has attempted move, so gather data of attempted move
                result = tryMove(piece, newX, newY);
            }

            // get the previous coordinates in order to clear the tile
            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());

            // abort illegal move, or perform normal or capture (jump) move
            switch (result.getType()) {
                case NONE:
                    // cancel illegal move
                    piece.abortMove();
                    break;
                case NORMAL:
                    // place piece in new position after move
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    break;
                case CAPTURE:
                    // place piece in new position after capture
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);

                    // remove captured piece
                    Piece otherPiece = result.getPiece(); // opponent's piece passed from Move
                    board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                    pieceGroup.getChildren().remove(otherPiece);
                    break;
            }
        });

        return piece;
    }

}
