package myCheckers;

public class MoveState
{
    // keep track of the type of move
    // and any pieces to be captured
    private MoveType type;

    public MoveType getType() {
        return type;
    }

    private PieceCoordinates piece;

    public PieceCoordinates getPiece() {
        return piece;
    }

    // for normal moves that don't capture a piece
    public MoveState(MoveType type) {
        this(type, null);
    }

    public MoveState(MoveType type, PieceCoordinates piece) {
        this.type = type;
        this.piece = piece;
    }

}
