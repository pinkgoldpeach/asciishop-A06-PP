import java.util.Arrays;

/** Diese abstrakte Klasse beinhaltet die Funktionalität, 
 * um das Bild zu durchlaufen und für jeden Pixel den benötigten Block an Nachbarpixeln zu bestimmen.
 * Sie bietet mit der Methode filter eine Schablone (Template) für die konkreten Filter Operationen.
 */

public abstract class FilterOperation implements Operation {

    private BlockGenerator generator;

    public FilterOperation(BlockGenerator generator){
        this.generator = generator;
    }

    /** führt den Blockfilter aus. Diese Methode muss von abgeleiteten Klassen nicht überschrieben werden. 
     * Die Methode durchläuft das Bild, bestimmt für jeden Pixel den Block an Nachbarpixeln 
     * und ruft damit dann die Methode filter auf. 
     * Das Ergebnis dieser Methode wird dann als neuer Pixel an der aktuellen Stelle gesetzt.
     */
    public AsciiImage execute(AsciiImage img) throws OperationException{
    
        
        AsciiImage image = new AsciiImage(img);
        int[] pixels = new int[generator.getSize()];

        for(int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                pixels = generator.getBlock(img, x, y);
                image.setPixel(x, y, img.getCharset().charAt(filter(pixels)));
            }
        }

        return image;
    }
/** muss von den abgeleiteten Klassen implementiert werden. 
 * Sie führt die eigentliche Logik des Filters durch. 
 * Das übergebene Array umfasst die Helligkeitswerte der Pixel im Block Zeile für Zeile. 
 * Diese Methode gibt den berechneten Helligkeitswert für den neuen Pixel zurück.
 */

    public abstract int filter(int[] values);
}
