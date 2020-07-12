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

    protected static Parent createContent(Group pieceGroup, Group tileGroup)
    {
        Pane root = new Pane();
        root.setPrefSize(TILE_SIZE * WIDTH, TILE_SIZE * HEIGHT);
//        display tiles in pane
        root.getChildren().addAll(tileGroup, pieceGroup);

        for (int y=0; y<HEIGHT; y++)
        {
            for (int x=0; x<WIDTH; x++)
            {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                tileGroup.getChildren().add(tile);
            }
        }
        return root;
    }
}
