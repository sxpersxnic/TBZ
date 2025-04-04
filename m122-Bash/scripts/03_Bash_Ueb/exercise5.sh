#!/usr/bin/bash

# Exercise 5 - For advanced
# Explain the expressions
# 1. Show lines in dmesg matching a timestamp pattern
dmesg | egrep '[0-9]{4}:[0-9]{2}:[0-9a-f]{2}.[0-9]'

# 2. Show IP addresses in ifconfig output
ifconfig | grep -oE '((1?[0-9][0-9]?|2[0-4][0-9]|25[0-5])\.){3}(1?[0-9][0-9]?|2[0-4][0-9]|25[0-5])'
