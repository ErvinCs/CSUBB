using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PPD_MPI_02.src
{
    class InvalidTermException : Exception
    {
        public InvalidTermException()
        {}

        public InvalidTermException(string message) : base(message)
        {}

        public InvalidTermException(string message, Exception inner) : base(message, inner)
        {}
    }
}
