package main;

public class AdditionThread extends Thread
{
    private Thread thread;
    private int number1, number2;
    int result;

    public AdditionThread(int number1, int number2)
    {
        this.thread = new Thread(this::run);
        this.number1 = number1;
        this.number2 = number2;
    }

    @Override
    public void run()
    {
        this.result = number1 + number2;
    }


    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }

    public int getResult() {
        return result;
    }
}

