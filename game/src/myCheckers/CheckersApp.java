package myCheckers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static myCheckers.Board.createContent;
import static myCheckers.Board.tileGroup;

public class CheckersApp extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Scene scene = new Scene(createContent(Board.pieceGroup, tileGroup));
        primaryStage.setTitle("My CheckersApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
