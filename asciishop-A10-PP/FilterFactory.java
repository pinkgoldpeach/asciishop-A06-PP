import java.util.Scanner;

/** Diese Factory erzeugt MedianOperations und AverageOperations.
 * liest den nächsten String ein und gibt, je nach Parameter, 
 * eine neue MedianOperation (bei ‘median’) oder eine neue AverageOperation (bei ‘average’) zurück. 
 * Ist der Typ unbekannt, so wird eine FactoryException geworfen.
 */

public class FilterFactory implements Factory {

    public FilterFactory(){

    }

    public Operation create(Scanner scanner) throws FactoryException{
        if(scanner.hasNext()){
            String filter = scanner.next();
            if(filter.equals("median")){
                return new MedianOperation();
            }else if(filter.equals("average")){
                return new AverageOperation();
            }
        }
        throw new FactoryException("INPUT MISMATCH");
    }
}
