package checkers;

import javax.swing.*;
import java.awt.*;

/**
 * This app lets two users play checkers against each other. Red starts the game.
 * If a player can jump an opponent's piece, then the player must jump.
 * When a player can make no more moves, the game ends.
 */

public class App extends JPanel
{
    public static void main(String[] args)
    {
        /**
         * Defines a window showing a Checkers panel;
         * the program ends when the user closes the window.
         */
        JFrame window = new JFrame("Checkers");
        App content = new App();
        window.setContentPane(content);
        window.pack();
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
//        window.setLocation( (screensize.width - window.getWidth())/2,
//                (screensize.height - window.getHeight())/2 );
        window.setLocation(500, 500);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setResizable(false);
        window.setVisible(true);
    }

    public App() {
        Window window = new Window();
        add(window);
    }

}
