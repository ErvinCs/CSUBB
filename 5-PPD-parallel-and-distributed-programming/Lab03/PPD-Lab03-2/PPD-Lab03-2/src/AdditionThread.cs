using System;
using System.Threading;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PPD_Lab03_2.src
{
    class AdditionThread 
    {
        private Thread thread;
        private int number1, number2;
        int result;

        public AdditionThread(int number1, int number2)
        {
            this.number1 = number1;
            this.number2 = number2;
            this.thread = new Thread(new ThreadStart(this.RunThread));
        }

        public void RunThread()
        {
            this.result = number1 + number2;
        }

        public int getNumber1()
        {
            return number1;
        }

        public int getNumber2()
        {
            return number2;
        }

        public int getResult()
        {
            return result;
        }
    }
}
