#!/bin/bash
#Print
#The first 5 and last 5 lines of the files in the current dir
#All of them if the file has less than 10 lines
find . -type f | ( while read f; do
    if file "$f" | grep -q "ASCII text"; then
        if wc -l "$f" -lt 10; then
            echo "All: "
            head -10 "$f"
        else
            echo "First 5:"
            head -5 "$f"
            echo "Last 5:"
            tail -5 "$f"
        fi
    fi
done)
