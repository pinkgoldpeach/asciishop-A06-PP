import java.util.ArrayList;
/** Diese Klasse ersetzt alle Vorkommnisse eines Zeichens in einem Bild durch ein anderes Zeichen
 */

public class ReplaceOperation implements Operation {

    private char oldChar, newChar;

    public ReplaceOperation(char oldChar, char newChar){
        this.oldChar = oldChar;
        this.newChar = newChar;
    }

    /** gibt ein neues AsciiImage zurück, in dem alle Vorkommnisse des Zeichen oldChar
     * durch das Zeichen newChar ersetzt worden sind. 
     * Falls das neue Zeichen nicht im Zeichensatz des AsciiImage enthalten ist, 
     * soll eine neue OperationException mit entsprechender Fehlermeldung geworfen werden.
     */

    public AsciiImage execute(AsciiImage img) throws OperationException{
        AsciiImage image = new AsciiImage(img);
        ArrayList<AsciiPoint> points = new ArrayList<AsciiPoint>();
        points = img.getPointList(oldChar);
        if(img.getCharset().indexOf(newChar)!= -1) {
            for (AsciiPoint p : points) {
                image.setPixel(p, newChar);
            }
        }else{
            throw new OperationException("OPERATION FAILED");
        }
        return image;
    }
}
