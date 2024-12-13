using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Project.Controller;

namespace Project
{
    class Program
    {
        static void Main(string[] args)
        {
            while (true)
            {
                Console.WriteLine("1: continue.");
                Console.WriteLine("0: exit.");
                Console.Write("Select one option: ");
                string op = Console.ReadLine();

                if (op.Equals("0"))
                    break;
                if (op.Equals("1"))
                {
                    Console.Write("Give the number of vertices:");
                    int nrV = Convert.ToInt32(Console.ReadLine());

                    Cont cont = new Cont(nrV);
                    cont.InitMatrix(nrV);
                    cont.Generate(nrV);
                    Stopwatch startwatch = new Stopwatch();
                    startwatch.Start();
                    cont.Start();
                    foreach (var thread in cont.GetThreads())
                        thread.Join();
                    startwatch.Stop();

                    Console.WriteLine(cont.Show());
                    Console.WriteLine("Time taken: {0} sec", startwatch.Elapsed.ToString());
                }
            }
        }
    }
}
