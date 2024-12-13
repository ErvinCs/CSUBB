namespace DBMS_Lab01
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.labelDoctors = new System.Windows.Forms.Label();
            this.labelRequests = new System.Windows.Forms.Label();
            this.viewDoctors = new System.Windows.Forms.DataGridView();
            this.viewRequests = new System.Windows.Forms.DataGridView();
            this.controlPanel = new System.Windows.Forms.Panel();
            this.addButton = new System.Windows.Forms.Button();
            this.updateButton = new System.Windows.Forms.Button();
            this.deleteButton = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.viewDoctors)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.viewRequests)).BeginInit();
            this.SuspendLayout();
            // 
            // labelDoctors
            // 
            this.labelDoctors.AutoSize = true;
            this.labelDoctors.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelDoctors.Location = new System.Drawing.Point(12, 9);
            this.labelDoctors.Name = "labelDoctors";
            this.labelDoctors.Size = new System.Drawing.Size(100, 25);
            this.labelDoctors.TabIndex = 2;
            this.labelDoctors.Text = "Doctors:";
            // 
            // labelRequests
            // 
            this.labelRequests.AutoSize = true;
            this.labelRequests.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelRequests.Location = new System.Drawing.Point(484, 9);
            this.labelRequests.Name = "labelRequests";
            this.labelRequests.Size = new System.Drawing.Size(211, 25);
            this.labelRequests.TabIndex = 4;
            this.labelRequests.Text = "Requests/Patients:";
            // 
            // viewDoctors
            // 
            this.viewDoctors.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.viewDoctors.Location = new System.Drawing.Point(17, 37);
            this.viewDoctors.Name = "viewDoctors";
            this.viewDoctors.Size = new System.Drawing.Size(423, 270);
            this.viewDoctors.TabIndex = 23;
            // 
            // viewRequests
            // 
            this.viewRequests.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.viewRequests.Location = new System.Drawing.Point(489, 37);
            this.viewRequests.Name = "viewRequests";
            this.viewRequests.Size = new System.Drawing.Size(423, 270);
            this.viewRequests.TabIndex = 24;
            // 
            // controlPanel
            // 
            this.controlPanel.Location = new System.Drawing.Point(17, 437);
            this.controlPanel.Name = "controlPanel";
            this.controlPanel.Size = new System.Drawing.Size(895, 120);
            this.controlPanel.TabIndex = 28;
            // 
            // addButton
            // 
            this.addButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.addButton.Location = new System.Drawing.Point(17, 351);
            this.addButton.Name = "addButton";
            this.addButton.Size = new System.Drawing.Size(172, 37);
            this.addButton.TabIndex = 29;
            this.addButton.Text = "Add";
            this.addButton.UseVisualStyleBackColor = true;
            this.addButton.Click += new System.EventHandler(this.addButton_Click);
            // 
            // updateButton
            // 
            this.updateButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.updateButton.Location = new System.Drawing.Point(379, 351);
            this.updateButton.Name = "updateButton";
            this.updateButton.Size = new System.Drawing.Size(172, 37);
            this.updateButton.TabIndex = 30;
            this.updateButton.Text = "Update";
            this.updateButton.UseVisualStyleBackColor = true;
            this.updateButton.Click += new System.EventHandler(this.updateButton_Click);
            // 
            // deleteButton
            // 
            this.deleteButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.deleteButton.Location = new System.Drawing.Point(740, 351);
            this.deleteButton.Name = "deleteButton";
            this.deleteButton.Size = new System.Drawing.Size(172, 37);
            this.deleteButton.TabIndex = 31;
            this.deleteButton.Text = "Delete";
            this.deleteButton.UseVisualStyleBackColor = true;
            this.deleteButton.Click += new System.EventHandler(this.deleteButton_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(924, 587);
            this.Controls.Add(this.deleteButton);
            this.Controls.Add(this.updateButton);
            this.Controls.Add(this.addButton);
            this.Controls.Add(this.controlPanel);
            this.Controls.Add(this.viewRequests);
            this.Controls.Add(this.viewDoctors);
            this.Controls.Add(this.labelRequests);
            this.Controls.Add(this.labelDoctors);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.viewDoctors)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.viewRequests)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.Label labelDoctors;
        private System.Windows.Forms.Label labelRequests;
        private System.Windows.Forms.DataGridView viewDoctors;
        private System.Windows.Forms.DataGridView viewRequests;
        private System.Windows.Forms.Panel controlPanel;
        private System.Windows.Forms.Button addButton;
        private System.Windows.Forms.Button updateButton;
        private System.Windows.Forms.Button deleteButton;
    }
}

