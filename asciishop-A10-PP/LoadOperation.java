
/** erzeugt eine neue LoadOperation mit den entsprechenden Bilddaten. 
 * Diese Bilddaten liegen als String vor, wobei die Bildzeilen durch Zeilenumbrüche (‘\n’) getrennt sind.
 * gibt ein neues AsciiImage zurück, das von Größe und Zeichensatz dem übergebenen AsciiImage entspricht 
 * und in das die Daten geladen wurden. 
 * Tritt beim Laden ein Fehler auf (zu wenige oder zu viele Daten bzw. ungültige Zeichen),
 * so wird eine OperationException mit einer entsprechenden Fehlermeldung geworfen.
 */

public class LoadOperation  implements Operation {

    private String data;

    public LoadOperation(String data){
        this.data = data;
        data.replace("\n", "");
    }

    public AsciiImage execute(AsciiImage img) throws OperationException {
        int height = img.getHeight();
        int width = img.getWidth();
        String charset = img.getCharset();
        AsciiImage image = new AsciiImage(img);
        data = data.replaceAll("\n", "");
        //String[] lines = data.split("\n");
        if (height * width != data.length()) {
            throw new OperationException("OPERATION FAILED");
        } else {
            int x = 0;
            int y = 0;
            for (int i = 0; i < data.length(); i++) {
                if ((i % width) == 0 && i != 0) {
                    y++;
                    x = 0;
                }
                if (y < height) {
                    if (charset.indexOf(data.charAt(i)) != -1) {
                        image.setPixel(x, y, data.charAt(i));
                        x++;
                    } else {
                        throw new OperationException("OPERATION FAILED");
                    }
                }
            }
        }
        return image;
    }
}
