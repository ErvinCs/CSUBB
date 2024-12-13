using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using examasp.DataAbstractionLayer;
using examasp.Models;

namespace examasp.Controllers
{
    public class MainController : Controller
    {
        // GET: Main
        public ActionResult Index()
        {
            return View("MainView");
        }

        public string GetItemsByUser()
        {
            int userId = -1;
            string username = "";
            HttpCookie cookieId = Request.Cookies["UserId"];
            HttpCookie cookieUsername = Request.Cookies["Username"];
            if (cookieId == null || cookieUsername == null)
            {
                Response.Redirect("../Shared/Error.cshtml");
            }
            else
            {
                userId = Int32.Parse(cookieId.Value);
                username = cookieUsername.Value;
            }
            int page = int.Parse(Request.Params["page"]);

            DAL dal = new DAL();
            List<File> itemList = dal.GetItemsByUserId(userId, page);
            ViewData["itemList"] = itemList;

            string result = "User: " + username + "<br/>";

            result += "<table><thead><th>Filename</th><th>Filepath</th><th>Size</th></thead>";
            foreach (File i in itemList)
            {
                result += "<tr>";
                result += "<td id='name'>" + i.Filename + "</td><td id='path'>" + i.Filepath + "</td><td id='size'>" + i.Size + "</td>";
                result += "</tr>";
            }

            result += "</table>";
            return result;
        }
    }
}