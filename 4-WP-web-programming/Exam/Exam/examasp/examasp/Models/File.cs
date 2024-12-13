using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace examasp.Models
{
    public class File
    {
        public int File_id { get; set; }
        public string Filename { get; set; }
        public string Filepath { get; set; }
        public int Size { get; set; }
        public int User_id { get; set; }
    }
}