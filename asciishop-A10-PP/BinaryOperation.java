import java.util.ArrayList;

/** Diese Klasse wandelt ein AsciiImage in ein Binärbild um.
 * gibt ein neues AsciiImage zurück, das das Binärbild des übergebenen AsciiImage ist. 
 * Zur Umwandlung in ein Binärbild werden alle Zeichen, 
 * die im Zeichensatz des Bildes vor dem Schwellwert kommen, 
 * auf das dunkelste Zeichen gesetzt, 
 * alle Zeichen ab dem Schwellwert werden auf das hellste Zeichen gesetzt.
 * Sollte das Schwellwertzeichen nicht im Zeichensatz des AsciiImage vorkommen, so wird eine OperationException geworfen.
 */

public class BinaryOperation implements Operation {

    private char threshold;

    public BinaryOperation(char threshold){

        this.threshold = threshold;
    }

    public AsciiImage execute(AsciiImage img) throws OperationException{
        AsciiImage image = new AsciiImage(img);
            if(image.getCharset().indexOf(threshold) == -1 ){
                throw new OperationException("OPERATION FAILED");
            }else{
                for(int y = 0; y < image.getHeight(); y++){
                    for(int x = 0; x < image.getWidth(); x++){
                        char replace = image.getPixel(x, y);
                        image.setPixel(x, y, checkChar(replace, img));
                    }
                }
            }
            return image;
        }

    /** returns either the darkest or the lightest char in the charset,
     * depending on the position of the char. (before threshold = darkest, after threshold = lightest)
     */
    private char checkChar(char c, AsciiImage img){
        String charset = img.getCharset();
        if(charset.indexOf(c) < charset.indexOf(threshold)){
            return charset.charAt(0);
        }else{
            return charset.charAt((charset.length()-1));
        }
    }
}
