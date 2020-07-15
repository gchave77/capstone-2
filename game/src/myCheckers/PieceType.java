package myCheckers;

public enum PieceType
{
//    1 = down, -1 = up - aKing (true or false)
    RED(1, false, true, false), WHITE(-1, false, false, true);

    public int moveDir;
    public boolean isKing;
    public boolean isRed;
    public boolean isWhite;

    PieceType(int moveDir, boolean isKing, boolean isRed, boolean isWhite)
    {
        this.moveDir = moveDir;
        this.isKing = isKing;
        this.isRed = isRed;
        this.isWhite = isWhite;
    }
}
