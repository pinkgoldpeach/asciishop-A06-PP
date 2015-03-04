import java.util.Arrays;

/** Diese abstrakte Klasse beinhaltet die Funktionalität, 
 * um das Bild zu durchlaufen und für jeden Pixel den benötigten Block an Nachbarpixeln zu bestimmen.
 * Sie bietet mit der Methode filter eine Schablone (Template) für die konkreten Filter Operationen.
 */

public abstract class FilterOperation implements Operation {

    public FilterOperation(){

    }

    /** führt den Blockfilter aus. Diese Methode muss von abgeleiteten Klassen nicht überschrieben werden. 
     * Die Methode durchläuft das Bild, bestimmt für jeden Pixel den Block an Nachbarpixeln 
     * und ruft damit dann die Methode filter auf. 
     * Das Ergebnis dieser Methode wird dann als neuer Pixel an der aktuellen Stelle gesetzt.
     */

    public AsciiImage execute(AsciiImage img) {
        AsciiImage image = new AsciiImage(img);
        int[] checkPixels = new int[9];
        String charset = img.getCharset();
        int counter = 0;
        try {
            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    for (int i = (y + 1); i >= (y - 1); i--) {
                        checkPixels[counter++] = getIndex(img, x, i);
                        checkPixels[counter++] = getIndex(img, (x - 1), i);
                        checkPixels[counter++] = getIndex(img, (x + 1), i);
                    }
                    Arrays.sort(checkPixels);
                    image.setPixel(x, y, charset.charAt(filter(checkPixels)));
                    counter =0;
                }
            }
        }catch (OperationException o){
            System.out.println(o.getMessage());
        }
        return image;
    }

    public int getIndex(AsciiImage img, int x, int y) throws OperationException {
        try{
            char c = img.getPixel(x, y);
            if(img.getCharset().indexOf(c) != -1){
                return img.getCharset().lastIndexOf(c);
            }else{
                throw new OperationException("OPERATION FAILED");
            }
        }catch(IndexOutOfBoundsException i){
            return img.getCharset().length()-1;
        }
    }

/** muss von den abgeleiteten Klassen implementiert werden. 
 * Sie führt die eigentliche Logik des Filters durch. 
 * Das übergebene Array umfasst die Helligkeitswerte der Pixel im Block Zeile für Zeile. 
 * Diese Methode gibt den berechneten Helligkeitswert für den neuen Pixel zurück.
 */
    public abstract int filter(int[] values);


}
