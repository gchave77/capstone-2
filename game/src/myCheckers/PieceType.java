package myCheckers;

public enum PieceType
{
//    1 = down, -1 = up, 0 = up or down
    RED(1), WHITE(-1), RED_KING(0), WHITE_KING(0);

    public int moveDir;

    PieceType(int moveDir)
    {
        this.moveDir = moveDir;
    }
}
