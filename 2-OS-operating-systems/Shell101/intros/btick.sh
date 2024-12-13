#!/bin/sh
#'`'(backtick) indicates that the enclosed text is to be executed as a command
myName=`grep "^${USER}:" /etc/passwd | cut -d: -f5`
echo $myName
#Assign to myName my username^


