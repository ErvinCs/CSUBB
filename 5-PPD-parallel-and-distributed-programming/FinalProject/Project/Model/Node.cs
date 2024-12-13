using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Project.Model
{
    class Node
    {
        private int id;
        private int color;
        private List<Node> nodes;

        public Node(int id)
        {
            this.Id = id;
            this.Color = 0;
            this.nodes = new List<Node>();
        }
        
        public int Color { get => color; set => color = value; }
        public int Id { get => id; set => id = value; }
        public List<Node> Nodes { get => nodes; set => nodes = value; }

        public string NodesToString()
        {
            StringBuilder stringBuilder = new StringBuilder();
            this.Nodes.ForEach(elem => stringBuilder.Append(elem.Id).Append(" "));
            return stringBuilder.ToString();
        }
    }
}
