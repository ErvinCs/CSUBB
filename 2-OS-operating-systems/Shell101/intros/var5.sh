#!/bin/sh
echo -en "Enter your name: [ `whoami` ]"
read myName
if [ -z "$myName" ]; then
    myName=`whoami`
fi
echo "Your name: ${myName}"
echo "\n"
#'-en' notifies not to add a line break

#Better version
echo -en "Enter your name: [ `whoami` ]"
read myName
echo "Your name: ${myName:-`whoami`}"
echo "\n"

#Another version
echo -en "Enter your name: [ `whoami` ]"
read myName
echo "Your name: ${myName:=`whoami`}"
