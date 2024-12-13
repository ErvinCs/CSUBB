#!/bin/sh
while read words; do    #It actually reads lines
    case "$words" in
        hello)      echo "English" : $words ;;
        "be mue")   echo "Romeno"  : $words ;;
        bonjour)    echo French    : $words ;;
        "guten tag")echo German    : $words ;;
        *)          
            echo "No se stie domnu": $words 
            echo "Mare prost" ;;
    esac
done < $PWD/tmp/cases.txt
#Reads from cases.txt
