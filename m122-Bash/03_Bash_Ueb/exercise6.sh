#!/usr/bin/bash

# Exercise 6 - stdout, stdin, stderr:
echo "a) - - - - - - - - - - - - - -"
echo "Create and fill content in datei.txt"

cat << END > ./files/datei.txt
a
b
c
d
e
END

echo "b) - - - - - - - - - - - - - -"
echo "Create and direct error in to ./errorsLs.log"

ls -z 2> ./files/errorsLs.log

echo "c) - - - - - - - - - - - - - -"
echo "Redirect output to a file using > and >>"

echo "Hello world!" > ./files/datei.txt 
# "Hello world!" is written in ./files/datei.txt
cat ./files/datei.txt > ./files/datei2.txt 
# "Hello world!" is written in ./files/datei.txt and ./files/datei2.txt
cat ./files/datei.txt > ./files/datei2.txt
# Nothing changed
cat ./files/datei2.txt
# The content ("Hello world!") is printed in the console
cat ./files/datei.txt >> ./files/datei2.txt
# Hello world is printed out in console one time and written one time in ./files/datei.txt and two times in ./files/datei2.txt
cat ./files/datei.txt >> ./files/datei2.txt
# Hello world is printed out in console one time and written one time in ./files/datei.txt and three times in ./files/datei2.txt
cat ./files/datei2.txt 
# Hello world is printed out in console four times and written one time in ./files/datei.txt and three times in ./files/datei2.txt

#

# Difference: >> adds content, > overwrites content
cat ./files/datei.txt >> ./files/datei.txt 2> ./files/errorsCat.log

echo "d) - - - - - - - - - - - - - -"
whoami > ./files/info.txt
echo "e) - - - - - - - - - - - - - -"
id >> ./files/info.txt
echo "f) - - - - - - - - - - - - - -"
cat ./files/info.txt | wc -w > ./files/wordCount.txt
