#!/bin/sh
message="Hello World"
#Assignment^
echo $message
message = "Hello World"
#Treates message as a command and tries to execute it
#Shell is weakly typed

x=10
echo $x
#Prints 10^
echo x=$(($x+1))
#Prints x=11^
x=$(($x+1))
echo $x
#Prints 11^
x=x+1
echo $x
#Prints x+1
    #x=$(($x+1))
    #echo $x
    #Ileegal NUmber: can't add 1 to 'x+1'
#Arithmetic examples^

x="Hi"
expr $x + 1
#AN infers vairable types => This^ will raise an error
