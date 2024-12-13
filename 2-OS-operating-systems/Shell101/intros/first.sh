#!/bin/sh
#Hashtag marks a comment - except for the first line
#The first line specifies that this will be executed by bin/sh - Bourne Shell
#For Perl #!/usr/bin/perl would be used
echo Hello world
#Parameteres 'Hello' & 'world' - spearated by space^
#Run chmod 755 <fileName>.sh - make the text file executable
#./<filename>.sh - run the file
echo Hello          world
#Called echo with 2 arguments^; whitespaces are ignored
echo "Hello         world"
#Called echo with 1 argument ^
echo "Hello * world"
echo Hello * world
echo "Hello" "      " world
echo 'Hello' `world`
