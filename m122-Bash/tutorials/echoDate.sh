#!/usr/bin/bash

date=$(date +%Y_%m_%d)
#echo $date
date2=$date; echo $date2

cd ./DATE
touch file_$date
ls 
cd ..
