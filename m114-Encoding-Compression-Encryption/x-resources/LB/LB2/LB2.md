# Multi-Media

## RGB (*R*ed *G*reen *B*lue)

1. <div style="display:flex;flex-direction:row;gap:0.5rem;"><strong>RGB (252, 178, 92)</strong><p>=</p><p style="color:rgb(252,178,91)">Orange</p></div>
2. <div style="display:flex;flex-direction:row;gap:0.5rem;"><strong>#ff0000</strong><p>=</p><p style="color:#ff0000">Red</p></div>
3. <div style="display:flex;flex-direction:row;gap:0.5rem;"><strong>#00ff00</strong><p>=</p><p style="color:#00ff00">Green</p></div>
4. <div style="display:flex;flex-direction:row;gap:0.5rem;"><strong>#0000ff</strong><p>=</p><p style="color:#0000ff">Blue</p></div>
5. <div style="display:flex;flex-direction:row;gap:0.5rem;"><strong>#ffff00</strong><p>=</p><p style="color:#ffff00">Yellow</p></div>
6. <div style="display:flex;flex-direction:row;gap:0.5rem;"><strong>#000000</strong><p>=</p><p style="color:#000000;background-color:#ffffff11;">Black</p></div>
7. <div style="display:flex;flex-direction:row;gap:0.5rem;"><strong>#00bc00</strong><p>=</p><p style="color:#00bc00">Green</p></div>

## CMYK (*C*yan *M*agenta *Y*ellow *K*ey)

1. <div style="display:flex;flex-direction:row;gap:0.5rem;"><strong>C:</strong><p>100%</p></strong><strong>M:</strong><p>0%</p><strong>Y:</strong><p>0%</p><strong>K:</strong><p>0% =</p><p style="color:#00ffff;">Cyan</p></div>
2. <div style="display:flex;flex-direction:row;gap:0.5rem;"><strong>C:</strong><p>0%</p></strong><strong>M:</strong><p>100%</p><strong>Y:</strong><p>100%</p><strong>K:</strong><p>0% =</p><p style="color:#ff0000;">Red</p></div>
3. <div style="display:flex;flex-direction:row;gap:0.5rem;"><strong>C:</strong><p>100%</p></strong><strong>M:</strong><p>100%</p><strong>Y:</strong><p>0%</p><strong>K:</strong><p>0% =</p><p style="color:#0000ff;">Blue</p></div>
4. <div style="display:flex;flex-direction:row;gap:0.5rem;"><strong>C:</strong><p>100%</p></strong><strong>M:</strong><p>100%</p><strong>Y:</strong><p>100%</p><strong>K:</strong><p>0% =</p><p style="color:#000000;background-color:#ffffff11;">Black</p></div>
5. <div style="display:flex;flex-direction:row;gap:0.5rem;"><strong>C:</strong><p>0%</p></strong><strong>M:</strong><p>0%</p><strong>Y:</strong><p>0%</p><strong>K:</strong><p>100% =</p><p style="color:#000000;background-color:#ffffff11;">Black</p></div>
6. <div style="display:flex;flex-direction:row;gap:0.5rem;"><strong>C:</strong><p>0%</p></strong><strong>M:</strong><p>46%</p><strong>Y:</strong><p>38%</p><strong>K:</strong><p>22% =</p><p style="color:#c76b7b;">Pink-Red</p></div>


## DPI (**d**ots **p**er **i**nch) -> Drucker

- dpi = Anzahl Farbpunkte pro **2.54cm** Breite.
- **1 Inch** = **1 Zoll** = **2.54cm**
- **Formel:**  
  ```
  px / dpi = x cm
  ```

## PPI (**p**ixel **p**er **i**nch) -> Bildschirm

- ppi = Anzahl Pixel pro **2.54cm** Breite.
- **Formel:**  
  ```
  x = D / sqrt(a^2 + b^2)
  ```
  - Horizontale Pixel = a × x × ppi  
  - Vertikale Pixel = b × x × ppi  

## Speicherbedarf RGB (1B/Kanal)

Bsp. **1000x600** Pixel: 1000 x 600 x 3 = **1'800'000 Byte**

- **Formel - HD *(x)* Seitenverhältnis *(a:b)* :**  
  - x / a * b = *y* Pixel/Bildzeile  
  - x * y * 3 = *z* Byte/Bild  
  - Speicherbedarf bei v-min-Video *(60 * v)* mit 25 Bilder/s *(s)*:  
    - z * s * 60 * v = w Byte auf Speichermedium.  

