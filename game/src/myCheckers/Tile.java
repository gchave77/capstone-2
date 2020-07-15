package myCheckers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle
{
    private PieceCoordinates piece;

    public boolean hasPiece()
    {
        return piece != null;
    }

    public PieceCoordinates getPiece()
    {
        return piece;
    }

    public void setPiece(PieceCoordinates piece)
    {
        this.piece = piece;
    }

    public Tile(boolean light, int x, int y)
    {
        // create the tile objects on the board
        setWidth(Board.TILE_SIZE);
        setHeight(Board.TILE_SIZE);

        relocate(x * Board.TILE_SIZE, y * Board.TILE_SIZE);

        setFill(light ? Color.LIGHTGREY : Color.GRAY);

    }

}
