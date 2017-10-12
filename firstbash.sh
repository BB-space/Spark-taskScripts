#!/bin/bash
MAX_NO = 0

echo -n "Enter Number between (5 to 9):"
read MAX_NO

if ![$MAX_NO -ge 5 -a $MAX_NO -le 9];then
	echo "i asked to endter number between 5 to 9"
	exit 1

clear

for((i=1;i=i;s--))
do
	echo -n " "
done
for((j=1;j=1;i--))
do
	for((s=i;s<=MAX_NO;s++))
	do
		echo -n " "
	done
	for((j=1;j<=i;j++))
	do
		echo -n " ."
	done
	echo ""
done

echo -e"\n\n\t\t\t Whenever u need help,Tecmint.com is always there
