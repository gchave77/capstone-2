package checkers;

import javax.swing.*;
import java.awt.*;

public class Window extends JPanel
{
    /**
     * The constructor creates the Board (which in turn creates and manages
     * the buttons and message label), adds all the components, and sets
     * the bounds of the components.  A null layout is used.  (This is
     * the only thing that is done in the main Checkers class.)
     */
    public Window() {

        setLayout(null);  // set layout below
        setPreferredSize( new Dimension(350,250) );

        setBackground(new Color(3,100,250));  // blue background

        /* Create the components and add them to the applet. */

        Board board = new Board();  // The constructor for the
        //   board also creates the buttons and label
        add(board);
        add(board.newGameButton);
        add(board.resignButton);
        add(board.message);

      /* Set the position and size of each component by calling
       its setBounds() method. */

        board.setBounds(20,20,164,164); // Note:  size MUST be 164-by-164 !
        board.newGameButton.setBounds(210, 60, 120, 30);
        board.resignButton.setBounds(210, 120, 120, 30);
        board.message.setBounds(0, 200, 350, 30);

    } // end constructor

}
