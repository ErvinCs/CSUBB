using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using examasp.Models;
using MySql.Data.MySqlClient;

namespace examasp.DataAbstractionLayer
{
    public class DAL
    {
        const string ConnectionString = "server=localhost;uid=root;pwd=;database=exam;";

        // ---------- Users ----------

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

                if (reader.Read())
                {
                    user.User_id = reader.GetInt32("user_id");
                    user.Username = reader.GetString("username");
                    user.Password = reader.GetString("password");
                }
                else
                {
                    user.User_id = -1;
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

        public User GetUserByUsername(string username)
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
                cmd.CommandText = "select * from users where username='" + username + "'";
                MySqlDataReader reader = cmd.ExecuteReader();

                if (reader.Read())
                {
                    user.User_id = reader.GetInt32("user_id");
                    user.Username = reader.GetString("username");
                    user.Password = reader.GetString("password");
                }
                else
                {
                    user.User_id = -1;
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
        
        public List<User> GetAllUsers()
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            List<User> userList = new List<User>();

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from users";
                MySqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    User user = new User();
                    user.User_id = reader.GetInt32("user_id");
                    user.Username = reader.GetString("username");
                    user.Password = reader.GetString("password");
                    userList.Add(user);
                }
                reader.Close();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return userList;
        }

        public User DeleteUser(int userId)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            User user = new User();

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmdFind = new MySqlCommand();
                cmdFind.Connection = conn;
                cmdFind.CommandText = "select * from users where user_id=" + userId;
                MySqlDataReader readerFind = cmdFind.ExecuteReader();

                if (readerFind.Read())
                {
                    user.User_id = readerFind.GetInt32("user_id");
                    user.Username = readerFind.GetString("username");
                    user.Password = readerFind.GetString("password");
                }
                readerFind.Close();

                MySqlCommand cmdDel = new MySqlCommand();
                cmdDel.Connection = conn;
                cmdDel.CommandText = "delete from users where user_id=" + userId;
                cmdDel.ExecuteNonQuery();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return user;
        }

        public void AddUser(User user)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "insert into users (user_id, username, password) values (" + user.User_id + ",'" + user.Username + "','"
                    + user.Password + "')";
                cmd.ExecuteNonQuery();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
        }

        public void UpdateUser(User user)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "update users set username='" + user.Username + "',password='" + user.Password + " where user_id=" + user.User_id;
                cmd.ExecuteNonQuery();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
        }

        // ---------- Items ----------

        public Item GetItemById(int id)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            Item item = new Item();

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from items where item_id=" + id;
                MySqlDataReader reader = cmd.ExecuteReader();

                if (reader.Read())
                {
                    item.Item_id = reader.GetInt32("item_id");
                    item.Name = reader.GetString("name");
                    item.Description = reader.GetString("description");
                    item.Value = reader.GetInt32("value");
                    item.User_id = reader.GetInt32("user_id");
                }
                reader.Close();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return item;
        }

        public List<Item> GetAllItems()
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            List<Item> itemList = new List<Item>();

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from items";
                MySqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Item item = new Item();
                    item.Item_id = reader.GetInt32("item_id");
                    item.Name = reader.GetString("name");
                    item.Description = reader.GetString("description");
                    item.Value = reader.GetInt32("value");
                    item.User_id = reader.GetInt32("user_id");
                    itemList.Add(item);
                }
                reader.Close();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return itemList;
        }

        public List<Item> GetItemsByUserId(int userId)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            List<Item> itemList = new List<Item>();

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from items where user_id=" + userId;
                MySqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Item item = new Item();
                    item.Item_id = reader.GetInt32("item_id");
                    item.Name = reader.GetString("name");
                    item.Description = reader.GetString("description");
                    item.Value = reader.GetInt32("value");
                    item.User_id = reader.GetInt32("user_id");
                    itemList.Add(item);
                }
                reader.Close();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return itemList;
        }

        public List<Item> GetItemsPaged(int page)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            List<Item> itemList = new List<Item>();
            int pageBegin = (page - 1) * 4;
            int pageEnd = page + 3;

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from items limit " + pageBegin + "," + pageEnd;
                MySqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Item item = new Item();
                    item.Item_id = reader.GetInt32("item_id");
                    item.Name = reader.GetString("name");
                    item.Description = reader.GetString("description");
                    item.Value = reader.GetInt32("value");
                    item.User_id = reader.GetInt32("user_id");
                    itemList.Add(item);
                }
                reader.Close();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return itemList;
        }

        public List<ItemUser> GetNoItemsPerUser()
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            List<ItemUser> itemList = new List<ItemUser>();

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select users.user_id, users.username, (select count(items.item_id) from items where items.user_id = users.user_id) as item_count, (select sum(items.value) from items where items.user_id = users.user_id) as total_value from users";
                MySqlDataReader reader = cmd.ExecuteReader();

                while(reader.Read())
                {
                    ItemUser itemUser = new ItemUser();
                    itemUser.User_id = reader.GetInt32("user_id");
                    itemUser.Username = reader.GetString("username");
                    itemUser.Item_count = reader.GetInt32("item_count");
                    itemUser.Total_value = reader.GetInt32("total_value");
                    itemList.Add(itemUser);

                }
                reader.Close();

            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return itemList;
        }

        public Item DeleteItem(int id)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            Item item = new Item();

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmdFind = new MySqlCommand();
                cmdFind.Connection = conn;
                cmdFind.CommandText = "select * from items where item_id=" + id;
                MySqlDataReader readerFind = cmdFind.ExecuteReader();

                if (readerFind.Read())
                {
                    item.Item_id = readerFind.GetInt32("item_id");
                    item.Name = readerFind.GetString("name");
                    item.Description = readerFind.GetString("description");
                    item.Value = readerFind.GetInt32("value");
                    item.User_id = readerFind.GetInt32("user_id");
                }
                readerFind.Close();

                MySqlCommand cmdDel = new MySqlCommand();
                cmdDel.Connection = conn;
                cmdDel.CommandText = "delete from items where item_id=" + id;
                cmdDel.ExecuteNonQuery();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return item;
        }

        public void DeleteItems(List<int> itemIds)
        {
            foreach(int id in itemIds)
            {
                DeleteItem(id);
            }
        }

        public void AddItem(Item item)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "insert into items (name, description, value, user_id) values ('" + item.Name + "','" + item.Description + "',"
                    + item.Value + "," + item.User_id + ")";
                cmd.ExecuteNonQuery();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
        }

        public void AddItems(List<Item> items)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                string cmdString = "insert into items (name, description, value, user_id) values";
                int count = items.Count;
                for(int i = 0; i < count - 1; i++)
                {
                    cmdString += "('" + items[i].Name + "', '" + items[i].Description + "', " + items[i].Value + "," + items[i].User_id + "),";
                }
                cmdString += "('" + items[count - 1].Name + "', '" + items[count - 1].Description + "', " + 
                              items[count - 1].Value + "," + items[count - 1].User_id + ");";
                cmd.CommandText = cmdString;
                cmd.ExecuteNonQuery();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
        }

        public void UpdateItem(Item item)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "update items set name='" + item.Name + "',description='" + item.Description + "',value="
                    + item.Value + " where user_id=" + item.User_id + " and item_id=" + item.Item_id;
                cmd.ExecuteNonQuery();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
        }

        public void UpdateItems(List<Item> items)
        {
            foreach(Item item in items)
            {
                UpdateItem(item);
            }
        }
    }
}