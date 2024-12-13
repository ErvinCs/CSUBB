#!/bin/bash
#Print all the filenames with names less then 4 characters
#If they are .txt, print the first 10 lines of each
find $1 -type f -name '????' | ( while read f; do
    if file "$f"; then
        echo "filename=${f}"
        #if [[ $d == "*.txt" ]]; then
            head -10 "$f"
        #fi
    fi
done)
