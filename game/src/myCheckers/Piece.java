package myCheckers;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import static myCheckers.Board.TILE_SIZE;


public class Piece extends StackPane
{
    private PieceType type;

    public PieceType getType()
    {
        return type;
    }

    public Piece(PieceType type, int x, int y)
    {
        this.type = type;
//  create checker ellipse with App import of TILE_SIZE
        Ellipse bg = new Ellipse(TILE_SIZE * .3125, TILE_SIZE * .26);
        bg.setFill(type == PieceType.RED ? Color.RED : Color.WHITE);

        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(TILE_SIZE * .03);

    }
}
