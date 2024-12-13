#!/bin/bash

#Find and print all the file types in the directories given as parameteres
#find $@ -type f -name "*.*" | awk -F. '{print $NF}' | sort -u

#Find all the files in a given directory and display:
#1.A report showing how many files of each type there are
echo "File types:"
find $1 -type f | sed 's/.*\.//' | sort | uniq -c 
echo "Space:"
#2.What is the total space occupied by each type
find $1 -iname '*.*' -print0 | du -ch --files0-from=- #Not really what i was looking for
#3.What is the largest file size of each type



