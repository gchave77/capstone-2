package checkers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * This panel displays a checkerboard pattern with a 2-pixel black border.
 * This class does the work of letting the users play checkers and displays the checkerboard.
 */
public class Board extends JPanel implements ActionListener, MouseListener
{
    public JButton newGameButton;  // Button to start a new game.
    public JButton resignButton;   // Button to end a game.
    public JLabel message;  // Label to display messages to the players.

    GameHistory board;  // The data for the checkers board is kept here.
    //    This board is also responsible for generating lists of legal moves.

    boolean gameInProgress; // Check for game in progress

    /* The next three variables are valid only when the game is in progress. */
    int currentPlayer;
    // Whose turn is it now?  Possible values are GameHistory.RED and GameHistory.BLACK.

    int selectedRow, selectedCol;
    // If the current player has selected a piece to move, these give the row and column
    // containing that piece.  If no piece is yet selected, then selectedRow is -1.

    Move[] legalMoves;
    // An array containing the legal moves for the current player.

    /**
     * Constructor.  Create the buttons and label.  Listens for mouse clicks and for clicks on the buttons.
     * Create the board and start the first game.
     */
    Board() {
        setBackground(Color.BLACK);
        addMouseListener(this);
        resignButton = new JButton("Resign");
        resignButton.addActionListener(this);
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this);
        message = new JLabel("",JLabel.CENTER);
        message.setFont(new  Font("Serif", Font.BOLD, 18));
        message.setForeground(Color.white);
        board = new GameHistory();
        newGame();
    }

//     * Respond to user's click on one of the two buttons.

    public void actionPerformed(ActionEvent event) {
        Object src = event.getSource();
        if (src == newGameButton)
            newGame();
        else if (src == resignButton)
            resignGame();
    }

//     * Start a new game
    void newGame() {
        try {
            if (gameInProgress);
        } catch (Exception e) {
            message.setText("Finish the current game first!");
//          Have some type of error handling for bad user input (incorrect key press, etc.)
        }

        board.setUpGame();   // Set up the pieces.
        currentPlayer = GameHistory.RED;   // RED moves first.
        legalMoves = board.getLegalMoves(GameHistory.RED);  // Get RED's legal moves.
        selectedRow = -1;   // RED has not yet selected a piece to move.
        message.setText("Red:  Make your move.");
        gameInProgress = true;
        newGameButton.setEnabled(false);
        resignButton.setEnabled(true);
        repaint();
    }

