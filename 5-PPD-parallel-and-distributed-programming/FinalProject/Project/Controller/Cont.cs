using Project.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Project.Controller
{
    class Cont
    {
        private List<Node> nodes;
        private int[,] graph;
        private Mutex mutex = new Mutex();
        private int noColors;
        private Thread[] threads;

        public Cont(int no)
        {
            this.nodes = new List<Node>();
            this.threads = new Thread[no];
        }

        public Thread[] GetThreads() { return threads; }

        public void InitMatrix(int nrV)
        {
            graph = new int[nrV, nrV]; 
            Random random = new Random();

            for (int i = 0; i < nrV; i++)
                for (int j = i; j < nrV; j++)
                    if (i.Equals(j))
                        graph[i, j] = 0;
                    else
                    {
                        graph[i, j] = random.Next(2);
                        graph[j, i] = graph[i, j];
                    }
        }

        public void Generate(int nrV)
        {
            int max = 0;
            Random random = new Random();
            for (int i = 0; i < nrV; i++)
                this.nodes.Add(new Node(i));

            foreach (var elem in this.nodes)
            {
                for (int i = 0; i < nrV; i++)
                    if (graph[elem.Id, i].Equals(1))
                        elem.Nodes.Add(nodes[i]);
                if (elem.Nodes.Count > max)
                    max = elem.Nodes.Count;
            }
            this.noColors = random.Next(max + 1, nrV + 1);
        }

        public void Start()
        {
            int i = 0;
            this.nodes.ForEach(elem =>{
                ThreadStart threadStart = () => Compute(elem);
                threads[i] = new Thread(threadStart);
                threads[i].Start();
                i++; });
        }

        private void Compute(Node node)
        {
            List<int> colors = new List<int>();
            Random random = new Random();

            foreach (var item in node.Nodes)
                if (item.Color != 0)
                    colors.Add(item.Color);

            mutex.WaitOne();
            if (colors.Count.Equals(0))
                node.Color = random.Next(1, (noColors + 1));
            else
            {
                int no;
                do
                {
                    no = random.Next(1, (noColors + 1));
                } while (colors.Contains(no));
                node.Color = no;
            }
            mutex.ReleaseMutex();
        }

        public string Show()
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.Append("No colors:").Append(this.noColors).Append("\n");
            nodes.ForEach(elem => stringBuilder.Append("Id:").Append(elem.Id).Append(" Color:")
                .Append(elem.Color).Append(" Neighbours:").Append(elem.NodesToString()).Append("\n"));
            return stringBuilder.ToString();
        }
    }
}
