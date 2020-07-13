package myCheckers;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import static myCheckers.TryMove.toBoard;
import static myCheckers.TryMove.tryMove;

public class Board
{
    static final int TILE_SIZE = 100;
    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;

    public static Group tileGroup = new Group();
    public static Group pieceGroup = new Group();

    public static Tile[][] board = new Tile[WIDTH][HEIGHT];

    protected static Parent createContent(Group pieceGroup, Group tileGroup)
    {
        Pane root = new Pane();
        root.setPrefSize(TILE_SIZE * WIDTH, TILE_SIZE * HEIGHT);

        // display pieces in tiles within pane
        root.getChildren().addAll(tileGroup, pieceGroup);

        // create board tiles and pieces in proper tiles
        for (int y=0; y<HEIGHT; y++) {
            for (int x=0; x<WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;
                tileGroup.getChildren().add(tile);

                Piece piece = null;

                // for rows 0 - 2, set piece type to red and make piece
                if (y <= 2 && (x+y) % 2 != 0) {
                    piece = makePiece(PieceType.RED, x, y);
                }
                // for rows 5 - 7, set piece type to white and make piece
                if (y >= 5 && (x+y) % 2 != 0) {
                    piece = makePiece(PieceType.WHITE, x, y);
                }
                if (piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }
        return root;
    }

    public static Piece makePiece(PieceType type, int x, int y) {
        Piece piece = new Piece(type, x, y);

        // Lambda method to get new layout before setting piece on board
        piece.setOnMouseReleased(e -> {
            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());

            MoveState result;

            if (newX < 0 || newY < 0 || newX >= WIDTH || newY >= HEIGHT) {
                // piece not moved so result is no move
                result = new MoveState(MoveType.NONE);
            } else {
                // piece has attempted move, so gather data of attempted move
                result = tryMove(piece, newX, newY);
            }

            // get the previous coordinates in order to clear the tile
            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());

            // validate the attempted move
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
                    Piece otherPiece = result.getPiece(); // opponent's piece passed from TryMove
                    board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                    pieceGroup.getChildren().remove(otherPiece);
                    break;
            }
        });

        return piece;
    }

}
