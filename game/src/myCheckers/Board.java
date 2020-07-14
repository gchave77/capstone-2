package myCheckers;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import static myCheckers.BoardBehavior.makePiece;

public class Board
{
    static final int TILE_SIZE = 100;
    static final int WIDTH = 8;
    static final int HEIGHT = 8;

    public static Group tileGroup = new Group();
    public static Group pieceGroup = new Group();

    public static Tile[][] board = new Tile[WIDTH][HEIGHT];
    public static Object piece;

    protected static Parent createContent(Group pieceGroup, Group tileGroup)
    {
        // creates the window pane object for the board
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

                PieceCoordinates piece = null;

                // for rows 0 - 2, set piece type to red and make piece on odd (darker) tiles
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

}