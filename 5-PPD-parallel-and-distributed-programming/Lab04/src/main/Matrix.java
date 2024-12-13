package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Matrix
{
    private final int rows;
    private final int columns;
    private int[][] data;

    private final Lock lock = new ReentrantLock();


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

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int[][] getData() {
        return data;
    }


    public void setData(int row, int col, int val) {
        this.data[row][col] = val;
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
