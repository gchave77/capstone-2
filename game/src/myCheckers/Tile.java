package myCheckers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle
{
    private Piece piece;

    public boolean hasPiece()
    {
        return piece != null;
    }

    public Piece getPiece()
    {
        return piece;
    }

    public void setPiece(Piece piece)
    {
        this.piece = piece;
    }

    public Tile(boolean light, int x, int y)
    {
        setWidth(Board.TILE_SIZE);
        setHeight(Board.TILE_SIZE);
        relocate(x * Board.TILE_SIZE, y * Board.TILE_SIZE);

//        setFill(light ? Color.valueOf("#ebf0f2") : Color.valueOf("#1e84d0"));
        setFill(light ? Color.LIGHTGREY : Color.GRAY);

    }

}
