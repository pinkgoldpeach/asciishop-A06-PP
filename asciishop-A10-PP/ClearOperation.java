/** Diese Klasse setzt alle Pixel des Bildes auf das hellste Zeichen.
 * gibt ein neues AsciiImage zurück, das dem übergebenen AsciiImage entspricht, 
 * wobei alle Zeichen auf das hellste Zeichen, sprich dem letzten Zeichen im Zeichensatz des Bildes, gesetzt sind.
 */


public class ClearOperation implements Operation {

    public ClearOperation(){

    }

    public AsciiImage execute(AsciiImage img){
        AsciiImage image = new AsciiImage(img.getWidth(), img.getHeight(), img.getCharset());
        return image;
    }
}
