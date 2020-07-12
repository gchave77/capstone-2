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

        relocate(x * TILE_SIZE, y * TILE_SIZE);

//  create checkers with Class import of TILE_SIZE
        Ellipse bg = new Ellipse(TILE_SIZE * .3125, TILE_SIZE * .26);
        bg.setFill(Color.BLACK);

        // paint background of piece
        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(TILE_SIZE * .03);

        // center background in tile
        bg.setTranslateX((TILE_SIZE - TILE_SIZE * .3125 * 2 ) /2);
        bg.setTranslateY((TILE_SIZE - TILE_SIZE * .26 * 2 ) /2 + TILE_SIZE * .07);

        // paint piece red or black
        Ellipse ellipse = new Ellipse(TILE_SIZE * .3125, TILE_SIZE * .26);
        ellipse.setFill(type == PieceType.RED ? Color.RED : Color.WHITE);

        // paint shadow of piece
        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(TILE_SIZE * .03);

        // center shadow in tile
        ellipse.setTranslateX((TILE_SIZE - TILE_SIZE * .3125 * 2 ) /2);
        ellipse.setTranslateY((TILE_SIZE - TILE_SIZE * .26 * 2 ) /2);

        getChildren().addAll(bg, ellipse);
    }

}
