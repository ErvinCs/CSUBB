using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;


namespace PPD_Lab05.src
{
    class Program
    {

        static void startDelegate(object task)
        {
            int i = Convert.ToInt32(task);
            AsyncClient.StartClient(AsyncClient.hostNames[i], i);
        }

        static void Main(string[] args)
        {
            List<Thread> threads = new List<Thread>();
            ParameterizedThreadStart start = new ParameterizedThreadStart(startDelegate);
            List<Task> tasks = new List<Task>();

            for (int i = 0; i < 2; i++)
            {
                //tasks.Add(Task.Factory.StartNew(doStart, i));
                int index = i;
                ThreadStart threadStart = () => startDelegate(index);
                Thread thread = new Thread(threadStart);
                thread.Start();
            }
            Task.WaitAll(tasks.ToArray());
            Console.ReadLine();
        }
    }
}
