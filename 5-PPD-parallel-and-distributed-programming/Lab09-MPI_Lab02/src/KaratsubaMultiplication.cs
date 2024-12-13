using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Diagnostics;

namespace PPD_MPI_02.src
{
    class KaratsubaMultiplication : Multiplication
    {
        public KaratsubaMultiplication(Polynomial p1, Polynomial p2) : base(p1, p2)
        { }

        public override Polynomial multiply()
        {
            Stopwatch time = new Stopwatch();
            time.Start();

            Polynomial a0 = p1.getFirstHalf();
            Polynomial a1 = p1.getSecondHalf();
            Polynomial b0 = p2.getFirstHalf();
            Polynomial b1 = p2.getSecondHalf();

            Polynomial A0B0 = new RegularMultiplication(a0, b0).multiply();
            Polynomial A0B1 = new RegularMultiplication(a0, b1).multiply();
            Polynomial A1B0 = new RegularMultiplication(a1, b0).multiply();
            Polynomial A1B1 = new RegularMultiplication(a1, b1).multiply();

            int n = (p1.getSize() + p2.getSize()) / 2;
            if (n % 2 == 0)
                n += 1;
            int mid = n / 2 + 1;

            int[] middleCoef = new int[mid];
            for (int i = 0; i < mid - 1; i++)
                middleCoef[i] = 0;
            middleCoef[mid - 1] = 1;
            Polynomial middle = new Polynomial(mid, middleCoef);

            int[] lastCoef = new int[n];
            for (int i = 0; i < n - 1; i++)
                lastCoef[i] = 0;
            lastCoef[n - 1] = 1;
            Polynomial last = new Polynomial(n, lastCoef);

            this.result = A0B0
                .add(new RegularMultiplication(A0B1, middle).multiply())
                .add(new RegularMultiplication(A1B0, middle).multiply())
                .add(new RegularMultiplication(A1B1, last).multiply());

            time.Stop();
            //Console.WriteLine("Single-threaded Karatsuba multiplication Time: " + time.ToString() + "ms");

            return this.result;
        }
    }
}