## Bildformate

- R = Rastergrafik  
- V = Vektorgrafik  

|Format|Browser unterstützt|Grafiktyp|a-Kanal|Transparenzfarbe|Geeignet für...|
|------|-------------------|---------|-------|----------------|---------------|
|JPG|Ja|R|Nein|Nein|Bilder, verlustfreie Komprimierung|
|GIF|Ja|R|Nein|Ja|Grafik, Bildcompositing, Animation|
|PNG|Ja|R|Ja|Nein|Bilder, 2D-Grafik|
|TIF|Nein|R|Ja|Nein|Bilder für Archivierung, Verlustlos|
|BMP|Ja|R|Nein|Nein|Bilder, kein bevorzugtes Format|
|WEBP|Ja|R|Ja|Nein|Bilder, Animationen|
|SVG|Ja|V|n.a.|n.a.|2D-Grafik|

## RGB, CMYK, HEX-Darstellung von RGB und CMYK

## YCrCb Nur Umrechnung RGB zu Y (Luminanz)

```Y = 0.299 * R + 0.587 * G + 0.114 * B```

## Verständnisfragen

- **Alphakanal** → Transparenzkanal (z. B. in PNG)  
- **GIF-Format**  
  - **Farbtabelle:** Nur 256 Farben  
  - **Transparenzfarbe:** Nur eine Farbe kann transparent sein  
  - **Animation:** Unterstützt einfache Animationen  
- **Rastergrafik vs. Vektorgrafik**  
  - **Rastergrafik:** Pixelbasiert (Fotos, JPG, PNG)  
  - **Vektorgrafik:** Mathematisch beschrieben (SVG, AI) → Skalierbar ohne Qualitätsverlust  

## Analog-Digital-Umwandlung
1. **Sampling** → Zeitdiskretisierung (z. B. 44.1 kHz für Audio)  
2. **Quantisierung** → Wertdiskretisierung (Bit-Tiefe)  
3. **Kodierung** → Speicherung als Binärdaten  

## Videonormen

| Norm | Auflösung | Bildmodus |
|------|-----------|----------|
| **HD 720p** | 1280x720 | Progressiv |
| **Full HD 1080p** | 1920x1080 | Progressiv |

## Speicherplatzberechnung bei Bildern/Videos

**Bildspeicherplatz (unkomprimiert):**  
```Speicher (Bytes) = Breite × Höhe × Farbtiefe (in Byte)```

**Videospeicherplatz:**  
```Gesamtgröße = Bildgröße × FPS × Dauer (Sekunden)```

## Codes vs. Container

- **Codecs** → Komprimieren & dekomprimieren Daten (z. B. H.264, VP9)  
- **Container** → Verpackt Video, Audio, Untertitel (z. B. MP4, MKV, AVI)  

## Verlustbehaftete Komprimierung

- **Farbreduktion** → Weniger Farben (GIF, PNG)  
- **Auflösungsreduktion** → Pixelanzahl verringern  
- **Subsampling (Farbinformationen reduzieren)**  
  - **4:4:4** → Keine Reduktion  
  - **4:2:2** → Horizontale Farbinformation halbiert  
  - **4:1:1** → Noch stärkere Reduktion  
  - **4:2:0** → Horizontale & vertikale Reduktion  

### Subsampling-Berechnung
```Ersparnis = 100% - (Datenmenge mit Subsampling / Originaldatenmenge × 100%)```

## JPG Blockartefakte

- **Durch DCT entstehen 8×8-Pixelblöcke**  
- **Starke Komprimierung** → Sichtbare Blockartefakte  

## Intraframe vs. Interframe-Komprimierung

- **Intraframe (innerhalb eines Bildes)** → Komprimierung jedes Einzelbildes  
- **Interframe (über mehrere Bilder hinweg)** → Komprimiert durch Vergleich mit vorherigen/nächsten Bildern  

## GOP (Group of Pictures) Sequenz

- **I-Frame (Intra-Frame):** Vollständiges Bild  
- **P-Frame (Prädiktiv-Frame):** Speichert nur Änderungen zum letzten Bild  
- **B-Frame (Bidirektional-Frame):** Speichert Unterschiede zu vorherigen & nächsten Bildern  
- **Beispiel:** GOP25 → Alle **25 Bilder** enthält ein vollständiges I-Frame  

