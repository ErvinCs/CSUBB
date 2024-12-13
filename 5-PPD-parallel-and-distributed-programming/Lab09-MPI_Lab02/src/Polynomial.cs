using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PPD_MPI_02.src
{
    [Serializable]
    class Polynomial
    {
        private int size;
        private int[] coeficients;

        public Polynomial(int size, int[] coeficients)
        {
            this.size = size;
            this.coeficients = coeficients;
        }

        public Polynomial(Polynomial other)
        {
            this.size = other.size;
            this.coeficients = other.coeficients;
        }

        public int getSize()
        {
            return size;
        }

        public int[] getCoeficients()
        {
            return coeficients;
        }

        public void setCoeficient(int index, int value)
        {
            //Throws InvlaidTermException if index is invalid
            this.coeficients[index] = value;
        }

        public Polynomial getFirstHalf()
        {
            int coefSize = this.size / 2;
            int[] newCoeficients = new int[coefSize];
            for (int i = 0; i < this.size / 2; i++)
            {
                newCoeficients[i] = coeficients[i];
            }
            Polynomial half = new Polynomial(coefSize, newCoeficients);
            return half;
        }

        public Polynomial getSecondHalf()
        {
            int coefSize = this.size % 2 == 1 ? this.size / 2 + 1 : this.size / 2;
            int[] newCoeficients = new int[coefSize];
            int j = 0;
            for (int i = this.size / 2; i < this.size; i++)
            {
                newCoeficients[j] = coeficients[i];
                j++;
            }
            return new Polynomial(coefSize, newCoeficients);

        }

        public Polynomial add(Polynomial other)
        {
            int resultSize = this.size > other.size ? this.size : other.size;
            int[] resultCoef = new int[resultSize];

            for (int i = 0; i < this.size; i++)
            {
                resultCoef[i] += this.coeficients[i];
            }
            for (int i = 0; i < other.size; i++)
            {
                resultCoef[i] += other.coeficients[i];
            }
            return new Polynomial(resultSize, resultCoef);
        }

        public override String ToString()
        {
            String output = "";
            for (int i = this.size - 1; i > 0; i--)
            {
                if (i != this.size - 1)
                {
                    if (coeficients[i] > 0)
                    {
                        output += "+";
                    }
                }
                if (this.coeficients[i] != 0)
                {
                    output += this.coeficients[i] + "x^" + i + " ";
                }
            }
            if (coeficients[0] > 0)
            {
                output += "+";
                output += this.coeficients[0];
            }
            return output;
        }
    }
}
