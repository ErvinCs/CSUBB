using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Lab9.Models;
using MySql.Data.MySqlClient;

namespace Lab9.DataAbstractionLayer
{
    public class DAL
    {
        const string ConnectionString = "server=localhost;uid=root;pwd=;database=lab9;";

        public User GetUserByCredentials(string username, string password)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            User user = new User();

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from users where username='" + username + "' and password='" + password + "'";
                MySqlDataReader reader = cmd.ExecuteReader();

                if(reader.Read())
                {
                    user.Id = reader.GetInt32("id");
                    user.Username = reader.GetString("username");
                    user.Password = reader.GetString("password");
                }
                else
                {
                    user.Id = -1;
                    user.Username = "";
                    user.Password = "";
                }
                reader.Close();
                
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return user;
        }

        public Product GetProductById(int id)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            Product product = new Product();

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmdFind = new MySqlCommand();
                cmdFind.Connection = conn;
                cmdFind.CommandText = "select * from products where id=" + id;
                MySqlDataReader readerFind = cmdFind.ExecuteReader();

                if (readerFind.Read())
                {
                    product.Id = readerFind.GetInt32("id");
                    product.Name = readerFind.GetString("name");
                    product.Price = readerFind.GetInt32("price");
                    product.Category = readerFind.GetString("category");
                }
                readerFind.Close();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return product;
        }

        public List<Product> GetProductsByCategory(string category, int page)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            List<Product> productList = new List<Product>();

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                int pageBegin = (page - 1) * 4;
                int pageEnd = page + 3;
                cmd.CommandText = "select * from products where category='" + category + "' limit " + pageBegin + "," + pageEnd;
                MySqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Product prod = new Product();
                    prod.Id = reader.GetInt32("id");
                    prod.Name = reader.GetString("name");
                    prod.Price = reader.GetInt32("price");
                    prod.Category = reader.GetString("category");
                    productList.Add(prod);
                }
                reader.Close();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return productList;
        }

        public Product RemoveProductById(int id)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            Product product = new Product();

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmdFind = new MySqlCommand();
                cmdFind.Connection = conn;
                cmdFind.CommandText = "select * from products where id=" + id;
                MySqlDataReader readerFind = cmdFind.ExecuteReader();

                if (readerFind.Read())
                {
                    product.Id = readerFind.GetInt32("id");
                    product.Name = readerFind.GetString("name");
                    product.Price = readerFind.GetInt32("price");
                    product.Category = readerFind.GetString("category");
                }
                readerFind.Close();

                MySqlCommand cmdDel = new MySqlCommand();
                cmdDel.Connection = conn;
                cmdDel.CommandText = "delete from products where id=" + id;
                MySqlDataReader readerDel = cmdDel.ExecuteReader();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return product;
        }
    }
}