//     * Current player resigns.  Game ends.  Opponent wins.
    void resignGame() {
        if (!gameInProgress) {
            message.setText("There is no game in progress!");
            return;
        }
        if (currentPlayer == GameHistory.RED)
            gameOver("RED resigns.  BLACK wins.");
        else
            gameOver("BLACK resigns.  RED wins.");
    }


    /**
     * The game ends.  The parameter, str, is displayed as a message
     * to the user.  The states of the buttons are adjusted so playes
     * can start a new game.  This method is called when the game
     * ends at any point in this class.
     */
    void gameOver(String str) {
        message.setText(str);
        newGameButton.setEnabled(true);
        resignButton.setEnabled(false);
        gameInProgress = false;
    }


    /**
     * This is called by mousePressed() when a player clicks on the
     * square in the specified row and col.  It has already been checked
     * that a game is, in fact, in progress.
     */
    void clickSquare(int row, int col) {

         /* If the player clicked on one of the pieces that the player
          can move, mark this row and col as selected and return.  (This
          might change a previous selection.)  Reset the message, in
          case it was previously displaying an error message. */

        for (int i = 0; i < legalMoves.length; i++)
            if (legalMoves[i].fromRow == row && legalMoves[i].fromCol == col) {
                selectedRow = row;
                selectedCol = col;
                if (currentPlayer == GameHistory.RED)
                    message.setText("RED:  Make your move.");
                else
                    message.setText("BLACK:  Make your move.");
                repaint();
                return;
            }

         /* If no piece has been selected to be moved, the user must first
          select a piece.  Show an error message and return. */

        if (selectedRow < 0) {
            message.setText("Click the piece you want to move.");
            return;
        }

         /* If the user clicked on a squre where the selected piece can be
          legally moved, then make the move and return. */

        for (int i = 0; i < legalMoves.length; i++)
            if (legalMoves[i].fromRow == selectedRow && legalMoves[i].fromCol == selectedCol
                    && legalMoves[i].toRow == row && legalMoves[i].toCol == col) {
                makeMove(legalMoves[i]);
                return;
            }

         /* If we get to this point, there is a piece selected, and the square where
          the user just clicked is not one where that piece can be legally moved.
          Show an error message. */

        message.setText("Click a legal square to move, or choose a new checker");

    }  // end clickSquare()


    /**
     * This is called when the current player has chosen the specified
     * move.  Make the move, and then either end or continue the game
     * appropriately.
     */
    void makeMove(Move move) {

        board.makeMove(move);

         /* If the move was a jump, it's possible that the player has another
          jump.  Check for legal jumps starting from the square that the player
          just moved to.  If there are any, the player must jump.  The same
          player continues moving.
          */

        if (move.isJump()) {
            legalMoves = board.getLegalJumpsFrom(currentPlayer,move.toRow,move.toCol);
            if (legalMoves != null) {
                if (currentPlayer == GameHistory.RED)
                    message.setText("RED:  You must continue jumping.");
                else
                    message.setText("BLACK:  You must continue jumping.");
                selectedRow = move.toRow;  // Since only one piece can be moved, select it.
                selectedCol = move.toCol;
                repaint();
                return;
            }
        }

         /* The current player's turn is ended, so change to the other player.
          Get that player's legal moves.  If the player has no legal moves,
          then the game ends. */

        if (currentPlayer == GameHistory.RED) {
            currentPlayer = GameHistory.BLACK;
            legalMoves = board.getLegalMoves(currentPlayer);
            if (legalMoves == null)
                gameOver("BLACK has no moves.  RED wins.");
            else if (legalMoves[0].isJump())
                message.setText("BLACK:  Make your move.  You must jump.");
            else
                message.setText("BLACK:  Make your move.");
        }
        else {
            currentPlayer = GameHistory.RED;
            legalMoves = board.getLegalMoves(currentPlayer);
            if (legalMoves == null)
                gameOver("RED has no moves.  BLACK wins.");
            else if (legalMoves[0].isJump())
                message.setText("RED:  Make your move.  You must jump.");
            else
                message.setText("RED:  Make your move.");
        }

         /* Set selectedRow = -1 to record that the player has not yet selected
          a piece to move. */

        selectedRow = -1;

         /* As a courtesy to the user, if all legal moves use the same piece, then
          select that piece automatically so the use won't have to click on it
          to select it. */

        if (legalMoves != null) {
            boolean sameStartSquare = true;
            for (int i = 1; i < legalMoves.length; i++)
                if (legalMoves[i].fromRow != legalMoves[0].fromRow
                        || legalMoves[i].fromCol != legalMoves[0].fromCol) {
                    sameStartSquare = false;
                    break;
                }
            if (sameStartSquare) {
                selectedRow = legalMoves[0].fromRow;
                selectedCol = legalMoves[0].fromCol;
            }
        }

        /* Make sure the board is redrawn in its new state. */

        repaint();

    }  // end makeMove();


    /**
     * Draw  checkerboard pattern in gray and lightGray.  Draw the checkers.
     * If a game is in progress, highlight the legal moves.
     */
    public void paintComponent(Graphics graphics) {
        /* Draw a two-pixel black border around the edges of the canvas. (Graphics.java) */
        graphics.setColor(Color.black);
        graphics.drawRect(0,0,getSize().width-1,getSize().height-1);
        graphics.drawRect(1,1,getSize().width-3,getSize().height-3);

        /* Draw the squares of the checkerboard and the checkers. */
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ( row % 2 == col % 2 )
                    graphics.setColor(Color.LIGHT_GRAY);
                else
                    graphics.setColor(Color.GRAY);
//              Draws the outline of the specified rectangle (Graphics.java)
                graphics.fillRect(4 + col*40, 4 + row*40, 40, 40);
                switch (board.pieceAt(row,col)) {
                    case GameHistory.RED:
                        graphics.setColor(Color.RED);
                        graphics.fillOval(6 + col*40, 6 + row*40, 36, 36);
                        break;
                    case GameHistory.BLACK:
                        graphics.setColor(Color.BLACK);
                        graphics.fillOval(6 + col*40, 6 + row*40, 36, 36);
                        break;
                    case GameHistory.RED_KING:
                        graphics.setColor(Color.RED);
                        graphics.fillOval(6 + col*40, 6 + row*40, 36, 36);
                        graphics.setColor(Color.WHITE);
                        graphics.drawString("K", 18 + col*40, 26 + row*40);
                        break;
                    case GameHistory.BLACK_KING:
                        graphics.setColor(Color.BLACK);
                        graphics.fillOval(6 + col*40, 6 + row*40, 36, 36);
                        graphics.setColor(Color.WHITE);
                        graphics.drawString("K", 18 + col*40, 26 + row*40);
                        break;
                }
            }
        }

         /* If a game is in progress, highlight the legal moves.
         Note: legalMoves is never null while a game is in progress. */
        if (gameInProgress) {
            /* First, draw a 2-pixel cyan border around the pieces that can be moved. */
            graphics.setColor(Color.cyan);
            for (int i = 0; i < legalMoves.length; i++) {
                graphics.drawRect(4 + legalMoves[i].fromCol*40, 4 + legalMoves[i].fromRow*40, 38, 38);
                graphics.drawRect(5 + legalMoves[i].fromCol*40, 5 + legalMoves[i].fromRow*40, 38, 38);
            }
               /* If a piece is selected for moving (i.e. if selectedRow >= 0), then draw a 2-pixel white border
               around that piece and draw green borders around each square that the piece can move to. */
            if (selectedRow >= 0) {
                graphics.setColor(Color.white);
                graphics.drawRect(4 + selectedCol*40, 4 + selectedRow*40, 38, 38);
                graphics.drawRect(5 + selectedCol*40, 5 + selectedRow*40, 38, 38);
                graphics.setColor(Color.green);
                for (int i = 0; i < legalMoves.length; i++) {
                    if (legalMoves[i].fromCol == selectedCol && legalMoves[i].fromRow == selectedRow) {
                        graphics.drawRect(4 + legalMoves[i].toCol*40, 4 + legalMoves[i].toRow*40, 38, 38);
                        graphics.drawRect(5 + legalMoves[i].toCol*40, 5 + legalMoves[i].toRow*40, 38, 38);
                    }
                }
            }
        }
    }  // end paintCompone

    /**
     * Respond to a user click on the board.  If no game is in progress, show an error message.
     * Otherwise, find the row and column that the user clicked and call clickSquare() to handle it.
     */
    public void mousePressed(MouseEvent event) {
        if (!gameInProgress)
            message.setText("Click \"New Game\" to start a new game.");
        else {
            int col = (event.getX() - 2) / 40;
            int row = (event.getY() - 2) / 40;
            if (col >= 0 && col < 8 && row >= 0 && row < 8)
                clickSquare(row,col);
        }
    }

    public void mouseReleased(MouseEvent event) { }
    public void mouseClicked(MouseEvent event) { }
    public void mouseEntered(MouseEvent event) { }
    public void mouseExited(MouseEvent event) { }

}

//@FunctionalInterface
//interface LambdaEvent
//{
//    List<String> calc(List<String> list, boolean mutate);
//}
