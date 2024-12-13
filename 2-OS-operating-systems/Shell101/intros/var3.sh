#!/bin/sh
echo "VAR=$VAR"
VAR="Ce face fetele?"
echo "VAR=$VAR"

#A variable can be created and assigned in the Terminal
#Calling VAR=Buna, will assign, but it will do it outside the scope of this script
#'export VAR' - allows VAR to be used by another program
#VAR is passed to the script by value, so the changes made to it remain local
#WHen the script exits, its environment is destroyed, so only the global VAR remains

#Normally a script creates another local shell to run it, then it is destroyed
#"Sourcing" a script allows it to be run in the current environment: '. ./script.sh'
