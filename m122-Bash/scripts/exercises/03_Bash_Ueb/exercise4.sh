#!/bin/bash

# Exercise 4 - grep, cut (, awk):
seperator=""
echo "a) ---------------------"
echo $seperator
# Create text file with content
echo -e "alpha1:1alpha1:alp1ha\nbeta2:2beta:be2ta\ngamma3:3gamma:gam3ma\nobelix:belixo:xobeli\nasterix:sterixa:xasteri\nidefix:defixi:ixidef" > file.txt
# Grep patterns
cat file.txt | grep obelix
echo $seperator
cat file.txt | grep 2
echo $seperator
cat file.txt | grep e
echo $seperator
cat file.txt | grep -v gamma
echo $seperator
cat file.txt | grep -E "1|2|3"
echo $seperator
echo "b) ---------------------"
echo $seperator
cat file.txt | cut -d ':' -f 1
echo $seperator
cat file.txt | cut -d ':' -f 2
echo $seperator
cat file.txt | cut -d ':' -f 3
echo "c) ---------------------"
# Awk command
awk -F':' '{print $(NF-1)}' file.txt