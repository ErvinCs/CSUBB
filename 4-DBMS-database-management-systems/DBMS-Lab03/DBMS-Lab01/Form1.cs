using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;
using System.Windows.Forms;
using System.Configuration;
using System.Diagnostics;

namespace DBMS_Lab01
{
    public partial class Form1 : Form
    {
        SqlConnection connection;
        DataSet ds;
        SqlDataAdapter da;

        BindingSource bsChild;
        BindingSource bsParent;

        List<TextBox> textBoxes;
        List<Label> labels;

        string parent = ConfigurationManager.AppSettings["Parent"];
        string child = ConfigurationManager.AppSettings["Child"];

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            ConnectionStringSettings conSet = ConfigurationManager.ConnectionStrings["connectionString"];
            string connectionString = conSet.ConnectionString;
            connection = new SqlConnection(connectionString);

            bsParent = new BindingSource();
            bsChild = new BindingSource();
            da = new SqlDataAdapter();
            ds = new DataSet();

            updateDataSet();
            updateViews();
            initPanel();
        }

        public void updateDataSet()
        {
            connection.Open();

            string selectCmd = ConfigurationManager.AppSettings["ParentSelectAll"];
            da.SelectCommand = new SqlCommand(selectCmd, connection);
            da.Fill(ds, parent);

            selectCmd = ConfigurationManager.AppSettings["ChildSelectAll"];
            da.SelectCommand = new SqlCommand(selectCmd, connection);
            da.Fill(ds, child);

            connection.Close();
        }

        public void updateViews()
        {
            viewDoctors.DataSource = bsParent;
            viewRequests.DataSource = bsChild;
            updateChildView();
            viewDoctors.AutoResizeColumns();    
            viewRequests.AutoGenerateColumns = true;    
        }

        public void updateChildView()
        {
            string idParent = ConfigurationManager.AppSettings["idParent"];

            Debug.Write(ds.Tables[parent].Columns.ToString());
            Debug.Write(ds.Tables[parent].Columns.ToString());

            //Generic FK Relation
            DataRelation relation = new DataRelation("Parent_Child_FK",
                ds.Tables[parent].Columns[idParent],
                ds.Tables[child].Columns[idParent]);

            ds.Relations.Add(relation);

            bsParent.DataSource = ds;
            bsParent.DataMember = parent;

            bsChild.DataSource = bsParent;
            bsChild.DataMember = "Parent_Child_FK";
        }

        public void initPanel()
        {
            labelRequests.Text = ConfigurationManager.AppSettings["ChildTable"];
            this.textBoxes = new List<TextBox>();
            this.labels = new List<Label>();

            foreach (Control item in controlPanel.Controls.OfType<TextBox>())
            {
                controlPanel.Controls.Remove(item);
            }

            foreach (Control item in controlPanel.Controls.OfType<Label>())
            {
                controlPanel.Controls.Remove(item);
            }

            int idCounter = 0;
            int columnCount = ds.Tables[child].Columns.Count;

            for (int i = 0; i < columnCount; i++)
            {
                Label label = new Label();
                label.Text = ds.Tables[child].Columns[i].ColumnName;

                Point textP = new Point(idCounter * 120, 44);
                Point labelP = new Point(idCounter * 120, 30);
                label.Location = labelP;
                label.AutoSize = true;

                TextBox textBox = new TextBox();
                textBox.Location = textP;
                textBoxes.Add(textBox);
                labels.Add(label);
                idCounter++;

                controlPanel.Controls.Add(label);
                controlPanel.Controls.Add(textBox);
            }
        }

        private void updateButton_Click(object sender, EventArgs e)
        {
            if (viewRequests.SelectedCells.Count > 0)
            {
                connection.Open();
                string updateCmd = ConfigurationManager.AppSettings["ChildUpdate"];
                da.UpdateCommand = new SqlCommand(updateCmd, connection);

                int clientId = int.Parse(viewRequests.CurrentRow.Cells[0].Value.ToString());

                int columnCount = ds.Tables[child].Columns.Count;

                for (int i = 0; i < columnCount; i++)
                {
                    string value = "@v" + (i).ToString();
                    da.UpdateCommand.Parameters.Add(value, SqlDbType.VarChar).Value = textBoxes[i].Text;

                }

                da.UpdateCommand.ExecuteNonQuery();

                ds.Tables[child].Clear();
                string selectcmd = ConfigurationManager.AppSettings["ChildSelectAll"];
                da.SelectCommand = new SqlCommand(selectcmd, connection);
                da.Fill(ds, child);

                connection.Close();
            }

            //tbQuantity.Clear();
        }

        private void deleteButton_Click(object sender, EventArgs e)
        {
            if (viewRequests.SelectedCells.Count > 0)
            {
                connection.Open();
                int selectedRowIndex = viewRequests.SelectedCells[0].RowIndex;

                DataGridViewRow selectedRow = viewRequests.Rows[selectedRowIndex];

                string toDeleteId = Convert.ToString(selectedRow.Cells[0].Value);

                string deleteCmd = ConfigurationManager.AppSettings["ChildDelete"];
                da.DeleteCommand = new SqlCommand(deleteCmd, connection);
                da.DeleteCommand.Parameters.Add("v0", SqlDbType.VarChar).Value = toDeleteId;
                da.DeleteCommand.ExecuteNonQuery();

                ds.Tables[child].Clear();
                string selectcmd = ConfigurationManager.AppSettings["ChildSelectAll"];
                da.SelectCommand = new SqlCommand(selectcmd, connection);

                da.Fill(ds, child);


                connection.Close();
            }
            else
                MessageBox.Show("No row selected!");

            //tbQuantity.Clear();
        }

