#!/bin/sh
#Usually called with [ ... ] instead of test keyword => [ (and respecitvely ]) is a program
#So [ and ] must be separated by spaces
#'\' at the end of a line marks that the line continues on the next line
echo "Enter a number: "
read X
if [ "$X" -lt "0" ]; then
      echo "X is less than zero"
elif [ "$X" -gt "0" ]; then
      echo "X is greater than zero"
else [ "$X" = "0" ] && \
      echo "X is the string or number \"0\""
#elif [ "$X" = "hello" ] && \
#      echo "X matches the string \"hello\""
#elif [ "$X" != "hello" ] && \
#      echo "X is not the string \"hello\""
#elif [ -n "$X" ] && \
#      echo "X is of nonzero length"
#elif [ -f "$X" ] && \
#      echo "X is the path of a real file" || \
#      echo "No such file: $X"
#elif [ -x "$X" ] && \
#      echo "X is the path of an executable file"
#elif [ "$X" -nt "/etc/passwd" ] && \
#      echo "X is a file which is newer than /etc/passwd"
#elif [ "$X" -le "0" ] && \
#      echo "X is less than or equal to  zero"
#elif [ "$X" -ge "0" ] && \
#      echo "X is more than or equal to zero"
fi

