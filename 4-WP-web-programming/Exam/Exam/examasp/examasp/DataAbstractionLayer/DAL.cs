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

        public List<File> GetItemsByUserId(int userId, int page)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            List<File> itemList = new List<File>();
            int pageBegin = (page - 1) * 5;
            int pageEnd = page + 4;

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = ConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from files where user_id=" + userId + " limit " + pageBegin + "," + pageEnd; ;
                MySqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    File file = new File();
                    file.File_id = reader.GetInt32("file_id");
                    file.User_id = reader.GetInt32("user_id");
                    file.Size = reader.GetInt32("size");
                    file.Filename = reader.GetString("filename");
                    file.Filepath = reader.GetString("filepath");

                    itemList.Add(file);
                }
                reader.Close();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return itemList;
        }

    }
}