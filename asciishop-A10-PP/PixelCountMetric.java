/** Eine einfache Metrik für Bilder, die Bildgrößen vergleicht.
 * liefert den Absolutbetrag der Differenz der Bildgrößen von i1 und i2.
 * Mit Bildgröße ist das Produkt von Höhe mal Breite des Bildes gemeint.
 */
public class PixelCountMetric implements Metric<AsciiImage> {

    public int distance(AsciiImage i1, AsciiImage i2){
        int distance = i1.getHeight()*i1.getWidth() - i2.getHeight()*i2.getWidth();
        return Math.abs(distance);
    }

}

