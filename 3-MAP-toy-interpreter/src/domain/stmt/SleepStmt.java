package domain.stmt;
import domain.PrgState;
import exception.MyException;
import domain.adt.*;


public class SleepStmt implements IStmt
{
    private int number;

    public SleepStmt(int number)
    {
        this.number = number;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException
    {
        MyIStack<IStmt> stack = state.getExeStack();
        if (number > 0)
        {
            stack.push(new SleepStmt(number-1));
        }
        return null;
    }

    @Override
    public String toString() {
        return "SLEEP(" + Integer.toString(number) + ")";
    }
}