        private void addButton_Click(object sender, EventArgs e)
        {
            connection.Open();
            int selectedRowIndex = viewDoctors.SelectedCells[0].RowIndex;

            string foreignID = viewDoctors.Rows[selectedRowIndex].Cells[0].Value.ToString();

            string insertCmd = ConfigurationManager.AppSettings["ChildInsert"];
            da.InsertCommand = new SqlCommand(insertCmd, connection);

            int columnCount = ds.Tables[child].Columns.Count;

            for (int i = 0; i < columnCount; i++)
            {
                string value = "@v" + (i).ToString();
                da.InsertCommand.Parameters.AddWithValue(value, textBoxes[i].Text);

            }

            da.InsertCommand.ExecuteNonQuery();

            ds.Tables[child].Clear();
            string selectcmd = ConfigurationManager.AppSettings["ChildSelectAll"];
            da.SelectCommand = new SqlCommand(selectcmd, connection);
            da.Fill(ds, child);

            connection.Close();

            //tbQuantity.Clear();
        }

        //[Obsolete("DEPRECATED")]
        //private void updateViewRequests(int id)
        //{
        //    viewRequests.DataSource = null;

        //    SqlCommand cmd = new SqlCommand();
        //    cmd.Connection = connection;
        //    cmd.CommandType = CommandType.Text;
        //    cmd.CommandText = "SELECT * FROM Requests WHERE requests_doctor = @request_id";
        //    cmd.Parameters.AddWithValue("@request_id", id);

        //    DataSet ds = new DataSet();
        //    SqlDataAdapter adapter = new SqlDataAdapter(cmd);
        //    adapter.Fill(ds, "Requests");
        //    viewRequests.DataSource = ds.Tables["Requests"];
        //}

        //[Obsolete("DEPRECATED")]
        //private void updateViewDoctors()
        //{
        //    SqlDataAdapter adapter;
        //    BindingSource bs = new BindingSource();
        //    DataSet ds = new DataSet();

        //    adapter = new SqlDataAdapter("SELECT * FROM Doctors", connection);
        //    adapter.Fill(ds, "Doctors");
        //    bs.DataSource = ds.Tables["Doctors"];
        //    viewDoctors.DataSource = bs;

        //    int id;
        //    int.TryParse(viewDoctors.CurrentRow.Cells[0].Value.ToString(), out id);
        //    updateViewRequests(id);
        //}

        //[Obsolete("DEPRECATED")]
        //private void initComboBoxes()
        //{
        //    var rhs = new string[2];
        //    rhs[0] = "rh+";
        //    rhs[1] = "rh-";

        //    DataSet rhSet = new DataSet();
        //    DataTable rhTable = new DataTable("rhs");
        //    DataColumn rhColumn = new DataColumn("rh", typeof(string));
        //    rhTable.Columns.Add(rhColumn);

        //    for (int i = 0; i < rhs.Length; i++)
        //    {
        //        DataRow row = rhTable.NewRow();
        //        row["rh"] = rhs[i];
        //        rhTable.Rows.Add(row);
        //    }
        //    rhSet.Tables.Add(rhTable);
        //    cbRH.DataSource = rhSet.Tables["rhs"].DefaultView;
        //    cbRH.DisplayMember = "rh";
        //    cbRH.BindingContext = this.BindingContext;

        //    var blood_groups = new string[4];
        //    blood_groups[0] = "O1";
        //    blood_groups[1] = "A2";
        //    blood_groups[2] = "B3";
        //    blood_groups[3] = "AB4";

        //    DataSet groupSet = new DataSet();
        //    DataTable groupTable = new DataTable("blood_groups");
        //    DataColumn groupColumn = new DataColumn("blood_group", typeof(string));           
        //    groupTable.Columns.Add(groupColumn);

        //    for(int i = 0; i < blood_groups.Length; i++)
        //    {
        //        DataRow row = groupTable.NewRow();
        //        row["blood_group"] = blood_groups[i];
        //        groupTable.Rows.Add(row);
        //    }

        //    groupSet.Tables.Add(groupTable);
        //    cbGroup.DataSource = groupSet.Tables["blood_groups"].DefaultView;
        //    cbGroup.DisplayMember = "blood_group";
        //    cbGroup.BindingContext = this.BindingContext;
        //}

        //[Obsolete("DEPRECATED - Might still keep this one tho")]
        //private void viewDoctors_CellClick(object sender, DataGridViewCellEventArgs e)
        //{
        //    int doctorId;
        //    int.TryParse(viewDoctors.CurrentRow.Cells[0].Value.ToString(), out doctorId);

        //    updateViewRequests(doctorId);
        //}

        //[Obsolete("DEPRECATED")]
        //private void btnAddDoctor_Click(object sender, EventArgs e)
        //{
        //    connection.Open();

        //    string insertCmd = ConfigurationManager.AppSettings["ParentInsert"];
        //    da.InsertCommand = new SqlCommand(insertCmd, connection);

        //    int columnCount = ds.Tables[parent].Columns.Count;

        //    for (int i = 1; i < columnCount; i++)
        //    {
        //        string value = "@v" + (i).ToString();
        //        da.InsertCommand.Parameters.AddWithValue(value, textBoxes[i].Text);
        //    }

        //    da.InsertCommand.ExecuteNonQuery();

        //    ds.Tables[parent].Clear();
        //    string selectcmd = ConfigurationManager.AppSettings["ParentSelectAll"];
        //    da.SelectCommand = new SqlCommand(selectcmd, connection);
        //    da.Fill(ds, parent);

        //    connection.Close();
        //}
    }
}
