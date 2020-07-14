package myCheckers;

public class Checker
{
    private BoardBehavior moveBehavior;

    public Checker(BoardBehavior moveBehavior)
    {
        this.moveBehavior = moveBehavior;
    }

    public void move()
    {
        moveBehavior.move();
    }

    public void setMoveBehavior(BoardBehavior moveBehavior)
    {
        this.moveBehavior = moveBehavior;
    }

}
