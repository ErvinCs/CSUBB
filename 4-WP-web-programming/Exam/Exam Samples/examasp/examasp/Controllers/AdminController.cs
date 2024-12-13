using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using examasp.DataAbstractionLayer;
using examasp.Models;

namespace examasp.Controllers
{
    public class AdminController : Controller
    {
        // GET: Admin
        public ActionResult Index()
        {
            return View("AdminView");
        }

        public string GetAllItemDetails()
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
            List<ItemUser> itemList = dal.GetNoItemsPerUser();
            ViewData["itemList"] = itemList;

            string result = "<table><thead><th>Username</th><th>ItemCount</th><th>Total Value</th></thead>";
            foreach (ItemUser iu in itemList)
            {
                result += "<tr>";
                result += "<td>" + iu.Username + "</td><td>" + iu.Item_count + "</td><td>" + iu.Total_value + "</td>";
                result += "</tr>";
            }

            result += "</table>";
            return result;
        }
    }
}