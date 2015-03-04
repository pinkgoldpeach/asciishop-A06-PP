/** Diese Klasse glättet ein Bild mit einem 3x3-Medianfilter
 */

public class MedianOperation extends FilterOperation {


    public MedianOperation(){

    }
// geerbt von FilterOperation
    public AsciiImage execute(AsciiImage img){
        return super.execute(img);
    }

/** führt mit dem übergebenen Block den Medianfilter aus. 
 * Die Pixel des Blocks werden nach ihrem Helligkeitswert sortiert. 
 * Der Median (also das in der sortierten Liste in der Mitte stehende Zeichen) 
 * für diesen Block wird als Ergebnis zurückgegeben.
 */ 

//values/2 = mitte?? -1, weil array hat 0-... stellen
    public int filter(int[] values){
        int pos = (int)(Math.round((float)(values.length)/2.0));
        return values[pos-1];
    }

}
