using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using examasp.DataAbstractionLayer;
using examasp.Models;

namespace examasp.Controllers
{
    public class DetailsController : Controller
    {
        // GET: Details
        public ActionResult Index()
        {
            return View("DetailsView");
        }

        public string RemoveItem()
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

            int itemId = Int32.Parse(Request.Params["itemId"]);

            DAL dal = new DAL();
            dal.DeleteItem(itemId);

            return "Item #" + itemId + " Deleted!";
        }

        public string UpdateItem()
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

            int itemId = Int32.Parse(Request.Params["itemId"]);
            string name = Request.Params["name"];
            string description = Request.Params["description"];
            int value = Int32.Parse(Request.Params["value"]);

            Item item = new Item();
            item.Item_id = itemId;
            item.Name = name;
            item.Description = description;
            item.Value = value;
            item.User_id = userId;

            DAL dal = new DAL();
            dal.UpdateItem(item);

            return "Item #" + itemId + " Updated!";
        }
    }
}