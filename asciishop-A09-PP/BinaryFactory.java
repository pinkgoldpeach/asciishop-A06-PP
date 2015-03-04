import java.util.Scanner;

/** Diese Factory erzeugt BinaryOperations.
 * liest mit dem Scanner das Schwellwert Zeichen ein, 
 * erzeugt damit eine neue BinaryOperation und gibt diese zurück. 
 * Tritt beim Einlesen des Zeichens ein Fehler auf, so wird eine FactoryExceptionn geworfen.
 */

public class BinaryFactory implements Factory {
    public BinaryFactory(){

    }

    public Operation create(Scanner scanner) throws FactoryException{
        if(scanner.hasNext()){
            String check = scanner.next();

            if(check.length()==1){
                return new BinaryOperation(check.charAt(0));
            }
        }
        throw new FactoryException("INPUT MISMATCH");
    }

}
