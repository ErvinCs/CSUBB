package main;

import java.util.Random;

public class Matrix
{
    private final int rows;
    private final int columns;
    private int[][] data;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new int[rows][columns];
    }

    public Matrix(int[][] array) {
        this.rows = array.length;
        this.columns = array[0].length;
        this.data = new int[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                this.data[i][j] = data[i][j];
    }

    public Matrix(Matrix other) {
        this(other.data);
    }

    public void populate() {
        for(int i = 0; i < this.rows; i++)
            for(int j = 0; j < this.columns; j++)
                this.data[i][j] = (int)(Math.random() * 9 + 1);
    }

    public Matrix add(Matrix other) {

        if (this.columns != other.columns || this.rows != other.rows)
            throw new InvalidMatrixOperation("Cannot perform addition: invalid number of Rows & Columns!");
        Matrix result = new Matrix(this.rows, this.columns);

        int poolSize = this.columns * this.rows;
        AdditionThread[] threadPool = new AdditionThread[poolSize];
        int threadCounter = 0;

        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.columns; j++) {
                threadPool[threadCounter] = new AdditionThread(this.data[i][j], other.data[i][j]);
                threadPool[threadCounter].start();
                threadCounter++;
            }
        }

        for (int i = 0; i < threadCounter; i++) {
            try {
                threadPool[i].join();
            } catch (InterruptedException ex) {
                System.out.println("Addition interrupted!");
            }
        }

        threadCounter = 0;
        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.columns; j++) {
                result.data[i][j] = threadPool[threadCounter++].getResult();
            }
        }

        return result;
    }

    public Matrix multiply(Matrix other) {
        if (this.columns != other.rows)
            throw new InvalidMatrixOperation("Cannot perform multiplication: invalid number of Rows & Columns!");
        Matrix result = new Matrix(this.rows, other.columns);

        int poolSize = this.rows * other.columns;
        MultiplicationThread[] threadPool = new MultiplicationThread[poolSize];
        int threadCounter = 0;

        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.columns; j++) {
                threadPool[threadCounter] = new MultiplicationThread(this, other, i, j);
                threadPool[threadCounter].start();
                threadCounter++;
            }
        }

        for (int i = 0; i < threadCounter; i++) {
            try {
                threadPool[i].join();
            } catch (InterruptedException ex) {
                System.out.println("Multiplication interrupted!");
            }
        }

        threadCounter = 0;
        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.columns; j++) {
                result.data[i][j] = threadPool[threadCounter++].getResult();
            }
        }

        return result;
    }

    public Matrix multiplyNoThreads(Matrix other) {
        if (this.columns != other.rows)
            throw new InvalidMatrixOperation("Cannot perform multiplication: invalid number of Rows & Columns!");
        Matrix result = new Matrix(this.rows, other.columns);


        //Computations
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.columns; j++) {
                for (int k = 0; k < this.columns; k++) {
                    result.data[i][j] += (this.data[i][k] * other.data[k][j]);
                }
            }
        }

        return result;
    }

    public Matrix addNoThreads(Matrix other) {

        if (this.columns != other.columns || this.rows != other.rows)
            throw new InvalidMatrixOperation("Cannot perform addition: invalid number of Rows & Columns!");
        Matrix result = new Matrix(this.rows, this.columns);

        //Computations
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.columns; j++) {
                result.data[i][j] = this.getData()[i][j] + other.getData()[i][j];
            }
        }

        return result;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int[][] getData() {
        return data;
    }

    @Override
    public String toString()
    {
        String output = "[" + this.rows + "x" + this.columns + "]\n";
        for (int i = 0; i < this.rows; i++) {
            output += "( ";
            for (int j = 0; j < this.columns; j++) {
                output += this.data[i][j] + " ";
            }
            output += ")\n";
        }
        return output;
    }

}
