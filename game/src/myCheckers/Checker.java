package myCheckers;

public class Checker
{
    private PieceCoordinates pieceCoordinates;

    public Checker(PieceCoordinates pieceCoordinates)
    {
        this.pieceCoordinates = pieceCoordinates;
    }

    public void move(int x, int y)
    {
        pieceCoordinates.move(x, y);
    }

    public void setPieceCoordinates(PieceCoordinates pieceCoordinates)
    {
        this.pieceCoordinates = pieceCoordinates;
    }

}
