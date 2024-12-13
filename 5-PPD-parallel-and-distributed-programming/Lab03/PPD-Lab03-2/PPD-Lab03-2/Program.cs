using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace PPD_Lab03_2.src
{
    class Program
    {
        static void Main(string[] args)
        {
            int lines1 = 5;
            int columns1 = 5;
 
            Matrix A = new Matrix(lines1, columns1);
            A.populate();
            Console.WriteLine("A:" + A.ToString());

            Matrix B = new Matrix(lines1, columns1);
            B.populate();
            Console.WriteLine("B:" + B.ToString());

            Matrix AplusB = A.add(B);
            Console.WriteLine("Multi-threaded:\nA + B: " + AplusB.ToString());

            Matrix AplusBnoTh = A.addNoThreads(B);
            Console.WriteLine("Single-threaded:\nA + B: " + AplusBnoTh.ToString());

            Matrix AstarB = A.multiply(B);
            Console.WriteLine("Multi-threaded:\nA * B: " + AstarB);

            Matrix AstarBnoTh = A.multiplyNoThreads(B);
            Console.WriteLine("Single-threaded:\nA * B: " + AstarBnoTh.ToString());

            Console.Read();
        }
    }
}
