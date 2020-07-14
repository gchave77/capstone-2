package myCheckers;

public enum PieceType
{
//    1 = down, -1 = up - aKing (true or false)
    RED(1, false), WHITE(-1, false);

    public int moveDir;
    public boolean isKing;

    PieceType(int moveDir, boolean isKing)
    {
        this.moveDir = moveDir;
        this.isKing = isKing;
    }
}
