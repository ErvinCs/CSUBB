using System;
using System.Threading;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PPD_Lab03_2.src
{
    abstract class BaseThread
    {
        private Thread thread;

        protected BaseThread()
        {
            this.thread = new Thread(new ThreadStart(this.RunThread));
        }

        public void Start() => thread.Start();
        public void Join() => thread.Join();
        public bool isAlive() => thread.IsAlive;

        public abstract void RunThread();
    }
}
