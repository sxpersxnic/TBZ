#!/usr/bin/sh

rm -f collect.txt
touch ../k8s/collect.txt

echo "// namespace.yml" > ../k8s/collect.txt
cat ../k8s/namespace.yml >> ../k8s/collect.txt
echo -e "\n// account.yml" >> ../k8s/collect.txt
cat ../k8s/account.yml >> ../k8s/collect.txt
echo -e "\n// account-config.yml" >> ../k8s/collect.txt
cat ../k8s/account-config.yml >> ../k8s/collect.txt
echo "\n// buysell.yml" >> ../k8s/collect.txt
cat ../k8s/buysell.yml >> ../k8s/collect.txt
echo "\n// buysell-config.yml" >> ../k8s/collect.txt
cat ../k8s/buysell-config.yml >> ../k8s/collect.txt
echo "\n// sendreceive.yml" >> ../k8s/collect.txt
cat ../k8s/sendreceive.yml >> ../k8s/collect.txt
echo "\n// sendreceive-config.yml" >> ../k8s/collect.txt
cat ../k8s/sendreceive-config.yml >> ../k8s/collect.txt