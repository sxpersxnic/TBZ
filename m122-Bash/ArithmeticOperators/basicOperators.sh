#!/usr/bin/bash

a=5
b=10

echo "there are min. two types of expressions for arithmetical calculations: "
echo "x='$'(( IntArithmetic )) and x='$'[ IntArithmetic ] the dollar signs are shouldnt use '' its just for explanation!!!"

add=$(( $a+$b ))
echo $add
substract=$(( $b-$a ))
echo $substract
multiplicate=$(( $b*$a ))
echo $multiplicate
division=$(( $b/$a ))
echo $division
modulo=$(( $b%$a ))
echo $modulo
power=$(($a**$b))
echo $power


