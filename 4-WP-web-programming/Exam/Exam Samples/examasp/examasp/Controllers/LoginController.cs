using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using examasp.Models;
using examasp.DataAbstractionLayer;

namespace examasp.Controllers
{
    public class LoginController : Controller
    {
        // GET: User
        public ActionResult Index()
        {
            return View("LoginView");
        }

        public string GetUserByCredentials()
        {
            ClearCookies();
            string username = Request.Params["username"];
            string password = Request.Params["password"];
            DAL dal = new DAL();
            User user = dal.GetUserByCredentials(username, password);

            string result;
            if (user.User_id == -1)
            {
                result = "0";
            }
            else
            {
                result = "1";
                Response.Cookies.Add(new HttpCookie("Username", username));
                Response.Cookies.Add(new HttpCookie("UserId", user.User_id.ToString()));
                Response.Cookies.Add(new HttpCookie("Cart", ""));
                Response.Redirect("../Main");
            }
            return result;
        }

        public string RegisterUser()
        {
            string username = Request.Params["username"];
            string password = Request.Params["password"];
            DAL dal = new DAL();
            User user = dal.GetUserByUsername(username);

            string result;
            if (user.User_id == -1)
            {
                user.Username = username;
                user.Password = password;
                dal.AddUser(user);
                result = "1";
                Response.Cookies.Add(new HttpCookie("Username", username));
                Response.Cookies.Add(new HttpCookie("UserId", user.User_id.ToString()));
                Response.Cookies.Add(new HttpCookie("Cart", ""));
                Response.Redirect("../Main");
            }
            else
            {
                result = "0";
            }
            return result;
        }

        public void Logout()
        {
            ClearCookies();
            Response.Redirect("../Login");
        }

        private void ClearCookies()
        {
            if (Request.Cookies["Username"] != null)
            {
                Response.Cookies["Username"].Expires = DateTime.Now.AddDays(-1);
            }
            if (Request.Cookies["Cart"] != null)
            {
                Response.Cookies["Cart"].Expires = DateTime.Now.AddDays(-1);
            }
            if (Request.Cookies["UserId"] != null)
            {
                Response.Cookies["UserId"].Expires = DateTime.Now.AddDays(-1);
            }
        }
    }
}