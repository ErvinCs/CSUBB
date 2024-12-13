using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PPD_MPI_02.src
{
    [Serializable]
    class PolynomialBundle
    {
        private Polynomial p1;
        private Polynomial p2;
        private Polynomial result;

        public PolynomialBundle(Polynomial p1, Polynomial p2)
        {
            this.p1 = p1;
            this.p2 = p2;
            this.result = new Polynomial(p1.getSize() + p2.getSize(), new int[p1.getSize() + p2.getSize()]);
        }

        public Polynomial getP1() { return p1; }
        public Polynomial getP2() { return p2; }
        public Polynomial getResult() { return result; }
        public void setP1(Polynomial p1) { this.p1 = p1; }
        public void setP2(Polynomial p2) { this.p2 = p2; }
        public void setResult(Polynomial result) { this.result = result; }

        public override String ToString()
        {
            return "P1=" + p1.ToString() + "\nP2=" + p2.ToString() + "\nResult=" + result.ToString();
        }
    }

    [Serializable]
    class PolynomialScalarBundle
    {
        private Polynomial p1;
        private int scalarCoef;
        private int scalarRank;

        public PolynomialScalarBundle(Polynomial p1, int scalarCoef, int scalarRank)
        {
            this.p1 = p1;
            this.scalarCoef = scalarCoef;
            this.scalarRank = scalarRank;
        }

        public Polynomial getP1() { return p1; }
        public int getScalarCoef() { return scalarCoef; }
        public int getScalarRank() { return scalarRank; }
        public void setP1(Polynomial p1) { this.p1 = p1; }
        public void setScalarCoef(int scalarCoef) { this.scalarCoef = scalarCoef; }
        public void setScalarRank(int scalarRank) { this.scalarRank = scalarRank; }
    }
}
