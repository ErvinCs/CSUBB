using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace examasp.Models
{
    public class ItemUser
    {
        public int User_id { get; set; }
        public string Username { get; set; }
        public int Item_count { get; set; }
        public int Total_value { get; set; }
    }
}