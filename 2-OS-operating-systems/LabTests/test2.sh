#!/bin/bash
#Common
#Sa se scrie un script shell care
#R1 - 5
#Primeste oricate directoare ca parametrii si afiseaza statistici pentru fiecare in formatul:
#Director <nume_director>
#Executabile: nr
#Ne-executabile: nr
#Directoare: nr
#Se vor executa toate validarile necesare 

for dir in $@; do
if [ -d $dir ]; then
    echo "Director=${dir}"
    eNo=0
    neNo=0
    dNo=0
    for file in $dir/*; do
        if [ -x "$file" ]; then
            eNo=$(( eNo + 1 ))
        else
            if [ -f "$file" ]; then
                neNo=$(( neNo + 1 ))
            fi
        fi
        if [ -d "$file" ]; then
            dNo=$(( dNo + 1 ))
        fi
    done
    echo "Executabile=${eNo}"
    echo "Ne-executabile=${neNo}"
    echo "Directoare=${dNo}"
    echo
else 
    echo "Not a directory=${dir}"
    echo
fi
done
