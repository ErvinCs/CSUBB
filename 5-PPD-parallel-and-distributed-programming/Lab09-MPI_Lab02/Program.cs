using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Diagnostics;
using MPI;

//C:\Users\csoka\IdeaProjects\PPD-MPI-02\PPD-MPI-02\bin\Debug
//mpiexec -n 5 PPD-MPI-02.exe
//Project - Manage NuGet packages - MPI (v1.3)
namespace PPD_MPI_02.src
{
    class Program
    {
        public static int[] generateCoefs(int n)
        {
            int[] coefs = new int[n];
            Random random = new Random();
            for (int i = 0; i < n; i++)
            {
                coefs[i] = (int)random.Next(1, 6);
            }
            return coefs;
        }

        //TODO - Doc + stopwatch
        static void Main(string[] args)
        {
            //Single-threaded Regular & Karatsuba
            /*int size = 5;
            int[] coef1 = { 1, 2, 3, 4, 5 };    //generateCoefs(size);
            Polynomial p1 = new Polynomial(size, coef1);
            int[] coef2 = { 5, -4, 3, -2, 1 };  //generateCoefs(size);
            Polynomial p2 = new Polynomial(size, coef2);

            Console.WriteLine("Polynomials: ");
            Console.WriteLine("P1=" + p1.ToString());
            Console.WriteLine("P2=" + p2.ToString());

            var watch = Stopwatch.StartNew();

            Console.WriteLine("\nRegular Multiplication: ");
            RegularMultiplication mRegular = new RegularMultiplication(p1, p2);
            Console.WriteLine("Single-threaded = " + mRegular.multiply().ToString());

            Console.WriteLine("\nKaratsuba Multiplication: ");
            KaratsubaMultiplication mKaratsuba = new KaratsubaMultiplication(p1, p2);
            Console.WriteLine("Single-threaded = " + mKaratsuba.multiply().ToString());

            watch.Stop();
            var elapsed = watch.ElapsedMilliseconds;

            Console.Read(elapsed);*/

            //Multi-threaded Regular
            /*using (new MPI.Environment(ref args))
            {
                Intracommunicator comm = Communicator.world;

                int size = 5;
                int[] coef1 = generateCoefs(size);  //{ 1, 2, 3, 4, 5 };   
                Polynomial p1 = new Polynomial(size, coef1);
                int[] coef2 = generateCoefs(size);  //{ 5, -4, 3, -2, 1 }
                Polynomial p2 = new Polynomial(size, coef2);
                PolynomialBundle bundle = new PolynomialBundle(p1, p2);

                if (comm.Rank == 0)
                {
                    var watch = Stopwatch.StartNew();

                    Console.WriteLine("Polynomials: ");
                    Console.WriteLine("P1=" + p1.ToString());
                    Console.WriteLine("P2=" + p2.ToString());
                    Console.WriteLine();

                    comm.Send(bundle, 1, 0);

                    PolynomialBundle recMsg = comm.Receive<PolynomialBundle>(Communicator.anySource, 0);

                    Console.WriteLine("(if) Rank=" + comm.Rank + "; Recieved: " + recMsg.ToString());
                    
                    watch.Stop();
                    var elapsed = watch.ElapsedMilliseconds;

                    Console.WriteLine(elapsed);
                }
                else
                {
                    int rank = comm.Rank - 1;
                    PolynomialBundle msg = comm.Receive<PolynomialBundle>(rank, 0);

                    Console.WriteLine("(else) Rank=" + comm.Rank + ";\n Recieved: " + msg.ToString());

                    for (int i = 0; i < msg.getP1().getSize(); i++)
                    {
                        msg.getResult().setCoeficient(i + rank, msg.getResult().getCoeficients()[i + rank] + p1.getCoeficients()[i] * p2.getCoeficients()[rank]);
                    }

                    Console.WriteLine("(else) Rank=" + comm.Rank + ";\n Sending: " + msg.ToString());

                    comm.Send(msg, (comm.Rank + 1) % comm.Size, 0);
                }
            }*/

            //Multi-threaded Karatsuba
            using (new MPI.Environment(ref args))
            {
                Intracommunicator comm = Communicator.world;

                int size = 5;
                int[] coef1 = generateCoefs(size);  //{ 1, 2, 3, 4, 5 }; 
                Polynomial p1 = new Polynomial(size, coef1);
                int[] coef2 = generateCoefs(size);  //{ 5, -4, 3, -2, 1 };
                Polynomial p2 = new Polynomial(size, coef2);

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

                Polynomial a0, a1, b0, b1;
                a0 = p1.getFirstHalf();
                a1 = p1.getSecondHalf();
                b0 = p2.getFirstHalf();
                b1 = p2.getSecondHalf();

                Polynomial result = new Polynomial(p1.getSize() + p2.getSize(), new int[p1.getSize() + p2.getSize()]);

                if (comm.Rank == 0)
                {
                    var watch = Stopwatch.StartNew();

                    Console.WriteLine("Polynomials: ");
                    Console.WriteLine("P1=" + p1.ToString());
                    Console.WriteLine("P1.FirstHalf=" + p1.getFirstHalf().ToString());
                    Console.WriteLine("P1.SecondHalf=" + p1.getSecondHalf().ToString());
                    Console.WriteLine("P2=" + p2.ToString());
                    Console.WriteLine("P2.FirstHalf=" + p2.getFirstHalf().ToString());
                    Console.WriteLine("P2.SecondHalf=" + p2.getSecondHalf().ToString());
                    Console.WriteLine();

                    comm.Send(result, 1, 0);

                    Polynomial recMsg = comm.Receive<Polynomial>(Communicator.anySource, 0);

                    Console.WriteLine("(if) Rank=" + comm.Rank + "; Recieved: " + recMsg.ToString());

                    watch.Stop();
                    var elapsed = watch.ElapsedMilliseconds;

                    Console.WriteLine(elapsed);
                }
                else if (comm.Rank == 1)
                {
                    int rank = comm.Rank - 1;
                    Polynomial msg = comm.Receive<Polynomial>(rank, 0);

                    Console.WriteLine("(else) Rank=" + comm.Rank + ";\n Recieved: " + msg.ToString());

                    Polynomial A0B0 = new RegularMultiplication(a0, b0).multiply();
                    msg = msg.add(A0B0);

                    Console.WriteLine("(else) Rank=" + comm.Rank + ";\n Sending: " + msg.ToString());

                    comm.Send(msg, (comm.Rank + 1) % comm.Size, 0);
                }
                else if (comm.Rank == 2)
                {
                    int rank = comm.Rank - 1;
                    Polynomial msg = comm.Receive<Polynomial>(rank, 0);

                    Console.WriteLine("(else) Rank=" + comm.Rank + ";\n Recieved: " + msg.ToString());

                    Polynomial A0B1 = new RegularMultiplication(a0, b1).multiply();
                    msg = msg.add(new RegularMultiplication(A0B1, middle).multiply());

                    Console.WriteLine("(else) Rank=" + comm.Rank + ";\n Sending: " + msg.ToString());

                    comm.Send(msg, (comm.Rank + 1) % comm.Size, 0);
                }
                else if (comm.Rank == 3)
                {
                    int rank = comm.Rank - 1;
                    Polynomial msg = comm.Receive<Polynomial>(rank, 0);

                    Console.WriteLine("(else) Rank=" + comm.Rank + ";\n Recieved: " + msg.ToString());

                    Polynomial A1B0 = new RegularMultiplication(a1, b0).multiply();
                    msg = msg.add(new RegularMultiplication(A1B0, middle).multiply());

                    Console.WriteLine("(else) Rank=" + comm.Rank + ";\n Sending: " + msg.ToString());

                    comm.Send(msg, (comm.Rank + 1) % comm.Size, 0);
                }
                else if (comm.Rank == 4)
                {
                    int rank = comm.Rank - 1;
                    Polynomial msg = comm.Receive<Polynomial>(rank, 0);

                    Console.WriteLine("(else) Rank=" + comm.Rank + ";\n Recieved: " + msg.ToString());

                    Polynomial A1B1 = new RegularMultiplication(a0, b1).multiply();
                    msg = msg.add(new RegularMultiplication(A1B1, last).multiply());

                    Console.WriteLine("(else) Rank=" + comm.Rank + ";\n Sending: " + msg.ToString());

                    comm.Send(msg, (comm.Rank + 1) % comm.Size, 0);
                }
                
            }
        }
    }
}
