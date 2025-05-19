# KN03: Netzwerk, Sicherheit

## A. Eigenes Netzwerk

1. **Addressing:**

    > ![IP addresses of Busybox{1..4}](/m347-Container/x-resources/03/busybox.png)

2. **Busybox1** with same Default-Gateway as Busybox2:

    > ![Information of Busybox1](/m347-Container/x-resources/03/busybox1.png)

3. **Busybox3** with same Default-Gateway as Busybox4:

    > ![Information of Busybox3](/m347-Container/x-resources/03/busybox3.png)

4. **Gemeinsamkeiten und Unterschiede:**

    - Another container is pingable if it is in the same network.
    - When a container name outside of the network is pinged, the container is not found so no packages are sent.
    - An IP-Address inside the same network is pingable as much as its container name.
    - An IP-Address outside of the network is pingable and won't instantly return an error as it did when we pinged the container by its name. That's because the ping sends packages to that IP but if it is not found all packages are lost.

5. **Erläuterung KN02**

    - In welchem Netzwerk befanden sich die beiden Container?

        **Antwort:** Im default Netzwerk (bridge).

    - Wieso konnten die miteinander reden?

        **Antwort:** Weil sie sich im selben Netz befanden, konnte der Containername als Servername in db.php verwendet werden.
