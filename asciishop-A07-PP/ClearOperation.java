/** sets all pixels in the image to the lightest character = last char in charset of image
*/

public class ClearOperation{

    public ClearOperation(){

    }

    public AsciiImage execute(AsciiImage img) {
        int height = img.getHeight();
        int width = img.getWidth();
        String charset = img.getCharset();
        AsciiImage image = new AsciiImage(img);
        char c = charset.charAt(charset.length() - 1);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                image.setPixel(x, y, c);
            }
        }
        return image;
    }
}