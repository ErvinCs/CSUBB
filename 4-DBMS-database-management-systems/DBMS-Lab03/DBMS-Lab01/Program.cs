using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace DBMS_Lab01
{
    static class Program
    {
        /// <summary>
        /**
         Transform your first lab to dynamically create the master-detail windows form. 
            The form caption, stored procedures / queries used to access and manipulate data 
            will be set in a configuration file.
         You must prepare at least two different scenarios handling data from two different 1:n relationships.
         The form should be generic enough such that switching between scenarios 
            (i.e., updating the application to handle data from another 1:n relationship)
            comes down to updating the configuration file.
         How you define and interpret the configuration file is entirely up to you.
         You must use core ADO.NET (data sets, data adapters, data readers, etc). 
            Solutions using LINQ, Entity Framework, or any other ORM framework are not accepted.
         */
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new Form1());
        }
    }
}
