import java.util.Scanner;

/** Diese Factory erzeugt MedianOperations.
 * liest den nächsten String ein und gibt, falls dieser ‘median’ ist, eine neue MedianOperation zurück. 
 * Tritt beim Einlesen des Strings ein Fehler auf, oder ist der String nicht ‘median’, 
 * so wird eine FactoryException geworfen.
 */
public class FilterFactory implements Factory {

    public Operation create(Scanner scanner) throws FactoryException{
        if(scanner.hasNext()){
            if(scanner.next().equals("median"));
            return new MedianOperation();
        }
        else{
            throw new FactoryException("INPUT MISMATCH");
        }
    }
}
