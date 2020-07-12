package myCheckers;

public enum PieceType
{
//    1 = down, -1 = up
    RED(1), WHITE(-1), RED_KING(2), WHITE_KING(-2);

    public int moveDir;

    PieceType(int moveDir)
    {
        this.moveDir = moveDir;
    }
}
