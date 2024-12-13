#!/bin/bash
#Print the file names in the files of a dir that contain numbers with more than 5 digits
find $1 -type f | ( while read f; do
    if file "$f"; then
        grep '\<[0-9]\{5,\}\>' $f
    fi
done)
