# ePortfolio - 14-06-2024

## Netwerkplan

### Physisch


![Verkabelungsplan](../assets/assets_14-06-2024/Netzwerkplan_Physisch.svg)


#### Details:
- Physische Topologie: Sterntopologie
- Kabellängen:
    - Grundrissmasse: 13.5m * 13m = 175.5qm
    - Büro1 - Geschäftsleitung | Total: 19m:
        - PC1: 7m
        - Switch1: 11m
        - S/W Drucker1: 0.5m
        - Access Point (AP1): 0.5m
    - Büro2 - Buchhalter:inen | Total: 20.7m:
        - PC2: 1m
        - PC3: 0.5m
        - PC4: 0.5m
        - PC5: 0.5m
        - S/W Drucker2: 0.2m
        - Switch2: 18m
    - Büro3 - Jurist:innen | Total: 29.2m:
        - PC7: 6m
        - PC6: 10m
        - Access Point (AP3): 5.6m
        - S/W Drucker3: 0.2m
        - Switch5: 0.2m 
        - Router: 7.2m
    - Büro4 - HR-Mitarbeiterin & Sekretärin | Total: 9m:
        - PC9: 2m
        - PC10: 4.5m
        - Farblaser Drucker: 0.5m
        - Switch6: 2m
    - Sitzungszimmer | Total: 13m:
        - Switch4: 13m
    - Unbenutzbarer Raum | Total: 14.5m:
        - Switch3: 14m
        - Access Point (AP2): 0.5m

#### Alle Daten

| Zimmer| Geräte |Kabellänge total in m |
|-------|--------|----------------------|
| Büro1 Geschäftsleitung | 1x PC, 1x Switch, 1x S/W Drucker, 1x Access Point | 19m |
| Büro2 Buchhalter:innen | 4x PC, 1x Switch, 1x S/W Drucker | 20.7m |
| Büro3 Jurist:innen | 2x PC, 1x Switch, 1x Router, 1x S/W Drucker, 1x Access Point | 29.2m |
| Büro4 HR Mitarbeiterin & Sekretärin | 2x PC, 1x Switch, 1x Farblaser Drucker | 9m |
| Sitzungszimmer | 1x Switch | 13m |
| Unbenutzbarer Raum | 1x Switch, 1x Access Point | 14.5m |
|| **TOTAL Anz. Geräte:** 10x PC, 6x Switch, 3x S/W Drucker, 1x Farblaser, 3x Access Points, 1x Router | **TOTALE Kabellänge in m:** *105.4m Ethernet Glasfaserkabel* |

- Ästhetik:
Da man keine Kabelkanäle in den Wänden verfügbar sind und auch kein verlegt werden sollen, kommt man nicht herum die Kabel durch die Räume zu ziehen. Doch man kann trotzdem Kabelkanäle verwenden und diese schön verputzen, damit diese nicht sichtbar sind.

### Logisch

![Logsiches Layout](../assets/assets_14-06-2024/Netzwerkplan_Logisch.svg)

#### Details:
- Gateway/Subnetzmaske: Alle Geräte haben die Subnetzmaske 255.255.255.0 (Standard für ein /24 Subnetz).
- Switch5: Dient als Backbone und Switch für Büro 3 der Jurist:innen.

- Adressen Hierarchie:

| Gerät | Startadresse | Endadresse | Mögliche Hosts pro Gerät |
|-------|--------------|------------|--------------------------|
| Switch | 192.168.1.1| 192.168.1.6 | 10 |
| AP (Access Point) | 192.168.1.10 | 192.168.1.12 | 10 |
| Drucker | 192.168.1.20 | 192.168.1.23 | 20 |
| PC | 192.168.1.40| 192.168.1.49 | 203 |
| Router | 192.168.1.254 | 192.168.1.254 | 1 |
| Broadcast | 192.165.1.255 | 192.168.1.255 | 1 |
