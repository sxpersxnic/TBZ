#!/bin/bash

echo -n "Module number: "; read -r moduleNumber
echo -n "Module topic: "; read -r moduleTopic

git remote add -f module${moduleNumber} git@github.com:sxpersxnic/TBZ-Module-${moduleNumber}.git
git read-tree --prefix="m${moduleNumber}-${moduleTopic}" -u module${moduleNumber}/main

echo "Done"
