#!/usr/bin/bash

# Exercise 2 - Wildcards
# ---
# 1. Create a directory /Docs in your Home directory
mkdir ~/Docs
# ---
# 2. Create the files file1 to file10 in the ~/Docs dir using touch
touch ~/Docs/file{1..10}
# ---
# 3. Delete all files, whos names include a "1"
rm -f ~/Docs/file1*
# ---
# 4. Delete file2, file4, file7 in a single command
rm -f ~/Docs/file[247]
# ---
# 5. Delete all remaining files in a single command
rm -f ~/Docs/* | rm -f ~/Docs/file*
# ---
# 6. Create a dir "Ordner" in your Home dir
mkdir ~/Ordner && cd ~/Ordner
# ---
# 7. Create the files file1 to file10 in the Ordner dir using touch
touch file{1..10}
# ---
# 8. Copy the dir Ordner and the whole content to Ordner2
cd .. && cp -R Ordner Ordner2
# ---
# 9. Copy the dir Ordner and the whole content to Ordner2/Ordner3
cp -R Ordner Ordner2/Ordner3
# ---
# 10. Rename the dir Ordner to Ordner1
mv Ordner Ordner1
# --- 
# 11. Remove all created directories ,including all files.
rm -r Ordner1 Ordner2 
# rm -f * wäre auch möglich doch ich möchte nicht das gesamte verzeichniss löschen
# ---