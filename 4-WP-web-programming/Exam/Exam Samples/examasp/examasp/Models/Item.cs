using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace examasp.Models
{
    public class Item
    {
        public int Item_id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public int Value { get; set; }
        public int User_id { get; set; }
    }
}