#!/bin/sh
#FOR
echo "For loop1:"
for i in 1 2 3 4 5 
do
    echo "Number=${i}"
done
echo "\n"
#Iterates trough the given numbers^

echo "For loop2:"
for i in hello 1 * 2 3 goodbye; do  #Note the syntax (;)
    echo "Value=${i}"
done
echo "\n"
#Iterate trough the given, numbers, strings and the files in the current dir

echo "For loop3:"
for i in 1 $PWD/*.txt goodbye #Escaping $PWD also prints the scape
do
    echo "Value=${i}"
done
echo "\n"
#The same as above, except it only iterates trough .txt files

#WHILE
string="Hello"
while [ "$string" != "bye" ]    #Quote $string; otherwise it tries to execute it as a function
do
    echo "Type something (\"bye\" or ^C to quit):"
    read string
    echo "Typed: $string"
done
#Read and print a string until "bye" is given^


    
