public class MultiplicationThread extends Thread
{
    private Thread thread;
    private int value1, value2;
    private int multiplicationResult;
    private Polynomial result;
    private int index1;
    private int index2;

    public MultiplicationThread(int value1, int value2, Polynomial result, int index1 ,int index2)
    {
        this.thread = new Thread(this::run);
        this.value1 = value1;
        this.value2 = value2;
        this.result = result;
        this.index1 = index1;
        this.index2 = index2;
    }

    @Override
    public void run()
    {
        this.multiplicationResult = value1 * value2;
        result.setCoeficient(index1 + index2, result.getCoeficients()[index1+index2] + value1 * value2);
    }

    public int getMultiplicationResult() {
        return this.multiplicationResult;
    }

    public Polynomial getResult() {
        return result;
    }
}