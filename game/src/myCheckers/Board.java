package myCheckers;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class Board
{
    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    public static Group tileGroup = new Group();
    public static Group pieceGroup = new Group();
    private static Tile[][] board = new Tile[WIDTH][HEIGHT];

    protected static Parent createContent(Group pieceGroup, Group tileGroup)
    {
        Pane root = new Pane();
        root.setPrefSize(TILE_SIZE * WIDTH, TILE_SIZE * HEIGHT);

        // display board tiles in pane
        root.getChildren().addAll(tileGroup, pieceGroup);

        // create board tiles
        for (int y=0; y<HEIGHT; y++) {
            for (int x=0; x<WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;
                tileGroup.getChildren().add(tile);

                Piece piece = null;

                if (y <= 2 && (x+y) % 2 != 0) {
                    piece = makePiece(PieceType.RED, x, y);
                }
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

    private static Piece makePiece(PieceType type, int x, int y) {
        Piece piece = new Piece(type, x, y);

        return piece;
    }

}
