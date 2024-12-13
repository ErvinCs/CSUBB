#!/bin/sh
echo "Enter name: "
read User_Name
echo "Hello, $User_Name"

echo "I will create you a file called $User_Name_File"
touch $User_Name_file
#Problems:
#There is no variable called User_Name_File
#The shell does not know where the variable ends and the rest starts
echo "I will create you a file called ${User_Name}_File"
touch "${User_Name}_file"
