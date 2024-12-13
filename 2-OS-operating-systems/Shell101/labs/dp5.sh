#!/bin/bash
#For each command line parameter
#If file: print name, character no, line no
#If directory: print name, file no, subdir no
for file in "$@"; do
    if [ -d "$file" ]; then
        echo `basename $file`
        echo wc -l $file
    fi
    if [ -f "$file" ]; then
        echo `basename $file`
        wc -l $file
        find ./$file -maxdepth 0 -type d
    fi
done
