using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Lab9.Models;
using Lab9.DataAbstractionLayer;

namespace Lab9.Controllers
{
    public class LoginController : Controller
    {
        public ActionResult Index()
        {
            return View("LoginPage");
        }

        public string GetUserByCredentials()
        {
            string username = Request.Params["username"];
            string password = Request.Params["password"];
            DAL dal = new DAL();
            User user = dal.GetUserByCredentials(username, password);

            if (user.Id == -1)
            {
                string result = "0";
                return result;
            }
            else
            {
                string result = "1";
                Response.Cookies.Add(new HttpCookie("Username", username));
                Response.Cookies.Add(new HttpCookie("Cart", ""));
                Response.Redirect("../Main");  
                return result;
            }
        }

        public string LogUserOut()
        {
            string username = Request.Params["username"];
            Response.Cookies["Username"].Expires = DateTime.Now.AddDays(-1);
            Response.Cookies["Cart"].Expires = DateTime.Now.AddDays(-1);
            Response.Redirect("LoginPage.cshtml");
            return "Logged Out Succesfully!";
        }
    }
}