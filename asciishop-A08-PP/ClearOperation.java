/** Diese Klasse setzt alle Pixel des Bildes auf das hellste Zeichen.
 * gibt ein neues AsciiImage zurück, das dem übergebenen AsciiImage entspricht, 
 * wobei alle Zeichen auf das hellste Zeichen, sprich dem letzten Zeichen im Zeichensatz des Bildes, gesetzt sind.
 */
public class ClearOperation implements Operation {

    public ClearOperation(){

    }
    public AsciiImage execute(AsciiImage img){
        AsciiImage image = new AsciiImage(img);
        String charset = img.getCharset();
        for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){
                image.setPixel(x, y, charset.charAt(charset.length()-1));
            }
        }
        return image;
    }
}
