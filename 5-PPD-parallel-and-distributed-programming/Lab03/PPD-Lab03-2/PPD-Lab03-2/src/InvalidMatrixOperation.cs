using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PPD_Lab03_2.src
{
    public class InvalidMatrixOperation : Exception
    {
        public InvalidMatrixOperation() { }
        public InvalidMatrixOperation(string message) : base(message) { }
        public InvalidMatrixOperation(string message, Exception inner) : base(message, inner) { }
    }
}
