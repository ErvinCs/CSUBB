using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Diagnostics;
using MPI;

namespace Project_MPI
{
    class Program
    {
        private static List<Node> InitListRand(int size)
        {
            List<Node> nodes = new List<Node>();
            for(int i = 0; i < size; i++)
            {
                Node node = new Node(i);
                nodes.Add(node);
            }

            Random random = new Random();
            for (int i = 0; i < size; i++)
            {
                for (int j = i + 1; j < size; j++)
                {
                    int fiftyPercent = random.Next(2);
                    if (fiftyPercent != 0)
                    {
                        nodes[i].AddVecin(nodes[j]);
                        nodes[j].AddVecin(nodes[i]);
                    }
                }
            }

            return nodes;
        }

        private static List<Node> InitList()
        {
            List<Node> nodes = new List<Node>();
            Node n0 = new Node(0);
            Node n1 = new Node(1);
            Node n2 = new Node(2);
            Node n3 = new Node(3);
            Node n4 = new Node(4);
            Node n5 = new Node(5);
            Node n6 = new Node(6);
            n0.AddVecin(n1).AddVecin(n2).AddVecin(n3);
            n1.AddVecin(n0);
            n2.AddVecin(n0);
            n3.AddVecin(n0).AddVecin(n4);
            n4.AddVecin(n3).AddVecin(n5);
            n5.AddVecin(n4).AddVecin(n6);
            n6.AddVecin(n5);

            nodes.Add(n0);
            nodes.Add(n1);
            nodes.Add(n2);
            nodes.Add(n3);
            nodes.Add(n4);
            nodes.Add(n5);
            nodes.Add(n6);

            return nodes;
        }

        private static bool isColorable(List<Node> nodes, int noColors)
        {
            foreach(Node node in nodes)
            {
                if (node.GetCountVecini() >= noColors)
                {
                    return false;
                }
            }
            return true;
        }

        static void Main(string[] args)
        {
            using (new MPI.Environment(ref args))
            {
                Intracommunicator comm = Communicator.world;

                List<Node> nodes = InitListRand(5);
                int noColors = 3;

                if (!isColorable(nodes, noColors))
                {
                    Console.WriteLine("Cannot color graph!");
                    return;
                }

                if (comm.Rank == 0)
                {
                    var watch = Stopwatch.StartNew();

                    String str = "Rank=0; \nNodes: \n";
                    nodes.ForEach(node => str += node.ToString() + "N=" + node.NodesToString() + "\n" );
                    Console.WriteLine(str);

                    comm.Send(nodes, 1, 0);

                    nodes = comm.Receive<List<Node>>(Communicator.anySource, 0);

                    str = "Rank=0; \nNodes: \n";
                    nodes.ForEach(node => str += node.ToString() + "\n");
                    Console.WriteLine(str);

                    watch.Stop();
                    var elapsed = watch.ElapsedMilliseconds;
                    Console.WriteLine("Time=" + elapsed.ToString() + "ms");
                }
                else
                {
                    int rank = comm.Rank - 1;
                    nodes = comm.Receive<List<Node>>(rank, 0);
                    String str = "Nodes: \n";
                    nodes.ForEach(node => str += node.ToString() + "\n");
                    Console.WriteLine("Rank=" + comm.Rank + ";\nRecieved: " + str);

                    //
                    List<int> validColors = new List<int>(Enumerable.Range(1, noColors).ToArray());
                    Random random = new Random();

                    foreach (var item in nodes[rank].Nodes)
                        if (item.Color != 0)
                            validColors.Remove(item.Color);

                    nodes[rank].Color = validColors[0];

                    comm.Send(nodes, (comm.Rank + 1) % comm.Size, 0);
                }
            }
        }
    }
}
