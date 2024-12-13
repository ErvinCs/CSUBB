#!/bin/sh
zip -r p1.zip > archived/p1All.zip  #Create one archive with all the files in p1
for i in p1; do
    tar czf ${i}.tar ${i}
done
