#!/bin/bash
#Sa se scrie un script care primeste oricati parmetri in linia de comanda 
#reprezentand numere naturale si inverseaza cifrele fiecarui numar 
#si le afiseaza pe ecran in formatul: "123456 ===> 654321"
rev=0
for i in $@; do
    ori=$i
    while [ $i -gt 0 ]; do
        n=$(( i % 10 ))
        rev=$(( rev * 10 + n ))
        i=$(( i / 10 ))
    done
    echo $ori "===>" $rev
    rev=0
done
