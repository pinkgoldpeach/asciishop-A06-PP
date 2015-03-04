import java.util.Scanner;

/** Diese Factory erzeugt LoadOperations. 
 * liest den eof-String ein und übergibt in einem String alle Zeilen
 * bis zum abschließenden eof-String durch Zeilenumbrüche getrennt an den Konstruktor der LoadOperation. 
 * Tritt beim Einlesen ein Fehler auf (eof fehlt), so wird eine FactoryException geworfen.
 */

public class LoadFactory implements Factory {

    public LoadFactory(){

    }

    public Operation create(Scanner scanner) throws FactoryException{
        if(scanner.hasNext()){
            String eof = scanner.next();
            String image = "";
            String check = "";
            while(scanner.hasNext()){

                check = scanner.next();
                if(check.equals(eof)) {
                    return new LoadOperation(image);
                }else{
                    image += check;
                    image += "\n";

                }
            }
        }
        throw new FactoryException("INPUT MISMATCH");
    }
}
