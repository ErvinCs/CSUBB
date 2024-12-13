#!/bin/sh
echo "Current dir: " $PWD
cp /$PWD/tmp/a/*.c   /$PWD/tmp/b
cp $PWD/tmp/a/*.html    $PWD/tmp/b  #Note that it works even without the first escape
                                    #Altough i think it's better practice not to use it
#Copy all .c and .html files from a into b^
cp -a $PWD/tmp/a/* $PWD/tmp/c
#Copy all the files from a into c^
