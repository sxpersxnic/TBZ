#!/usr/bin/bash

echo "-----------------------------------------------------------------------------"
echo $LOGNAME
echo "<- LOGNAME is the Loginname of the current user"
echo "-----------------------------------------------------------------------------"
echo $0
echo "<- 0 is the name of the called script"
echo "-----------------------------------------------------------------------------"
echo $1
echo "<- 1-9, {10}, ..., *: Params of the called shellscript"
echo "-----------------------------------------------------------------------------"
echo $#
echo "<- count of params in called sh-script"
echo "-----------------------------------------------------------------------------"
echo $$
echo "<-Processnumber of called sh-script"
echo "-----------------------------------------------------------------------------"
echo $?
echo "<- Finishstate of sh-script"
echo "-----------------------------------------------------------------------------"
echo $!
echo "Processnumber of previous started backgroundprocess"
echo "-----------------------------------------------------------------------------"
echo $PWD
echo "<- cwd"
echo "-----------------------------------------------------------------------------"
echo $OLDPWD
echo "<- Previous cwd"
echo "-----------------------------------------------------------------------------"
echo $HOME
echo "<- Home directiory of user, standard for cd"
echo "-----------------------------------------------------------------------------"
echo $UID
echo "<- User-ID of user. This identificator is locatef in /etc/passwd/-username-"
echo "-----------------------------------------------------------------------------"
echo $PATH
echo "<- Suchpfad für die Kommandos (Programme); meistens handelt es sich um eine durch Doppelpunkte getrennte Liste von Verzeichnissen, in denen nach einem Kommando gesucht wird, das ohne Pfadangabe aufgerufen wurde; Standardwerte: PATH=:/bin:/usr/bin"
echo "-----------------------------------------------------------------------------"
echo $CDPATH
echo "<- Suchpfad für das cd-Kommando"
echo "-----------------------------------------------------------------------------"
echo $SHELL
echo "<- Zeigt die aktuelle Shell mit dem Pfad an"
echo "-----------------------------------------------------------------------------"
echo $RANDOM
echo "<- Pseudo-Zufallszahl zwischen 0 bis 32767"
echo "-----------------------------------------------------------------------------"
echo $REPLY
echo "<- 	Bei Menüs (select) enthält REPLY die ausgewählte Nummer."
echo "-----------------------------------------------------------------------------"
echo $SECONDS
echo "<- Enthält die Anzahl von Sekunden, die seit dem Start (Login) der aktuellen Shell vergangen ist."
echo "-----------------------------------------------------------------------------"
echo $PROMPT_COMMAND
echo "<- Hier kann ein Kommando angegeben werden, das vor jeder Eingabeaufforderung automatisch ausgeführt wird."
echo "-----------------------------------------------------------------------------"
echo $PS1
echo "<- 	Primärer Prompt; Prompt zur Eingabe von Befehlen."
echo "-----------------------------------------------------------------------------"
echo $TZ
echo "<- Legt die Zeitzone fest (hierzulande MET = Middle European Time)"
echo "-----------------------------------------------------------------------------"