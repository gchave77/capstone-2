package myCheckers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static myCheckers.Board.*;

public class CheckersApp extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        Scene scene = new Scene(createContent(pieceGroup, tileGroup));
        primaryStage.setTitle("My CheckersApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
