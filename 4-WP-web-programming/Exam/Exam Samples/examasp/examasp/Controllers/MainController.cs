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


            DAL dal = new DAL();
            List<Item> itemList = dal.GetItemsByUserId(userId);
            ViewData["itemList"] = itemList;

            string result = "User: " + username + "<br/>";

            result += "<table><thead><th>Name</th><th>Description</th><th>Value</th><th>Edit</th></thead>";
            foreach (Item i in itemList)
            {
                result += "<tr>";
                result += "<td>" + i.Name + "</td><td>" + i.Description + "</td><td>" + i.Value + "</td>";
                result += "<td><a href='http://localhost:59421/Details?itemId=" + i.Item_id + "&name=" + i.Name + "&description=" + i.Description + "&value=" + i.Value + "'>Edit</a></td>";
                result += "</tr>";
            }

            result += "</table>";
            return result;
        }

        public void AddItem()
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

            string name = Request.Params["name"];
            string desc = Request.Params["description"];
            int val = Int32.Parse(Request.Params["value"]);
            Item item = new Item();
            item.Name = name;
            item.Description = desc;
            item.Value = val;
            item.User_id = userId;

            DAL dal = new DAL();
            dal.AddItem(item);
        }
    }
}