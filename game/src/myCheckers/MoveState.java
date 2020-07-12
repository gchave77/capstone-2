package myCheckers;

public class MoveState
{
    private MoveType type;

    public MoveType getType() {
        return type;
    }

    private Piece piece;

    public Piece getPiece() {
        return piece;
    }

    public MoveState(MoveType type) {
        this(type, null);
    }

    public MoveState(MoveType type, Piece piece) {
        this.type = type;
        this.piece = piece;
    }

}
