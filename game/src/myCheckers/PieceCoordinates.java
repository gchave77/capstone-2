package myCheckers;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;


public class PieceCoordinates extends StackPane
{
    private PieceType type;

    // how we track the mouse clicked coordinates and the old coordinates
    private double mouseX, mouseY;
    private double oldX, oldY;

    public PieceType getType()
    {
        return type;
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public PieceCoordinates(PieceType type, int x, int y)
    {
        this.type = type;

        move(x, y);

        // create checkers with Class import of TILE_SIZE
        Ellipse bg = new Ellipse(Board.TILE_SIZE * .3125, Board.TILE_SIZE * .26);
        bg.setFill(Color.BLACK);

        // paint background of piece
        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(Board.TILE_SIZE * .03);

        // center background in tile
        bg.setTranslateX((Board.TILE_SIZE - Board.TILE_SIZE * .3125 * 2 ) /2);
        bg.setTranslateY((Board.TILE_SIZE - Board.TILE_SIZE * .26 * 2 ) /2 + Board.TILE_SIZE * .07);

        // paint piece red or black
        Ellipse ellipse = new Ellipse(Board.TILE_SIZE * .3125, Board.TILE_SIZE * .26);
        ellipse.setFill(type == PieceType.RED ? Color.RED : Color.WHITE);
        // ternary

        // paint shadow of piece
        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(Board.TILE_SIZE * .03);

        // center shadow in tile
        ellipse.setTranslateX((Board.TILE_SIZE - Board.TILE_SIZE * .3125 * 2 ) /2);
        ellipse.setTranslateY((Board.TILE_SIZE - Board.TILE_SIZE * .26 * 2 ) /2);

        getChildren().addAll(bg, ellipse);

        // Lambda to capture mouse clicked coordinates
        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        // Lambda for the actual mouse movement
        // * Sets the piece's x,y properties in order to relocate to the new x,y location
        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
        });
    }

    public void move(int x, int y) {
        // legal move made, so relocate the moved piece
        oldX = x * Board.TILE_SIZE;
        oldY = y * Board.TILE_SIZE;
        relocate(oldX, oldY); // values come from newX and newY

    }

    // cancel illegal move
    public void abortMove() {
        relocate(oldX, oldY);
    }

}
