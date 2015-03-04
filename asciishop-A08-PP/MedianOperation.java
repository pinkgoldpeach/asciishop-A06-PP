import java.util.Arrays;
/** führt auf einer Kopie des Bildes den Medianfilter aus. 
 * Dabei werden immer 3 mal 3 Größe Blöcke des Bildes betrachtet, 
 * die Pixel nach ihrem `Helligkeitswert' sortiert 
 * und dann der Median (also das in der sortierten Liste in der Mitte stehende Zeichen)
 * als neues Pixel im Mittelpunkt des Blocks gesetzt.
 */

public class MedianOperation implements Operation {

    public MedianOperation(){

    }

    public AsciiImage execute(AsciiImage img) throws OperationException{
            AsciiImage image = new AsciiImage(img);
            int width = img.getWidth();
            int height = img.getHeight();
            String charset = img.getCharset();
            int[] checkPixel = new int[9];
            for(int y = 0; y < height; y ++){
                for(int x = 0; x < width; x++){

                    checkPixel[0] = charset.lastIndexOf(getPixel(x, y, img));
                    checkPixel[1] = charset.lastIndexOf(getPixel(x, (y + 1), img));
                    checkPixel[2] = charset.lastIndexOf(getPixel(x, (y - 1), img));
                    checkPixel[3] = charset.lastIndexOf(getPixel((x - 1), y, img));
                    checkPixel[4] = charset.lastIndexOf(getPixel((x - 1), (y - 1), img));
                    checkPixel[5] = charset.lastIndexOf(getPixel((x - 1), (y + 1), img));
                    checkPixel[6] = charset.lastIndexOf(getPixel((x + 1), y, img));
                    checkPixel[7] = charset.lastIndexOf(getPixel((x + 1), (y - 1), img));
                    checkPixel[8] = charset.lastIndexOf(getPixel((x + 1), (y + 1), img));

                    Arrays.sort(checkPixel);
                    image.setPixel(x, y, charset.charAt(checkPixel[4]));
                }
            }

            return image;
        }
/** returns either the pixel or '.' if the coordinates outside the image.
 */

    private char getPixel(int x, int y, AsciiImage image){
        try {
            return image.getPixel(x, y);
        }catch(IndexOutOfBoundsException i){
            return '.';
        }
    }
}