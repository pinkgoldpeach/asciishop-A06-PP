import java.util.Scanner;

/** Diese Factory erzeugt LoadOperations. 
 * liest den eof-String ein und übergibt in einem String alle Zeilen
 * bis zum abschließenden eof-String durch Zeilenumbrüche getrennt an den Konstruktor der LoadOperation. 
 * Tritt beim Einlesen ein Fehler auf (eof fehlt), so wird eine FactoryException geworfen.
 */

public class LoadFactory implements Factory{

    public LoadFactory(){

    }

    public Operation create(Scanner scanner) throws FactoryException{
        String data = new String();
        String check = "", eof = "";
        if(scanner.hasNext()) {
            eof = scanner.next();

            while(scanner.hasNext()) {
                check = scanner.next();
                if (check.equals(eof)) {
                    return new LoadOperation(data);

                } else {
                    data += check;
                    data += "\n";
                }
            }
        }
       throw new FactoryException("INPUT MISMATCH");
    }
}
