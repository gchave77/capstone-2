package myCheckers;

public enum PieceType
{
//    1 = down, -1 = up
    RED(1), WHITE(-1);

    public int moveDir;

    PieceType(int moveDir)
    {
        this.moveDir = moveDir;
    }
}
