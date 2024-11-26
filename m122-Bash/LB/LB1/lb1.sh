#!/usr/bin/bash

title="LINUX BASH-ÜBUNGEN ALS PRÜFUNGSVORBEREITUNG"
seperator="--------------------------------------------------"

link_rheinwerk=http://openbook.rheinwerk-verlag.de/shell_programmierung/
link_Cron_Jobs=https://www.stetic.com/developer/cronjob-linux-tutorial-und-crontab-syntax/
link_compressing="https://www.thomas-krenn.com/de/wiki/Archive_unter_Linux_(tar,_gz,_bz2,_zip)"
link_network_conf=https://www.howtoforge.de/anleitung/linux-ifconfig-befehl/
link_FPing_install=https://fping.org/
subtitle="Aufgaben"
a="a) Was macht folgende Zeile? --- ifconfig | grep "Ether" -c"
b="b) Was macht folgende Zeile? --- tar -vczf backup.tar.gz /root/"
c="c) Füllen sie eine Datei namen.txt mit folgendem Inhalt (Inhalt copy-paste)
     Otto
     Peter
     Martin
     Christian
     Andrea
     Tim
     Sven
     Heinz
     Bob
"

c2="Was macht folgender Befehl? -cat namen.txt | sort -u"
d="d) Formulieren sie einen Befehl, welcher aus der Datei /etc/passwd alle Heimverzeichnisse ausschneidet und in einer Datei homes.txt speichert."
e="e) Formulieren sie eine for-schleife, welche durch die Zahlen 1 bis 10 läuft und das Produkt der Zahlen mit sich selbst ausgibt."
f="f) Wie oft laufen folgende cronjobs? --- */10 * * * * <befehl1>
     5 8 * * 0 <befehl2>
     0 10 1 * * <befehl3>
"

gh="g) and h) is difficult to use in script go on this link to see: https://gitlab.com/ch-tbz-it/Stud/m122/-/tree/main/05_Bash_Vorb_LB1?ref_type=heads"
i="i) Was macht folgender Befehl? --- find / -user otto -iname "*bash*" -exec cp {} /data/otto/ \;
"
j="j) Was machen folgende Befehle? --- for ip in $(seq 200 254);do echo 192.168.13.$ip; done > ips.txt
      for ip in $(cat ips.txt);do dig -x $ip | grep $ip >> dns.txt; done
"

answers="
ANSWERS:
a)  Zählt wie oft der Begriff *ether* in der Ausgabe von grep vorkommt.

b) Komprimiert und archiviert den Inhalt des Ordners `/root/` in der Datei `backup.tar.gz`
c) Gibt die Begriffe in alphabetischer Reihenfolge ohne Duplikate aus
d)      cat /etc/passwd | cut -d ':' -f 6 > homes.txt
e)	for z in {1..10};do echo $((z*z)); done
f)  1: Alle 10 Minuten
    2: Sonntags um 8:05 Uhr
    3: An jedem 1.Tag im Monat um 10:00 Uhr
g) Enfacher Ping-Sweep des fping-Befehls mit `-g` Option
h) Lösung Ping-Sweep mit Schleifenverarbeitung
i)  Findet alle Dateien von user `otto` mit dem Begriff bash im Namen und kopiert diese nach `/data/otto`.
j) 1. Zeile: generiert IPs (24er Netz 192.168.13.x) und speichert diese in `ips.txt`
2. Zeile macht einen reverse DNS-lookup zu jeder IP in `ips.txt`
"
echo $title
echo $seperator
echo $link_rheinwerk
echo $seperator 
echo $link_Cron_Jobs
echo $seperator 
echo $link_compressing
echo $seperator 
echo $link_FPing_install
echo $seperator 
echo $link_network_conf
echo $seperator
echo $a
echo $seperator 
echo $b
echo $seperator 
echo $c
echo $seperator 
echo $c2
echo $seperator 
echo $d
echo $seperator 
echo $e
echo $seperator 
echo $f
echo $seperator 
echo $gh
echo $seperator 
echo $i
echo $seperator 
echo $j
echo $seperator
echo $answers
echo $seperator