using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Diagnostics;

namespace PPD_MPI_02.src
{
    class RegularMultiplication : Multiplication
    {
        public RegularMultiplication(Polynomial p1, Polynomial p2) : base(p1, p2)
        { }

        public override Polynomial multiply()
        {
            Stopwatch time = new Stopwatch();
            time.Start();

            for (int i = 0; i < p1.getSize(); i++)
            {
                for (int j = 0; j < p2.getSize(); j++)
                {
                    result.setCoeficient(i + j, result.getCoeficients()[i + j] + p1.getCoeficients()[i] * p2.getCoeficients()[j]);
                }
            }

            time.Stop();
            //Console.WriteLine("Single-threaded Regular multiplication Time: " + time.ToString() + "ms");

            return this.result;
        }

    }   
}
