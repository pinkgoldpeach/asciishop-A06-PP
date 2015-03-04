/** Eine einfache Metrik für Bilder, die die Anzahl unterschiedlicher Zeichen vergleicht.
 * liefert den Absolutbetrag der Differenz der Anzahl unterschiedlicher Zeichen in einem Bild. 
 * Zur Berechnung der Anzahl unterschiedlicher Zeichen eines Bildes kann die Methode getUniqueChars() 
 * aus Runde 4 herangezogen werden.
 */
public class UniqueCharsMetric implements Metric<AsciiImage>{

    public int distance(AsciiImage i1, AsciiImage i2){
        int distance = i1.getUniqueChars()-i2.getUniqueChars();
        return Math.abs(distance);
    }
}
