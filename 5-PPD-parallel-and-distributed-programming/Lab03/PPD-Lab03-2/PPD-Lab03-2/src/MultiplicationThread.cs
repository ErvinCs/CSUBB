using System;
using System.Threading;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PPD_Lab03_2.src
{
    class MultiplicationThread : BaseThread
    {
        private Thread thread;
        private int line, column;
        Matrix matrix1, matrix2;
        int result;

        public MultiplicationThread(Matrix matrix1, Matrix matrix2, int line, int column)
        {
            this.line = line;
            this.column = column;
            this.matrix1 = matrix1;
            this.matrix2 = matrix2;
            this.thread = new Thread(new ThreadStart(this.RunThread));
        }

        
        public override void RunThread()
        {
            this.result = 0;
            for (int i = 0; i < matrix1.Columns; i++)
                this.result += this.matrix1.Data[column, i] * this.matrix2.Data[i, line];
        }

        public int getLineIndex()
        {
            return line;
        }

        public int getColumnIndex()
        {
            return column;
        }

        public int getResult()
        {
            return result;
        }
    }
}
