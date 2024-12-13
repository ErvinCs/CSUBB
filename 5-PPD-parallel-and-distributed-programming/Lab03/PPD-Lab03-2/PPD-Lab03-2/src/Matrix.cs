using System;
using System.Diagnostics;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Collections;


namespace PPD_Lab03_2.src
{
    class Matrix
    {
        private int rows;
        private int columns;
        private int[,] data;

        public Matrix(int rows, int columns)
        {
            this.rows = rows;
            this.columns = columns;
            this.data = new int[rows, columns];
        }

        public Matrix(int[,] array)
        {
            this.rows = array.GetLength(0);
            this.columns = array.GetLength(1);
            this.data = new int[rows, columns];
            for (int i = 0; i < rows; i++)
                for (int j = 0; j < columns; j++)
                    this.data[i,j] = data[i,j];
        }

        public void populate()
        {
            Random rand = new Random();
            for (int i = 0; i < this.rows; i++)
                for (int j = 0; j < this.columns; j++)
                    this.data[i, j] = (int)rand.Next(1, 9);
        }

        public Matrix add(Matrix other)
        {
            if (this.columns != other.columns || this.rows != other.rows)
                throw new InvalidMatrixOperation("Cannot perform addition: invalid number of Rows & Columns!");
            Matrix result = new Matrix(this.rows, this.columns);

            Stopwatch stopwatch = new Stopwatch();
            stopwatch.Start();
            List<Task<int>> tasks = new List<Task<int>>();

            for (int i = 0; i < result.Rows; i++)
            {
                int indexi = i;
                for (int j = 0; j < result.Columns; j++)
                {
                    int indexj = j;
                    Task<int> t = Task<int>.Factory.StartNew(() =>
                    {
                        return this.Data[indexi, indexj] + other.Data[indexi, indexj];
                    });
                    tasks.Add(t);
                }
            }

            for (int i = 0; i < result.Rows; i++)
            {
                for (int j = 0; j < result.Columns; j++)
                {
                    tasks[(i * result.Columns) + j].Wait();
                    result.data[i, j] = tasks[(i * result.Columns) + j].Result;
                }
            }

            stopwatch.Stop();
            TimeSpan span = stopwatch.Elapsed;
            Console.WriteLine("Time: " + span + "ms");
            return result;
        }

        public Matrix multiply(Matrix other)
        {
            if (this.columns != other.rows)
                throw new InvalidMatrixOperation("Cannot perform multiplication: invalid number of Rows & Columns!");
            Matrix result = new Matrix(this.rows, other.columns);

            Stopwatch stopwatch = new Stopwatch();
            stopwatch.Start();
            List<Task<int>> tasks = new List<Task<int>>();

            for (int i = 0; i < result.rows; i++)
            {
                int indexi = i;
                for (int j = 0; j < result.columns; j++)
                {
                    int indexj = j;
                    Task<int> t = Task<int>.Factory.StartNew(() => 
                    {
                        int res = 0;
                        for (int k = 0; k < this.columns; k++)
                        {
                            res += this.Data[indexi, k] * other.Data[k, indexj];
                        }
                        return res;
                    });
                    tasks.Add(t);
                }
            }

            for (int i = 0; i < result.rows; i++)
            {
                for (int j = 0; j < result.columns; j++)
                {
                    tasks[i * this.Columns + j].Wait();
                    result.data[i, j] = tasks[i * this.Columns + j].Result;
                }
            }

            stopwatch.Stop();
            TimeSpan span = stopwatch.Elapsed;
            Console.WriteLine("Time: " + span + "ms");
            return result;
        }

        public Matrix multiplyNoThreads(Matrix other)
        {
            if (this.columns != other.rows)
                throw new InvalidMatrixOperation("Cannot perform multiplication: invalid number of Rows & Columns!");
            Matrix result = new Matrix(this.rows, other.columns);

            Stopwatch stopwatch = new Stopwatch();
            stopwatch.Start();

            for (int i = 0; i < this.rows; i++)
            {
                for (int j = 0; j < other.columns; j++)
                {
                    for (int k = 0; k < this.columns; k++)
                    {
                        result.data[i, j] += (this.data[i, k] * other.data[k, j]);
                    }
                }
            }

            stopwatch.Stop();
            TimeSpan span = stopwatch.Elapsed;
            Console.WriteLine("Time: " + span + "ms");
            return result;
        }

        public Matrix addNoThreads(Matrix other)
        {

            if (this.columns != other.columns || this.rows != other.rows)
                throw new InvalidMatrixOperation("Cannot perform addition: invalid number of Rows & Columns!");
            Matrix result = new Matrix(this.rows, this.columns);

            Stopwatch stopwatch = new Stopwatch();
            stopwatch.Start();

            for (int i = 0; i < this.rows; i++)
            {
                for (int j = 0; j < other.columns; j++)
                {
                    result.data[i, j] = this.data[i, j] + other.data[i, j];
                }
            }

            stopwatch.Stop();
            TimeSpan span = stopwatch.Elapsed;
            Console.WriteLine("Time: " + span + "ms");
            return result;
        }

        public int Rows{
            get { return this.rows; }
        }

        public int Columns{
            get { return this.columns; }
        }

        public int[,] Data{
            get { return this.data; }
        }

        public override string ToString()
        {
            string output = "[" + this.rows + "x" + this.columns + "]\n";
            for (int i = 0; i < this.rows; i++)
            {
                output += "( ";
                for (int j = 0; j < this.columns; j++)
                {
                    output += this.data[i, j] + " ";
                }
                output += ")\n";
            }
            return output;
        }
    }
}
