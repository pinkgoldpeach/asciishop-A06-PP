import java.util.ArrayList;

/**
 * Diese Klasse ersetzt alle Vorkommnisse eines Zeichens in einem Bild durch ein anderes Zeichen. 
 */
public class ReplaceOperation implements Operation {

    char oldChar, newChar;

    public ReplaceOperation(char oldChar, char newChar){
        this.oldChar = oldChar;
        this.newChar = newChar;
    }
/** get all asciipoints of the old char and set them to the new char
 * throw operation exception, if newChar is not part of the charset.
 */
    public AsciiImage execute(AsciiImage img) throws OperationException{
        if(img.getCharset().indexOf(newChar) == -1){
            throw new OperationException("OPERATION FAILED");
        }else {
            AsciiImage image = new AsciiImage(img);
            ArrayList<AsciiPoint> points = new ArrayList<AsciiPoint>();
            points = image.getPointList(oldChar);

            for (AsciiPoint p : points) {
                image.setPixel(p, newChar);
            }
            return image;
        }
    }
}
