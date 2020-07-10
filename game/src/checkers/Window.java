package checkers;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame
{
    public Window() {
        this.setVisible(true);
        this.setTitle("Checkers");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new Panel());
    }
    private JButton newGameButton;  // Button for starting a new game.
}
