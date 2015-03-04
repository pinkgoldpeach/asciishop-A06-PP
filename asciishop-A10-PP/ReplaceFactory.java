import java.util.Scanner;

/** Diese Factory erzeugt ReplaceOperations. 
 * liest mit Hilfe des Scanners zwei Zeichen ein und gibt eine damit initialisierte neue ReplaceOperation zurück.
 * Tritt beim Einlesen ein Fehler (zu wenig Parameter), so wird eine FactoryException geworfen.
 */

public class ReplaceFactory implements Factory{

    public ReplaceFactory(){

    }

    public Operation create(Scanner scanner) throws FactoryException{
        if(scanner.hasNext()){
            String check = scanner.next();
            if(check.length() == 1){
                char oldChar = check.charAt(0);
                if(scanner.hasNext()){
                    check = scanner.next();
                    if(check.length() == 1){
                        char newChar = check.charAt(0);
                        return new ReplaceOperation(oldChar, newChar);
                    }
                }
            }
        }
        throw new FactoryException("INPUT MISMATCH");
    }
}
