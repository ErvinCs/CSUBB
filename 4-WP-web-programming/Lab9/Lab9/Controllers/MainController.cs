using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Lab9.Models;
using Lab9.DataAbstractionLayer;
using System.Web.UI.WebControls;

namespace Lab9.Controllers
{
    public class MainController : Controller
    {
        public ActionResult Index()
        {
            return View("FilterProducts");
        }

        public void Logout()
        {
            if (Request.Cookies["Username"] != null)
            {
                Response.Cookies["Username"].Expires = DateTime.Now.AddDays(-1);
            }
            if (Request.Cookies["Username"] != null)
            {
                Response.Cookies["Cart"].Expires = DateTime.Now.AddDays(-1);
            }
            Response.Redirect("../Login"); 
        }

        public string GetProductsByCategory()
        {
            string category = Request.Params["category"];
            int limit = int.Parse(Request.Params["page"]);
            DAL dal = new DAL();
            List<Product> productList = dal.GetProductsByCategory(category, limit);
            ViewData["productList"] = productList;

            string result = "<table><thead><th>Category</th><th>Name</th><th>Price</th><th>Purchase</th></thead>";
            foreach (Product p in productList)
            {
                result += "<tr>";
                result += "<td>" + p.Category + "</td><td>" + p.Name + "</td><td>" + p.Price + "</td>";
                result += "<td><button type='button' id='buy' name=" + p.Id + "> Add </button></td>";
                result += "</tr>";
            }

            result += "</table>";
            return result;
        }

        public string AddProductToCart()
        {
            int id = int.Parse(Request.Params["name"]);
            DAL dal = new DAL();
            Product toAdd = dal.GetProductById(id);

            List<string> cartList = new List<string>();
            string cartListString = "";

            HttpCookie cookie = Request.Cookies["Cart"];
            if (cookie == null)
            {
                cookie = new HttpCookie("Cart");
            } else
            {
                cartList = cookie.Value.Split(' ').ToList();
            }
             
            List<Product> cart = new List<Product>();
            cart.Add(toAdd);


            foreach (string productId in cartList)
            {
                if (productId.Length > 0)
                {
                    Product cartProduct = dal.GetProductById(int.Parse(productId));
                    cart.Add(cartProduct);
                    cartListString += cartProduct.Id.ToString() + " ";
                }
            }
            cartListString += id.ToString();

            cookie = new HttpCookie("Cart", cartListString);
            Response.Cookies.Add(cookie);

            ViewData["cartList"] = cartList;

            string result = "<table><thead><th>Category</th><th>Name</th><th>Price</th><th>Action</th></thead>";
            foreach (Product p in cart)
            {
                result += "<tr>";
                result += "<td>" + p.Category + "</td><td>" + p.Name + "</td><td>" + p.Price + "</td>";
                result += "<td><button type='button' id='delete' name=" + p.Id + "> Remove </button></td>";
                result += "</tr>";
            }

            result += "</table>";
            return result;
        }

        public string RemoveProductFromCart()
        {
            int id = int.Parse(Request.Params["name"]);
            DAL dal = new DAL();
            Product toRemove = dal.GetProductById(id);

            List<string> cartList = new List<string>();
            string cartListString = "";

            HttpCookie cookie = Request.Cookies["Cart"];
            if (cookie == null)
            {
                cookie = new HttpCookie("Cart");
            }
            else
            {
                cartList = cookie.Value.Split(' ').ToList();
            }

            cartList.Remove(id.ToString());
            List<Product> cart = new List<Product>();

            foreach (string productId in cartList)
            {
                if (productId.Length > 0)
                {
                    Product cartProduct = dal.GetProductById(int.Parse(productId));
                    cart.Add(cartProduct);
                    cartListString += cartProduct.Id.ToString() + " ";
                }
            }
            cartListString.Trim();

            cookie = new HttpCookie("Cart", cartListString);
            Response.Cookies.Add(cookie);

            ViewData["cartList"] = cartList;

            string result = "<table><thead><th>Category</th><th>Name</th><th>Price</th><th>Action</th></thead>";
            foreach (Product p in cart)
            {
                result += "<tr>";
                result += "<td>" + p.Category + "</td><td>" + p.Name + "</td><td>" + p.Price + "</td>";
                result += "<td><button type='button' id='delete' name=" + p.Id + "> Remove </button></td>";
                result += "</tr>";
            }

            result += "</table>";
            return result;
        }
    }
}