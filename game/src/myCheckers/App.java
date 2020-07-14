package myCheckers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static myCheckers.Board.*;

public class App extends Application
{

    public static void main(String[] args)
    {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage)
    {
        Scene scene = new Scene(createContent(pieceGroup, tileGroup));
        primaryStage.setTitle("My Checkers App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
